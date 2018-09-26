package com.capgemini.bankapp.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import com.capgemini.bankapp.dbutil.DbUtil;
import com.capgemini.bankapp.entities.BankAccount;

import com.capgemini.bankapp.repository.BankAccountRepository;
@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {
	
	@Autowired
	private DbUtil dbUtil ;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public double getBalance(long accountId) {
		double balance=jdbcTemplate.queryForObject("select balance from bankAccount1 where account_Id=?`", new Object[] {accountId},Double.class) ;
		return balance;
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		int count=jdbcTemplate.update("update bankAccount1 set balance=? where account_Id=?",new Object[] {accountId,newBalance}) ;
		return count!=0 ;
	}

	@Override
	public boolean addBankAccount(BankAccount account) {
		int count=jdbcTemplate.update("insert into bankAccount1 values(?,?,?)",new Object[] {account.getAccountId(),account.getAccountType(),account.getAccountBalance()}) ;
		return count!=0 ;
	}

	@Override
	public BankAccount findBankAccountById(long accountId) {
		return jdbcTemplate.queryForObject("select * from bankAccount1 where account_Id=?",new Object[] {accountId},new BankAccountRowMapper()) ;
	}

	@Override
	public List<BankAccount> findAllBankAccounts() {
		return jdbcTemplate.query("select * from bankAccount1",new Object[] {}, new BankAccountRowMapper()) ;
	}

	@Override
	public BankAccount updateBankAccount(BankAccount account) {
		int count=jdbcTemplate.update("update bankAccount1 set account_type=? where account_Id=?",new Object[] {account.getAccountType(),account.getAccountId()}) ;
		return count!=0 ?account: findBankAccountById(account.getAccountId());
	}

	@Override
	public boolean deleteBankAccount(long accountId) {
		int count=jdbcTemplate.update("delete from bankAccount1 where account_Id=?",new Object[] {accountId}) ;
		return count!=0 ;
	}
	
	private class BankAccountRowMapper implements RowMapper<BankAccount>
	{

		@Override
		public BankAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
			BankAccount account=new BankAccount() ;
			account.setAccountBalance(rs.getDouble(3)); 
			account.setAccountId(rs.getInt(1));
			account.setAccountType(rs.getString(2));
			return account;
		}
		
	}

	

	
}