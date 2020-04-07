package com.kakaoPayStock.test.service.trade;

import java.util.List;

import com.kakaoPayStock.test.dto.BranchDto;
import com.kakaoPayStock.test.dto.TradeAccount1Dto;
import com.kakaoPayStock.test.dto.TradeAccount2Dto;
import com.kakaoPayStock.test.dto.YearDto;

public interface ITradeService {
	
	/**
	 * 2018, 2019년 합계 금액이 가장 많은 고객 리스트 출력 Service
	 * @return List<TradeAccount1Dto>
	 */
	public List<TradeAccount1Dto> getAccountTradeSumAmountByYear();

	/**
	 * 2018년, 2019년 거래 없는 고객(계좌) 리스트  출력  Service
	 * @return List<TradeAccount2Dto>
	 */
	public List<TradeAccount2Dto> getAccountTradeYnByYear();

	/**
	 * 거래내역 상 연도 리스트 출력  Service
	 * @return List<YearDto>
	 */
	public List<YearDto> getTradeYear();

	/**
	 * 연도별 관리점별 거래금액 리스트 출력  Service
	 * @param tradeYear
	 * @return List<BranchDto>
	 */
	public List<BranchDto> getBranchSumAmtByYear(int tradeYear);
	
	/**
	 * 지점명 검색으로 지점명, 관리점코드, 거래금액 합계 반환  출력 Service
	 * @param branchName
	 * @return BranchDto
	 */
	public BranchDto getBranchSumAmt(String branchName);
}
