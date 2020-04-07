package com.kakaoPayStock.test.dao.trade;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kakaoPayStock.test.dto.BranchDto;
import com.kakaoPayStock.test.dto.TradeAccount1Dto;
import com.kakaoPayStock.test.dto.TradeAccount2Dto;
import com.kakaoPayStock.test.dto.YearDto;

public interface ITradeDao {
	
	/**
	 * 2018, 2019�� �հ� �ݾ��� ���� ���� �� ����Ʈ ��� Dao
	 * @return List<TradeAccount1Dto>
	 */
	public List<TradeAccount1Dto> getAccountTradeSumAmountByYear();

	/**
	 * 2018��, 2019�� �ŷ� ���� ��(����) ����Ʈ  ���  Dao
	 * @return List<TradeAccount2Dto>
	 */
	public List<TradeAccount2Dto> getAccountTradeYnByYear();
	
	/**
	 * �ŷ����� �� ���� ����Ʈ ���  Dao
	 * @return List<YearDto>
	 */
	public List<YearDto> getTradeYear();
	
	/**
	 * ������ �������� �ŷ��ݾ� ����Ʈ ���  Dao
	 * @param tradeYear
	 * @return List<BranchDto>
	 */
	public List<BranchDto> getBranchSumAmtByYear(@Param("tradeYear") int tradeYear);
	
	/**
	 * ������ �˻����� ������, �������ڵ�, �ŷ��ݾ� �հ� ��ȯ  ��� Dao
	 * @param branchName
	 * @return BranchDto
	 */
	public BranchDto getBranchSumAmt(@Param("branchName") String branchName);
}
