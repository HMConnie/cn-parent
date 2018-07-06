package cn.connie.business.core.impl;

import cn.connie.business.service.FileBusinessService;
import cn.connie.business.to.TFileBTO;
import cn.connie.common.exception.CustomException;
import cn.connie.common.utils.FileUtils;
import cn.connie.file.from.TFileFrom;
import cn.connie.file.service.FileService;
import cn.connie.file.to.TFileTO;
import com.sgcai.commons.lang.utils.BeanConvertUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FileBusinessServiceImpl implements FileBusinessService {

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
                url = FileUtils.saveFile(fileName, data);
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
}
