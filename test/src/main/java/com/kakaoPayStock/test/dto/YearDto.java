package com.kakaoPayStock.test.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class YearDto {
   
	private int tradeYear;
	
	private List<BranchDto> branchList;
}