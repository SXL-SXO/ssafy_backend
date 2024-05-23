package com.example.enjoytrip.image.controller;

import com.example.enjoytrip.exception.CustomException;
import com.example.enjoytrip.exception.ErrorCode;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@RestController
@RequestMapping("/upload")
@Slf4j
public class ImageController {
    @Value("${file.dir}")
    private String fileDir;

    @GetMapping()
    public String newFile() {
        return "upload-form";
    }

    @GetMapping("/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws
            MalformedURLException {
        System.out.println("filename = " + filename);
        String fullPath = getFullPath(filename);
//        String path = fullPath.substring(2);
//        System.out.println(path);
//        System.out.println(fullPath);

        return new UrlResource("file:" + fullPath);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> saveFile(@ModelAttribute("file") MultipartFile file) throws IOException {
//        System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
//        System.out.println("file.getName() = " + file.getName());
//        System.out.println("file.getSize() = " + file.getSize());

        String uuid;
        if (!file.isEmpty()) {
            uuid = createStoreFileName(file.getOriginalFilename()); // uuid + ext
            System.out.println("uuid = " + uuid);
            // store -> 이미지 스토어에 저장
            file.transferTo(new File(getFullPath(uuid)));
        }else{
            throw new CustomException(ErrorCode.FileUploadFail);
        }
        Map<String, String> map = new HashMap<>();
        map.put("url", uuid);
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
    public String getFullPath(String filename) {
        return fileDir + filename;
    }
}