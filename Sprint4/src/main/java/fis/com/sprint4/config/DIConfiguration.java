package fis.com.sprint4.config;//package com.fis.ducnv.config;
//
//import com.fis.ducnv.dao.jdbc_spring.CriminalCaseDAO;
//import com.fis.ducnv.dao.jdbc_spring.impl.CriminalCaseImpl;
//import com.fis.ducnv.util.EnvUtil;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//@ComponentScan(value={"com.fis.ducnv"})
//public class DIConfiguration {
//    @Bean
////    @Scope("singleton")
//    public DataSource getDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        String url = "jdbc:mysql://" + EnvUtil.get("DB_HOST") + ":" + EnvUtil.get("DB_PORT") + "/" + EnvUtil.get("DB_NAME") + "?serverTimezone=UTC";
//        dataSource.setUrl(url);
//        dataSource.setUsername(EnvUtil.get("DB_USER"));
//        dataSource.setPassword(EnvUtil.get("DB_PASSWORD"));
//        return dataSource;
//    }
//
//    @Bean
//    public CriminalCaseDAO getCriminalCaseDAO() {
//        return new CriminalCaseImpl(getDataSource());
//    }
//}
