package com.zkf.controller;

import com.zkf.model.VResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class JrImageController {

    private static final Logger logger = LoggerFactory.getLogger(JrImageController.class);


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public VResult<?> upload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        VResult<String> vResult = new VResult<>();
        vResult.setSuccess(false);
        BufferedOutputStream bos = null;
        try {
            Long time = System.currentTimeMillis();
            String fileName = time + "." + file.getOriginalFilename().split("\\.")[1];
            vResult.setContent(fileName);
//            File newFile = new File("F:\\" + fileName);
            File newFile = new File("/usr/app/files/" + fileName);
            if (!newFile.exists()) {
                newFile.createNewFile();
            } else {
                newFile.delete();
                newFile.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(newFile);
            bos = new BufferedOutputStream(fos);
            bos.write(file.getBytes());
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            logger.error("上传文件异常", e);
            return vResult;
        } catch (IOException e) {
            logger.error("上传文件异常", e);
            return vResult;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    logger.error("上传文件异常", e);
                    return vResult;
                }
            }
        }
        vResult.setSuccess(true);
        return vResult;
    }
}
