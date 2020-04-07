package com.kakaoPayStock.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TradeAccount1Dto {
   
	private int tradeYear;
	private String accountNo;
	private String accountName;
	private int sumAmount;
	  
}