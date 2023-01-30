package com.azki.utils;

import com.azki.repository.*;
import com.azki.service.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class AppConfig {
    @Bean
    public CommentsDao getCommentsDao() throws SQLException {
        return new CommentsDao();
    }

    @Bean
    public CommentsService getCommentsService() {
        return new CommentsService();
    }
    
    @Bean
    public CompaniesDao getCompaniesDao() throws SQLException {
        return new CompaniesDao();
    }

    @Bean
    public CompaniesService getCompaniesService() {
        return new CompaniesService();
    }

    @Bean
    public HealthInsuranceDao getHealthInsuranceDao() throws SQLException {
        return new HealthInsuranceDao();
    }

    @Bean
    public HealthInsuranceService getHealthInsuranceService() {
        return new HealthInsuranceService();
    }
    
    @Bean
    public LifeInsuranceDao getLifeInsuranceDao() throws SQLException {
        return new LifeInsuranceDao();
    }

    @Bean
    public LifeInsuranceService getLifeInsuranceService() {
        return new LifeInsuranceService();
    }

    @Bean
    public OTPDao getOTPDao() throws SQLException {
        return new OTPDao();
    }

    @Bean
    public OTPService getOTPService() {
        return new OTPService();
    }

    @Bean
    public PeopleHealthInsuranceDao getPeopleHealthInsuranceDao() throws SQLException {
        return new PeopleHealthInsuranceDao();
    }

    @Bean
    public PeopleHealthInsuranceService getPeopleHealthInsuranceService() {
        return new PeopleHealthInsuranceService();
    }
    
    @Bean
    public PropertyInsuranceDao getPropertyInsuranceDao() throws SQLException {
        return new PropertyInsuranceDao();
    }

    @Bean
    public PropertyInsuranceService getPropertyInsuranceService() {
        return new PropertyInsuranceService();
    }

    @Bean
    public ReminderDao getReminderDao() throws SQLException {
        return new ReminderDao();
    }

    @Bean
    public ReminderService getReminderService() {
        return new ReminderService();
    }

    @Bean
    public UserDao getUserDaoDao() throws SQLException {
        return new UserDao();
    }

    @Bean
    public UserService getUserDaoService() {
        return new UserService();
    }
}
