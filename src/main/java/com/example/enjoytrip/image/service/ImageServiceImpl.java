package com.example.enjoytrip.image.service;

import com.example.enjoytrip.exception.CustomException;
import com.example.enjoytrip.exception.ErrorCode;
import com.example.enjoytrip.image.dao.ImageDao;
import com.example.enjoytrip.image.dto.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{
    private final ImageDao imageDao;

    @Override
    public String saveFile(ImageDto imageDto) {
        Integer result= imageDao.saveFile(imageDto);
        if(result!=1) throw new CustomException(ErrorCode.FileUploadFail);
        return imageDto.getImageUuid();
    }
}
