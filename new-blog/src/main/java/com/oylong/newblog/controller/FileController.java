package com.oylong.newblog.controller;


import com.oylong.newblog.entity.Result;
import com.oylong.newblog.utils.ResultUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;


@Api(tags = "图片上传接口")
@Slf4j
@RestController
public class FileController {

    @Value("${server.ip}")
    private  String ip;
    @Value("${server.port}")
    private  String port;

    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file, HttpServletRequest request) throws FileNotFoundException {
        String filePath = ResourceUtils.getFile("classpath:").getAbsolutePath() + "/static/img/";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdirs();
        }

        String path = file1.getAbsolutePath() + File.separator;

        //原始文件名
        String originalFilename = file.getOriginalFilename();
        //文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        //图片名称为uuid+图片后缀防止冲突
        String fileName = UUID.randomUUID().toString() + "." + suffix;
        String finalFilePath = path + fileName.trim();
        log.info(finalFilePath);
        File desFile = new File(finalFilePath);
        if (desFile.exists()) {
            desFile.delete();
        }
        try {
            file.transferTo(desFile);
            Result result = ResultUtil.buildSuccessResult("上传成功");
            result.setData("http://"+ip+":"+port+"/img/" + fileName);
            return result;
        } catch (Exception e) {
            return ResultUtil.buildUnSuccessResult("上传失败");
        }
    }
}
