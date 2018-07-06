package cn.connie.business.core.impl;

import cn.connie.business.service.FileBusinessService;
import cn.connie.business.to.TFileBTO;
import cn.connie.common.exception.CustomException;
import cn.connie.common.utils.FileUtils;
import cn.connie.file.from.TFileFrom;
import cn.connie.file.service.FileService;
import cn.connie.file.to.TFileTO;
import com.sgcai.commons.lang.utils.BeanConvertUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileBusinessServiceImpl implements FileBusinessService {


    @Value("${local.save.path}")
    private String LOCAL_SAVE_PATH;
    @Value("${network.access.path}")
    private String NETWORK_ACCESS_PATH;

    @Autowired
    private FileService fileService;

    @Override
    public TFileBTO uploadFile(String fileName, byte[] data) throws CustomException {
        String md5 = DigestUtils.md5Hex(data);
        TFileTO tFileTO = fileService.getFileByMD5(md5);
        if (tFileTO != null) {
            return BeanConvertUtils.convert(tFileTO, TFileBTO.class);
        } else {
            String url;
            try {
                url = saveFile(fileName, data);
            } catch (IOException e) {
                throw new CustomException("upload failed", "文件上传失败");
            }
            TFileFrom tFileFrom = new TFileFrom();
            tFileFrom.setMd5(md5);
            tFileFrom.setUrl(url);
            tFileTO = fileService.insert(tFileFrom);
            return BeanConvertUtils.convert(tFileTO, TFileBTO.class);
        }
    }

    /**
     * 保存文件到服务器中，返回文件访问路径
     *
     * @param fileName
     * @param data
     * @return
     * @throws IOException
     */
    private String saveFile(String fileName, byte[] data) throws IOException {
        StringBuilder sb = new StringBuilder();
        String month = new SimpleDateFormat("yyMM").format(new Date());
        String newFileName = FileUtils.getUniqueFileName(fileName);
        String dir = sb.append(LOCAL_SAVE_PATH).append(month).append("/").toString();
        sb.append(newFileName);

        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        ByteArrayInputStream in = null;
        try {
            in = new ByteArrayInputStream(data);
            Image src = Toolkit.getDefaultToolkit().createImage(data);
            BufferedImage image = FileUtils.toBufferedImage(src);
            Thumbnails.of(image).scale(1f).outputQuality(1f).toFile(sb.toString());
            return NETWORK_ACCESS_PATH + month + "/" + newFileName;
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
