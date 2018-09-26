package com.capgemini.bankapp;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.capgemini.bankapp.config.AppConfig;
import com.capgemini.bankapp.controller.BankAccountController;
import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.repository.impl.BankAccountRepositoryImpl;

public class Application {
public static void main(String args[]) throws AccountNotFoundException {
/*	ApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");*/
	ApplicationContext context= new AnnotationConfigApplicationContext(AppConfig.class);
	BankAccountController bankAccountController=context.getBean("bankAccountController",BankAccountController.class);
	BankAccount account=new BankAccount() ;
	account.setAccountBalance(2000);
	account.setAccountHolderName("spandhana");
	account.setAccountId(3);
	account.setAccountType("Savings");
	bankAccountController.addBalanceAccount(account) ;
	
	account.setAccountBalance(2005);
	account.setAccountHolderName("spandhana");
	account.setAccountId(3);
	account.setAccountType("Savings");
	
	account.setAccountBalance(200);
	account.setAccountHolderName("spandhu");
	account.setAccountId(3);
	account.setAccountType("Savings");
	
	
}

}

