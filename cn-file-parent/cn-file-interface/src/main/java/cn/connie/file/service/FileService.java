package cn.connie.file.service;

import cn.connie.file.from.TFileFrom;
import cn.connie.file.to.TFileTO;

public interface FileService {


    TFileTO insert(TFileFrom tFileFrom);

    TFileTO getFileByMD5(String md5);
}
