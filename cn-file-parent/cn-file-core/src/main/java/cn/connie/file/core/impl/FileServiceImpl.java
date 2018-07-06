package cn.connie.file.core.impl;

import cn.connie.file.core.dao.TFileMapper;
import cn.connie.file.core.entity.TFile;
import cn.connie.file.core.entity.TFileExample;
import cn.connie.file.from.TFileFrom;
import cn.connie.file.service.FileService;
import cn.connie.file.to.TFileTO;
import com.sgcai.commons.lang.utils.BeanConvertUtils;
import com.sgcai.commons.lang.utils.Dui1DuiStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service层一般不做业务判断，只做数据的增删改查
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private TFileMapper fileMapper;

    @Override
    public TFileTO insert(TFileFrom tFileFrom) {
        TFile tFile = new TFile();
        tFile.setId(Dui1DuiStringUtils.generateUUID());
        tFile.setMd5(tFileFrom.getMd5());
        tFile.setUrl(tFileFrom.getUrl());
        tFile.setCreated(new Date());
        fileMapper.insert(tFile);
        return BeanConvertUtils.convert(tFile, TFileTO.class);
    }

    @Override
    public TFileTO getFileByMD5(String md5) {
        TFileExample example = new TFileExample();
        example.createCriteria().andMd5EqualTo(md5);
        List<TFile> tFiles = fileMapper.selectByExample(example);
        if (tFiles.size() > 0 && tFiles.get(0) != null) {
            TFile tFile = tFiles.get(0);
            return BeanConvertUtils.convert(tFile, TFileTO.class);
        }
        return null;
    }
}
