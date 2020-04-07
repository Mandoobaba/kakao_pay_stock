package com.kakaoPayStock.test;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.kakaoPayStock.test.controller.ApiTradeController;
import com.kakaoPayStock.test.dto.BranchDto;
import com.kakaoPayStock.test.dto.TradeAccount1Dto;
import com.kakaoPayStock.test.dto.TradeAccount2Dto;
import com.kakaoPayStock.test.dto.YearDto;
import com.kakaoPayStock.test.service.trade.ITradeService;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiTradeController.class)
public class ApiTradeControllerMockTest {

	@Autowired
    MockMvc mockMvc;
	
	@MockBean
	private ITradeService tradeService;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	/**
	 * 2018, 2019년 합계 금액이 가장 많은 고객 리스트를 출력하는 Controller의 Unit Test Method
	 * @throws Exception
	 */
	@DisplayName("/account/1")
	@Test
	void getAccountTradeSumAmountByYearTest() throws Exception{
		
		List<TradeAccount1Dto> list = new ArrayList<TradeAccount1Dto>(); 
		list.add(new TradeAccount1Dto(2018, "11111120", "로이", 18233867));
		list.add(new TradeAccount1Dto(2019, "11111120", "로이", 15000000));
		list.add(new TradeAccount1Dto(2020, "11111121", "에이스", 1000000));				
		
		given(tradeService.getAccountTradeSumAmountByYear()).willReturn(list);
		
		mockMvc.perform(get("/trade/account/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json("[{\"tradeYear\":2018,\"accountNo\":\"11111120\",\"accountName\":\"로이\",\"sumAmount\":18233867},{\"tradeYear\":2019,\"accountNo\":\"11111120\",\"accountName\":\"로이\",\"sumAmount\":15000000},{\"tradeYear\":2020,\"accountNo\":\"11111121\",\"accountName\":\"에이스\",\"sumAmount\":1000000}]"))
				.andDo(print());
	}
	
	/**
	 * 2018년, 2019년 거래 없는 고객(계좌) 리스트를 출력하는 Controller의 Unit Test Method
	 * @throws Exception
	 */
	@DisplayName("/account/2")
	@Test
	void getAccountTradeYnByYear() throws Exception {
		
		List<TradeAccount2Dto> list = new ArrayList<TradeAccount2Dto>(); 
		
		list.add(new TradeAccount2Dto(2018,"11111115","사라"));
		list.add(new TradeAccount2Dto(2018,"11111118","제임스"));
		list.add(new TradeAccount2Dto(2018,"11111121","에이스"));
		list.add(new TradeAccount2Dto(2019,"11111114","테드"));
		list.add(new TradeAccount2Dto(2019,"11111118","제임스"));
		list.add(new TradeAccount2Dto(2019,"11111121","에이스"));

		given(tradeService.getAccountTradeYnByYear()).willReturn(list);
		
		mockMvc.perform(get("/trade/account/2"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json("[{\"tradeYear\":2018,\"accountNo\":\"11111115\",\"accountName\":\"사라\"},{\"tradeYear\":2018,\"accountNo\":\"11111118\",\"accountName\":\"제임스\"},{\"tradeYear\":2018,\"accountNo\":\"11111121\",\"accountName\":\"에이스\"},{\"tradeYear\":2019,\"accountNo\":\"11111114\",\"accountName\":\"테드\"},{\"tradeYear\":2019,\"accountNo\":\"11111118\",\"accountName\":\"제임스\"},{\"tradeYear\":2019,\"accountNo\":\"11111121\",\"accountName\":\"에이스\"}]"))
				.andDo(print());
	}

	/**
	 * 연도별 관리점별 거래금액 리스트를 출력하는 Controller의 Unit Test Method
	 * @throws Exception
	 */
	@DisplayName("/branch/1")
	@Test
	void getBranchSumAmtByYear() throws Exception {
		
		List<BranchDto> branch2018List = new ArrayList<BranchDto>();
				
		branch2018List.add(new BranchDto("B","분당점",38500000));
		branch2018List.add(new BranchDto("A","판교점",20510000));
		branch2018List.add(new BranchDto("C","강남점",20234567));
		branch2018List.add(new BranchDto("D","잠실점",14000000));
		
		List<BranchDto> branch2019List = new ArrayList<BranchDto>();

		branch2019List.add(new BranchDto("A","판교점",66800000));
		branch2019List.add(new BranchDto("B","분당점",45400000));
		branch2019List.add(new BranchDto("C","강남점",19500000));
		branch2019List.add(new BranchDto("D","잠실점",6000000));
		
		List<BranchDto> branch2020List = new ArrayList<BranchDto>();

		branch2020List.add(new BranchDto("E","을지로점",1000000));
		
		List<YearDto> branchYearList = new ArrayList<YearDto>();
		
		branchYearList.add(new YearDto(2018, null));
		branchYearList.add(new YearDto(2019, null));
		branchYearList.add(new YearDto(2020, null));
		
		given(tradeService.getTradeYear()).willReturn(branchYearList);
		given(tradeService.getBranchSumAmtByYear(2018)).willReturn(branch2018List);
		given(tradeService.getBranchSumAmtByYear(2019)).willReturn(branch2019List);
		given(tradeService.getBranchSumAmtByYear(2020)).willReturn(branch2020List);
		
		mockMvc.perform(get("/trade/branch/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json("[{\"tradeYear\":2018,\"branchList\":[{\"branchCode\":\"B\",\"branchName\":\"분당점\",\"sumAmount\":38500000},{\"branchCode\":\"A\",\"branchName\":\"판교점\",\"sumAmount\":20510000},{\"branchCode\":\"C\",\"branchName\":\"강남점\",\"sumAmount\":20234567},{\"branchCode\":\"D\",\"branchName\":\"잠실점\",\"sumAmount\":14000000}]},{\"tradeYear\":2019,\"branchList\":[{\"branchCode\":\"A\",\"branchName\":\"판교점\",\"sumAmount\":66800000},{\"branchCode\":\"B\",\"branchName\":\"분당점\",\"sumAmount\":45400000},{\"branchCode\":\"C\",\"branchName\":\"강남점\",\"sumAmount\":19500000},{\"branchCode\":\"D\",\"branchName\":\"잠실점\",\"sumAmount\":6000000}]},{\"tradeYear\":2020,\"branchList\":[{\"branchCode\":\"E\",\"branchName\":\"을지로점\",\"sumAmount\":1000000}]}]"))
				.andDo(print());
	}

	/**
	 * 지점명 검색으로 지점명, 관리점코드, 거래금액 합계를 반환하는 Controller의 Unit Test Method
	 * @throws Exception
	 */
	@DisplayName("/branch/2")
	@Test
	void getBranchSumAmt() throws Exception {
		
		BranchDto branchDto = new BranchDto("A", "판교점", 174620000);
		
		given(tradeService.getBranchSumAmt("판교점")).willReturn(branchDto);
		
		mockMvc.perform(get("/trade/branch/2").param("brName", "판교점"))
			   .andExpect(status().isOk())
		   	   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		   	   .andExpect(content().json("{\"branchCode\":\"A\",\"branchName\":\"판교점\",\"sumAmount\":174620000}"))
			   .andDo(print());
		/*
		 * 분당점의 경우, 판교점과 통폐합 처리됐으므로 결과가 없음.
		 */
		given(tradeService.getBranchSumAmt("분당점")).willReturn(null);
		
		mockMvc.perform(get("/trade/branch/2").param("brName", "분당점"))
			   .andExpect(status().isNotFound())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(content().json("{\"code\":\"404\",\"message\":\"br code not found\"}"))
			   .andDo(print());
	}
}
