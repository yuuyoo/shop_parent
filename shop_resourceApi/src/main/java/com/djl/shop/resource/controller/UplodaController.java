package com.djl.shop.resource.controller;

import com.djl.common.vo.R;
import com.djl.shop.resource.util.UploadFileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DJL
 * @create 2019-06-22 21:44
 * @desc 文件上传
 **/
@RestController
@Api(value = "文件上传的API接口", tags = "文件上传至七牛云的数据接口")
public class UplodaController {

    @Autowired(required = false)
    private UploadFileUtil uploadFileUtil;

    @ApiOperation("单个文件上传接口")
    @PostMapping("/api/resource/upload.do")
    public R upload(MultipartFile file, HttpServletRequest request){
        if (!file.isEmpty()) {
            String fileUrl = uploadFileUtil.uploadMultipartFile(file);
            return R.setOK("OK", fileUrl);
        } else {
            return R.setERROR("请选择上传的文件");
        }
    }
}
