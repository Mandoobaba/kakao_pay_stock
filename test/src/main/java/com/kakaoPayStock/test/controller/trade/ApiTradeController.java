package com.kakaoPayStock.test.controller.trade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kakaoPayStock.test.dto.BranchDto;
import com.kakaoPayStock.test.dto.JsonResultDto;
import com.kakaoPayStock.test.dto.TradeAccount1Dto;
import com.kakaoPayStock.test.dto.TradeAccount2Dto;
import com.kakaoPayStock.test.dto.YearDto;
import com.kakaoPayStock.test.exception.HasNoBrCodeException;
import com.kakaoPayStock.test.service.trade.ITradeService;

@RestController
@RequestMapping(value="/trade")
public class ApiTradeController {
	
	@Autowired
	ITradeService tradeSvc;

	/**
	 * 2018, 2019�� �հ� �ݾ��� ���� ���� �� ����Ʈ�� ����ϴ� Controller
	 * @return List<TradeAccount1Dto>
	 */
	@RequestMapping(value="/account/1", method = {RequestMethod.GET})
	public List<TradeAccount1Dto> getAccountTradeSumAmountByYear() {
		
		return tradeSvc.getAccountTradeSumAmountByYear();
	}
	
	/**
	 * 2018��, 2019�� �ŷ� ���� ��(����) ����Ʈ�� ����ϴ� Controller
	 * @return List<TradeAccount2Dto>
	 */
	@RequestMapping(value="/account/2", method = {RequestMethod.GET})
	public List<TradeAccount2Dto> getAccountTradeYnByYear() {
		
		return tradeSvc.getAccountTradeYnByYear();
	}
	
	/**
	 * ������ �������� �ŷ��ݾ� ����Ʈ�� ����ϴ� Controller
	 * @return List<YearDto>
	 */
	@RequestMapping(value="/branch/1", method = {RequestMethod.GET})
	public List<YearDto> getBranchSumAmtByYear() {
		
		List<YearDto> tradeYearList = tradeSvc.getTradeYear();
		
		for(YearDto year : tradeYearList) {
			year.setBranchList(tradeSvc.getBranchSumAmtByYear(year.getTradeYear()));
		}
		
		return tradeYearList;
	}
	
	/**
	 * ������ �˻����� ������, �������ڵ�, �ŷ��ݾ� �հ踦 ��ȯ�ϴ� Controller
	 * @param brName
	 * @return BranchDto
	 * @throws HasNoBrCodeException
	 */
	@RequestMapping(value="/branch/2", method = {RequestMethod.GET})
	public BranchDto getBranchSumAmt(
			@RequestParam(name="brName", required=true, defaultValue="") String brName
		) throws HasNoBrCodeException {
		
		BranchDto branch = tradeSvc.getBranchSumAmt(brName);
		
		if(branch == null) {
			throw new HasNoBrCodeException();
		}
		
		return branch;
	}
	
	/**
	 * ������ �˻� �� ����� ã�� �� ���� �� �߻��ϴ� Exception
	 * @return JsonResultDto
	 */
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(HasNoBrCodeException.class)
	public JsonResultDto hasNoBrCodeExceptionHandler() {

		return JsonResultDto.builder()
							.code("404")
							.message("br code not found")
							.build();
	}
}