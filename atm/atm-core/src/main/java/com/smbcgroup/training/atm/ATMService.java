package com.smbcgroup.training.atm;

import java.math.BigDecimal;
import java.util.Collection;

import com.smbcgroup.training.atm.dao.AccountDAO;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.dao.UserNotFoundException;

public class ATMService {

	private AccountDAO dao;

	private Account selectedAccount;
	private User loggedInUserAccounts;

	public ATMService(AccountDAO accountDao) {
		this.dao = accountDao;
	}

	public User getUser(String userId) throws UserNotFoundException {
		return dao.getUser(userId);
	}

	public Account getAccount(String accountNumber) throws AccountNotFoundException {
		return dao.getAccount(accountNumber);
	}

	public void deposit (String accountNumber, BigDecimal amount) throws AccountNotFoundException{
		Account account = dao.getAccount(accountNumber);
		BigDecimal currentBalance = account.getBalance();
		currentBalance = currentBalance.add(amount);
		
		account.setBalance(currentBalance);
		dao.updateAccount(account);
	}

	public void withdraw(String accountNumber, BigDecimal amount) throws AccountNotFoundException{
		Account account = dao.getAccount(accountNumber);
		BigDecimal currentBalance = account.getBalance();
		currentBalance = currentBalance.subtract(amount);
		account.setBalance(currentBalance);

		dao.updateAccount(account);
	}

	public void transfer(String origin, String destination, BigDecimal amount) throws AccountNotFoundException{
		withdraw(origin, amount);
		deposit(destination, amount);
		//update account is called within the previous two functions
	}

	public void createUser(/*.... */){

		//TODO
	}

	public Collection<Account> getUserAccounts(String userId){
		//userId.getAcc

		//TODO
		return null;
	}

	public void addUserAccount(String userId, Account account){
		//TODO
	}


	
}
