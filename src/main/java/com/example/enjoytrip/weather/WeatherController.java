package com.example.enjoytrip.weather;

import com.example.enjoytrip.exception.CustomException;
import com.example.enjoytrip.exception.ErrorCode;
import com.example.enjoytrip.weather.client.WeatherClient;
import com.example.enjoytrip.weather.dto.WeatherRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherClient weatherClient;

    @PostMapping
    public ResponseEntity<String> getVilageFcst(@Valid @RequestBody WeatherRequestDto weatherRequestDto
                                                , Errors errors){
        if(errors.hasErrors()){
            throw new CustomException(ErrorCode.NotnullWeatherCoordinate);
        }


        return ResponseEntity.ok(weatherClient.getVilageFcst(weatherRequestDto));
    }
}
