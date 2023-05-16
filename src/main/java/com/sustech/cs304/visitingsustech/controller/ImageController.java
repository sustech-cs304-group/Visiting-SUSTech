package com.sustech.cs304.visitingsustech.controller;

import com.sustech.cs304.visitingsustech.common.JsonResult;
import com.sustech.cs304.visitingsustech.exception.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Value("${spring.servlet.multipart.location}")
    private String path;

    @PostMapping("/upload_image")
    public JsonResult<String> updateAvatar(HttpServletRequest request, @RequestParam("image") MultipartFile image) {
        if (image.isEmpty())
            return JsonResult.error("上传失败");
        try {
            File file = new File(path);
            if (!file.exists())
                file.mkdirs();
            String suffix = Objects.requireNonNull(image.getOriginalFilename()).substring(image.getOriginalFilename().lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
            image.transferTo(new File(path + newFileName));
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + newFileName;
            return JsonResult.success(200, "上传图片成功", url);
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        } catch (IOException e) {
            return JsonResult.error("上传图片失败");
        }
    }
}
