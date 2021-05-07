package fr.service.saccr.export.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig extends HikariConfig  {
	@Autowired Environment env;
	
	@Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
	@ConfigurationProperties("spring.datasource.primary")
	public HikariDataSource dataSource(){
	     return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}
	@Bean(name ="primaryJdbcTemplate")
	@Qualifier("primaryJdbcTemplate")
	public JdbcTemplate jdbcTemplate(
		@Qualifier("primaryDataSource") DataSource dataSource){
	    return new JdbcTemplate(dataSource);
	}  	
	@Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
	@ConfigurationProperties(prefix="spring.datasource.secondary")
	public HikariDataSource secondaryDataSource(){  
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
	 }	 	 
	 @Bean(name = "secondaryJdbcTemplate")
	 @Qualifier("secondaryJdbcTemplate")
	 public JdbcTemplate secondaryJdbcTemplate(
	    @Qualifier("secondaryDataSource") DataSource dataSource){
	    return new JdbcTemplate(dataSource);
	 }
}
