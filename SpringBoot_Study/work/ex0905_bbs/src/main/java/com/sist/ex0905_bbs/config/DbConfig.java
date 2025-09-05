package com.sist.ex0905_bbs.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.sist.ex0905_bbs.mapper")
public class DbConfig {
    // 자동으로 스프링환경이 한번 호출한다.

    @Bean // 반환하는놈을 뽑아서 Bean에 등록함
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
        // 위의 @Bean이라고 명시했기 때문에 빈객체를 만들기 위해 한번 호출함
        // SqlSessionFactory를 생성하는 객체를 만들자
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        factoryBean.setDataSource(ds);

        // SQL 문장들(mapper)을 인식하기 위해 mapper들이 있는 위치를 지정하자
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));

        return factoryBean.getObject(); // SqlSessionFactory 반환
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
