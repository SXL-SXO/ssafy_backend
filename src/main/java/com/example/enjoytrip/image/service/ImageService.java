package com.example.enjoytrip.image.service;

import com.example.enjoytrip.image.dto.ImageDto;
import org.springframework.transaction.annotation.Transactional;

public interface ImageService {
    @Transactional
    String saveFile(ImageDto imageDto);
}
