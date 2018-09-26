package com.capgemini.bankapp.repository;



import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import com.capgemini.bankapp.entities.BankAccount;

public interface BankAccountRepository {
	double getBalance(long accountId) throws AccountNotFoundException;
	public boolean updateBalance(long accountId, double newBalance);
	public boolean addBankAccount(BankAccount account);
	public BankAccount findBankAccountById(long accountId);
	public List<BankAccount> findAllBankAccounts();
	public BankAccount  updateBankAccount(BankAccount account);
	public boolean deleteBankAccount(long accountId);
	
}
