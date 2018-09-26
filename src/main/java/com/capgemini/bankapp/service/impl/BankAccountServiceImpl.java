package com.capgemini.bankapp.service.impl;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.repository.BankAccountRepository;
import com.capgemini.bankapp.service.BankAccountService;
@Service
public class BankAccountServiceImpl implements BankAccountService {

	private BankAccountRepository bankAccountRepository;
	
@Autowired
	private BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
		super();
		this.bankAccountRepository = bankAccountRepository;
	}

	//public void setBankAccountRepository(BankAccountRepository bankAccountRepository) {
		//this.bankAccountRepository = bankAccountRepository;
	//}


	@Override
	public double getBalance(long accountId) throws AccountNotFoundException {
		return bankAccountRepository.getBalance(accountId);
	}

	@Override
	public double withdraw(long accountId, double amount) throws LowBalanceException, AccountNotFoundException {
		double balance = bankAccountRepository.getBalance(accountId);
		if(balance != -1) {
			if(balance - amount >= 0)
				try {
					{
						bankAccountRepository.updateBalance(accountId, balance - amount);
						return bankAccountRepository.getBalance(accountId);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			else
				throw new LowBalanceException("account balance is low");
		}
		return balance;
	}

	@Override
	public double deposit(long accountId, double amount) throws AccountNotFoundException {
		double balance = bankAccountRepository.getBalance(accountId);
		if(balance != -1)
			try {
				{
					bankAccountRepository.updateBalance(accountId, balance + amount);
					return bankAccountRepository.getBalance(accountId);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return balance;
	}

	@Override
	public boolean fundTransfer(long fromAccount, long toAccount, double amount) throws LowBalanceException, AccountNotFoundException {
		double balance = withdraw(fromAccount, amount);
		if (balance != -1) {
			if (deposit(toAccount, amount) == -1) {
				deposit(fromAccount, amount);
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean addBalanceAccount(BankAccount account) {
		
		return bankAccountRepository.addBankAccount(account);
	}

	@Override
	public BankAccount findBAnkAccountById(long accountId) {
		return bankAccountRepository.findBankAccountById(accountId);
	}

	@Override
	public List<BankAccount> findAllBankAccount() {
		
		return bankAccountRepository.findAllBankAccounts();
	}

	@Override
	public BankAccount updateBankAccount(BankAccount account) {
		return bankAccountRepository.updateBankAccount(account);
	}

	@Override
	public boolean deleteBankAccount(long accountId) {
		return bankAccountRepository.deleteBankAccount(accountId);
	}
	
}



