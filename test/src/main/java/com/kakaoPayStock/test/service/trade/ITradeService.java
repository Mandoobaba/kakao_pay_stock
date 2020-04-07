package com.kakaoPayStock.test.service.trade;

import java.util.List;

import com.kakaoPayStock.test.dto.BranchDto;
import com.kakaoPayStock.test.dto.TradeAccount1Dto;
import com.kakaoPayStock.test.dto.TradeAccount2Dto;
import com.kakaoPayStock.test.dto.YearDto;

public interface ITradeService {
	
	/**
	 * 2018, 2019�� �հ� �ݾ��� ���� ���� �� ����Ʈ ��� Service
	 * @return List<TradeAccount1Dto>
	 */
	public List<TradeAccount1Dto> getAccountTradeSumAmountByYear();

	/**
	 * 2018��, 2019�� �ŷ� ���� ��(����) ����Ʈ  ���  Service
	 * @return List<TradeAccount2Dto>
	 */
	public List<TradeAccount2Dto> getAccountTradeYnByYear();

	/**
	 * �ŷ����� �� ���� ����Ʈ ���  Service
	 * @return List<YearDto>
	 */
	public List<YearDto> getTradeYear();

	/**
	 * ������ �������� �ŷ��ݾ� ����Ʈ ���  Service
	 * @param tradeYear
	 * @return List<BranchDto>
	 */
	public List<BranchDto> getBranchSumAmtByYear(int tradeYear);
	
	/**
	 * ������ �˻����� ������, �������ڵ�, �ŷ��ݾ� �հ� ��ȯ  ��� Service
	 * @param branchName
	 * @return BranchDto
	 */
	public BranchDto getBranchSumAmt(String branchName);
}
