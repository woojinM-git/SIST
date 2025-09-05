package com.sist.ex0905_emp.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration // 설정객체로 지정하기 위해 필요함
@MapperScan("com.sist.ex0905_emp.mybatis.mapper")
public class DbConfig {
    // 현재 클래스는 스프링(Boot) 환경이 알아서 호출하며,
    // 아래의 @Bean 어노테이션 때문에
    // 강제로 함수들을 한번씩 호출하여 스프링 환경(Context)에
    // 반환되어 객체들을 등록한다.

    @Bean // Bean으로 인해 1번만 수행함
    public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception{
        // 반환객체인 SqlSessionFactory를 만드는 객체를 생성하자!
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 생성된 factoryBean은 비어있는 상태다. 이때
        // 인자로 받은 ds를 factoryBean에게 지정한다.
        factoryBean.setDataSource(datasource);

        // class를 인지하는 객체 생성
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        Resource[] resourceAr = resolver.getResources("classpath:mybatis/mapper/**/*.xml");

        // Sql 문장들은 가진 mapper들 지정
        factoryBean.setMapperLocations(resourceAr);

        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory factory){
        return new SqlSessionTemplate(factory);
    }
}
