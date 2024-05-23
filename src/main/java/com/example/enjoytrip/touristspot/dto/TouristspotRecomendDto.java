package com.example.enjoytrip.touristspot.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TouristspotRecomendDto {
    private Integer touristspotId;
    private Integer accountId;
    private String mbti;
}
