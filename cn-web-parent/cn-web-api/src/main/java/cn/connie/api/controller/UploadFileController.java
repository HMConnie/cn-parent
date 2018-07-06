package cn.connie.api.controller;

import cn.connie.business.service.FileBusinessService;
import cn.connie.business.to.TFileBTO;
import cn.connie.common.exception.CustomException;
import cn.connie.common.utils.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;

@Controller
@RequestMapping("/upload")
public class UploadFileController {

    @Resource
    private FileBusinessService fileBusinessService;

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("img") CommonsMultipartFile file) throws CustomException {
        String fileName = file.getOriginalFilename();
        TFileBTO tFileBTO = fileBusinessService.uploadFile(fileName, file.getBytes());
        return ResponseUtils.toSuccessResponse(tFileBTO);
    }

}
