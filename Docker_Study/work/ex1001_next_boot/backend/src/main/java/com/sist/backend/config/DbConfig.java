package com.sist.backend.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.sist.backend.mapper")
public class DbConfig {

    @Value("${mapper-location}")
    private String mapper_uri;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception{
        // 반환객체인 SqlSessionFactory를 만드는 객체를 생성하자!
        SqlSessionFactoryBean factoryBean =
                new SqlSessionFactoryBean();
        //생성된 factoryBean은 비어있는 상태다. 이때
        // 인자로 받은 ds를 factoryBean에게 지정한다.
        factoryBean.setDataSource(ds);

        PathMatchingResourcePatternResolver resolver =
                new PathMatchingResourcePatternResolver();


        // SQL문장들을 가진 mapper들 지정
        factoryBean.setMapperLocations(resolver.getResources(
                mapper_uri));

        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory factory){
        return new SqlSessionTemplate(factory);
    }
}
