package com.example.enjoytrip.image.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageDto {
    private Long imageId;
    //파일 경로
    private String imagePath;
    // ** uuid (랜덤키)
    private String imageUuid;
    private MultipartFile file;
}