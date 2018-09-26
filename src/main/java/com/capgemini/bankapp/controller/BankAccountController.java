package com.capgemini.bankapp.controller;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.service.BankAccountService;
@Controller
public class BankAccountController {
	private BankAccountService bankAccountService;
	@Autowired
	private BankAccountController(BankAccountService bankAccountService) {
		super();
		this.bankAccountService = bankAccountService;
	}
	//public void setBankAccountService(BankAccountService bankAccountService) {
		//this.bankAccountService = bankAccountService;
	//}
	public double getBalance(long accountId) throws AccountNotFoundException {
		return bankAccountService.getBalance(accountId);
	}
	public double withdraw(long accountId,double amount)throws LowBalanceException, AccountNotFoundException{
		return bankAccountService.withdraw(accountId, amount);
	}
	public double deposit(long accountId, double amount) throws AccountNotFoundException {
	return bankAccountService.deposit(accountId, amount);
	}
	public boolean fundTransfer(long fromAccount, long toAccount, double amount) throws LowBalanceException, AccountNotFoundException {
		return bankAccountService.fundTransfer(fromAccount, toAccount, amount);
	}
	
	public boolean addBalanceAccount(BankAccount account) {
		
		return bankAccountService.addBalanceAccount(account);
	}

	public BankAccount findBAnkAccountById(long accountId) {
		return bankAccountService.findBAnkAccountById(accountId);
	}

	public List<BankAccount> findAllBankAccount() {
		
		return bankAccountService.findAllBankAccount();
	}

	public BankAccount updateBankAccount(BankAccount account) {
		return bankAccountService.updateBankAccount(account);
	}

	public boolean deleteBankAccount(long accountId) {
		return bankAccountService.deleteBankAccount(accountId);
	}
}
