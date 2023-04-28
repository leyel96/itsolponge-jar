package com.solponge;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@SpringBootApplication
public class ItsolpongeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItsolpongeApplication.class, args);
	}

	@Bean // 자바 빈 생성 함수
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception{ //마이바티스 맵퍼를 위치 지정(맵퍼는 테이블과 클래서 연결)
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"); //src/main/resources/mappers(여기 설정에 따라 변경가능)/에 있는 xml 파일 검색 맵퍼 등록
		sessionFactory.setMapperLocations(res);
		return sessionFactory.getObject();
	}
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {//sql을 실행 하기 위한 sql 세션을 생성 하는 함수
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
