package cn.connie.common.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class FileUtils {

    private static final ReentrantLock QUEUE_LOCK = new ReentrantLock();

    private static final AtomicInteger SERIAL_NUM = new AtomicInteger(0);


    public static String getUniqueFileName(String fileName) {
        String ext = getExtension(fileName);
        StringBuilder sb = new StringBuilder();
        try {
            QUEUE_LOCK.lock();
            if (SERIAL_NUM.get() >= 36) {
                SERIAL_NUM.set(0);
            }
            sb.append(Integer.toString(SERIAL_NUM.get(), 36).toLowerCase());
            sb.append(Integer.toString((int) (Math.random() * 36), 36).toLowerCase());
            sb.append(Integer.toString((int) (Math.random() * 36), 36).toLowerCase());
            SERIAL_NUM.incrementAndGet();
            sb.append(Long.toString(System.currentTimeMillis(), 36).toLowerCase());
            sb.append(".").append(ext);
        } finally {
            QUEUE_LOCK.unlock();
        }
        return sb.toString();
    }

    public static String getExtension(String fileName) {
        int ind = fileName.lastIndexOf('.');
        String ext = null;
        if (ind > 0) {
            ext = fileName.substring(ind + 1).toLowerCase();
        }
        return ext;
    }


    /**
     * 获取服务器IP地址
     *
     * @return
     */
    public static String getServerIp() {
        String SERVER_IP = null;
        InetAddress ip = null;
        try {
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                ip = ni.getInetAddresses().nextElement();
                SERVER_IP = ip.getHostAddress();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    SERVER_IP = ip.getHostAddress();
                    break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return SERVER_IP;
    }

    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null),
                    image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null),
                    image.getHeight(null), type);
        }
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }
}
