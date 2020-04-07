package com.kakaoPayStock.test.dao;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.kakaoPayStock.test.dao.trade.ITradeDao;

@Configuration
public class DaoBeanFactory {
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource primaryDs;

	@Bean(destroyMethod = "clearCache")
	public SqlSessionTemplate baseSessionTemplate() {
		try {
			PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		    Resource[] resources = resolver.getResources("classpath*:sql/*.xml");

			SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
			sqlSessionFactory.setDataSource(primaryDs);
			sqlSessionFactory.setMapperLocations(resources);

			return new SqlSessionTemplate(sqlSessionFactory.getObject());
		} catch (Exception e) {

		}

		return null;
	}
	
	@Bean
	public ITradeDao tradeDao() {
		SqlSessionTemplate sessionTemplate = baseSessionTemplate();
		return sessionTemplate.getMapper(ITradeDao.class);
	}
}