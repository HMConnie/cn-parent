package cn.connie.common.utils;

import net.coobird.thumbnailator.Thumbnails;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class FileUtils {

    private static final ReentrantLock QUEUE_LOCK = new ReentrantLock();

    private static AtomicInteger SERIAL_NUM = new AtomicInteger(0);

    private static final SimpleDateFormat MONTH_FORMAT = new SimpleDateFormat("yyMM");

    static {
        InputStream in = FileUtils.class.getClassLoader().getResourceAsStream("file.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
            GlobalConfigration.reload(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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
     * 保存文件到服务器中，返回文件访问路径
     *
     * @param fileName
     * @param data
     * @return
     * @throws IOException
     */
    public static String saveFile(String fileName, byte[] data) throws IOException {
        StringBuilder sb = new StringBuilder();
        String newFileName = getUniqueFileName(fileName);
        String dir = sb.append(GlobalConfigration.toString("local_save_path")).append(MONTH_FORMAT.format(new Date())).append("/").toString();
        sb.append(newFileName);

        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        ByteArrayInputStream in = null;
        try {
            in = new ByteArrayInputStream(data);
            Image src = Toolkit.getDefaultToolkit().createImage(data);
            BufferedImage image = toBufferedImage(src);
            Thumbnails.of(image).scale(1f).outputQuality(1f).toFile(sb.toString());
            return GlobalConfigration.toString("network_access_path") + MONTH_FORMAT.format(new Date()) + "/" + newFileName;
        } finally {
            if (in != null) {
                in.close();
            }
        }
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
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {
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
