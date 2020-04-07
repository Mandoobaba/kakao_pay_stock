package com.kakaoPayStock.test.service.trade.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakaoPayStock.test.dao.trade.ITradeDao;
import com.kakaoPayStock.test.dto.BranchDto;
import com.kakaoPayStock.test.dto.TradeAccount1Dto;
import com.kakaoPayStock.test.dto.TradeAccount2Dto;
import com.kakaoPayStock.test.dto.YearDto;
import com.kakaoPayStock.test.service.trade.ITradeService;

@Service
public class TradeService implements ITradeService {

	@Autowired
	ITradeDao tradeDao;
	
	@Override
	public List<TradeAccount1Dto> getAccountTradeSumAmountByYear() {

		return tradeDao.getAccountTradeSumAmountByYear();
	}

	@Override
	public List<TradeAccount2Dto> getAccountTradeYnByYear() {

		return tradeDao.getAccountTradeYnByYear();
	}

	@Override
	public List<YearDto> getTradeYear(){
		
		return tradeDao.getTradeYear();
	}

	@Override
	public List<BranchDto> getBranchSumAmtByYear(int tradeYear) {
		
		return tradeDao.getBranchSumAmtByYear(tradeYear);
	}

	@Override
	public BranchDto getBranchSumAmt(String branchName) {
		
		return tradeDao.getBranchSumAmt(branchName);
	}
	
}
