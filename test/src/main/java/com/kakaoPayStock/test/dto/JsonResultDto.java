package com.kakaoPayStock.test.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JsonResultDto {
   
	private String code;
	private String message;
	
}