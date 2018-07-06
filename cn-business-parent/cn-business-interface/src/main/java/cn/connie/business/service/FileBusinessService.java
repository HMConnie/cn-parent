package cn.connie.business.service;

import cn.connie.business.to.TFileBTO;
import cn.connie.common.exception.CustomException;

public interface FileBusinessService {
    TFileBTO uploadFile(String fileName, byte[] data) throws CustomException;
}
