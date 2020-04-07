package com.kakaoPayStock.test.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class TradeDto {
   
	private Date tradeDate;
	private String accountNo;
	private int tradeNo;
	private int tradeAmount;
	private int commissionAmount;
	private String isCanceled;
	
	private String tradeYear;
	private String accountName;
	private int sumAmount;
	  
}