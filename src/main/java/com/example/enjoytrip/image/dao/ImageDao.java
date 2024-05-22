package com.example.enjoytrip.image.dao;

import com.example.enjoytrip.image.dto.ImageDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageDao {

    Integer saveFile(ImageDto imageDto);
}