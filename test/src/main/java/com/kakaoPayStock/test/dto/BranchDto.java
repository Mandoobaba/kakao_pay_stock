package com.kakaoPayStock.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BranchDto {
   
	private String branchCode;
	private String branchName;
	
	private int sumAmount;
	  
}