package com.yang.ess.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.yang.ess.common.ali.MediaUtile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//阿里文件
@RestController
public class AliFileController {

    private String accessKeyId="LTAINiDMsu3UA2Pa";
    private String accessKeySecret="SSoNb6Aj7y3ZOswT1o6Ghp3QKjygaB";
    /**
     *  文件上传
     * @author JasonTsungLai
     */
    @PostMapping("/upload")
    public JSONObject uploadBanner(MultipartFile file) {
        String url = MediaUtile.testUploadImageStream(accessKeyId, accessKeySecret, file).getImageURL();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("url",url);
        return jsonObject;
    }
}
