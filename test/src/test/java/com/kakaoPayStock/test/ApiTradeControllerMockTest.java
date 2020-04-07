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
	 * 2018, 2019�� �հ� �ݾ��� ���� ���� �� ����Ʈ�� ����ϴ� Controller�� Unit Test Method
	 * @throws Exception
	 */
	@DisplayName("/account/1")
	@Test
	void getAccountTradeSumAmountByYearTest() throws Exception{
		
		List<TradeAccount1Dto> list = new ArrayList<TradeAccount1Dto>(); 
		list.add(new TradeAccount1Dto(2018, "11111120", "����", 18233867));
		list.add(new TradeAccount1Dto(2019, "11111120", "����", 15000000));
		list.add(new TradeAccount1Dto(2020, "11111121", "���̽�", 1000000));				
		
		given(tradeService.getAccountTradeSumAmountByYear()).willReturn(list);
		
		mockMvc.perform(get("/trade/account/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json("[{\"tradeYear\":2018,\"accountNo\":\"11111120\",\"accountName\":\"����\",\"sumAmount\":18233867},{\"tradeYear\":2019,\"accountNo\":\"11111120\",\"accountName\":\"����\",\"sumAmount\":15000000},{\"tradeYear\":2020,\"accountNo\":\"11111121\",\"accountName\":\"���̽�\",\"sumAmount\":1000000}]"))
				.andDo(print());
	}
	
	/**
	 * 2018��, 2019�� �ŷ� ���� ��(����) ����Ʈ�� ����ϴ� Controller�� Unit Test Method
	 * @throws Exception
	 */
	@DisplayName("/account/2")
	@Test
	void getAccountTradeYnByYear() throws Exception {
		
		List<TradeAccount2Dto> list = new ArrayList<TradeAccount2Dto>(); 
		
		list.add(new TradeAccount2Dto(2018,"11111115","���"));
		list.add(new TradeAccount2Dto(2018,"11111118","���ӽ�"));
		list.add(new TradeAccount2Dto(2018,"11111121","���̽�"));
		list.add(new TradeAccount2Dto(2019,"11111114","�׵�"));
		list.add(new TradeAccount2Dto(2019,"11111118","���ӽ�"));
		list.add(new TradeAccount2Dto(2019,"11111121","���̽�"));

		given(tradeService.getAccountTradeYnByYear()).willReturn(list);
		
		mockMvc.perform(get("/trade/account/2"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json("[{\"tradeYear\":2018,\"accountNo\":\"11111115\",\"accountName\":\"���\"},{\"tradeYear\":2018,\"accountNo\":\"11111118\",\"accountName\":\"���ӽ�\"},{\"tradeYear\":2018,\"accountNo\":\"11111121\",\"accountName\":\"���̽�\"},{\"tradeYear\":2019,\"accountNo\":\"11111114\",\"accountName\":\"�׵�\"},{\"tradeYear\":2019,\"accountNo\":\"11111118\",\"accountName\":\"���ӽ�\"},{\"tradeYear\":2019,\"accountNo\":\"11111121\",\"accountName\":\"���̽�\"}]"))
				.andDo(print());
	}

	/**
	 * ������ �������� �ŷ��ݾ� ����Ʈ�� ����ϴ� Controller�� Unit Test Method
	 * @throws Exception
	 */
	@DisplayName("/branch/1")
	@Test
	void getBranchSumAmtByYear() throws Exception {
		
		List<BranchDto> branch2018List = new ArrayList<BranchDto>();
				
		branch2018List.add(new BranchDto("B","�д���",38500000));
		branch2018List.add(new BranchDto("A","�Ǳ���",20510000));
		branch2018List.add(new BranchDto("C","������",20234567));
		branch2018List.add(new BranchDto("D","�����",14000000));
		
		List<BranchDto> branch2019List = new ArrayList<BranchDto>();

		branch2019List.add(new BranchDto("A","�Ǳ���",66800000));
		branch2019List.add(new BranchDto("B","�д���",45400000));
		branch2019List.add(new BranchDto("C","������",19500000));
		branch2019List.add(new BranchDto("D","�����",6000000));
		
		List<BranchDto> branch2020List = new ArrayList<BranchDto>();

		branch2020List.add(new BranchDto("E","��������",1000000));
		
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
				.andExpect(content().json("[{\"tradeYear\":2018,\"branchList\":[{\"branchCode\":\"B\",\"branchName\":\"�д���\",\"sumAmount\":38500000},{\"branchCode\":\"A\",\"branchName\":\"�Ǳ���\",\"sumAmount\":20510000},{\"branchCode\":\"C\",\"branchName\":\"������\",\"sumAmount\":20234567},{\"branchCode\":\"D\",\"branchName\":\"�����\",\"sumAmount\":14000000}]},{\"tradeYear\":2019,\"branchList\":[{\"branchCode\":\"A\",\"branchName\":\"�Ǳ���\",\"sumAmount\":66800000},{\"branchCode\":\"B\",\"branchName\":\"�д���\",\"sumAmount\":45400000},{\"branchCode\":\"C\",\"branchName\":\"������\",\"sumAmount\":19500000},{\"branchCode\":\"D\",\"branchName\":\"�����\",\"sumAmount\":6000000}]},{\"tradeYear\":2020,\"branchList\":[{\"branchCode\":\"E\",\"branchName\":\"��������\",\"sumAmount\":1000000}]}]"))
				.andDo(print());
	}

	/**
	 * ������ �˻����� ������, �������ڵ�, �ŷ��ݾ� �հ踦 ��ȯ�ϴ� Controller�� Unit Test Method
	 * @throws Exception
	 */
	@DisplayName("/branch/2")
	@Test
	void getBranchSumAmt() throws Exception {
		
		BranchDto branchDto = new BranchDto("A", "�Ǳ���", 174620000);
		
		given(tradeService.getBranchSumAmt("�Ǳ���")).willReturn(branchDto);
		
		mockMvc.perform(get("/trade/branch/2").param("brName", "�Ǳ���"))
			   .andExpect(status().isOk())
		   	   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		   	   .andExpect(content().json("{\"branchCode\":\"A\",\"branchName\":\"�Ǳ���\",\"sumAmount\":174620000}"))
			   .andDo(print());
		/*
		 * �д����� ���, �Ǳ����� ������ ó�������Ƿ� ����� ����.
		 */
		given(tradeService.getBranchSumAmt("�д���")).willReturn(null);
		
		mockMvc.perform(get("/trade/branch/2").param("brName", "�д���"))
			   .andExpect(status().isNotFound())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(content().json("{\"code\":\"404\",\"message\":\"br code not found\"}"))
			   .andDo(print());
	}
}
