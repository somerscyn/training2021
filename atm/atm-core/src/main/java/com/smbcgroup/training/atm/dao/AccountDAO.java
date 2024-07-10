package com.smbcgroup.training.atm.dao;

import java.util.Collection;

import com.smbcgroup.training.atm.Account;
import com.smbcgroup.training.atm.User;

public interface AccountDAO {

	public User getUser(String userId) throws UserNotFoundException;

	public Account getAccount(String accountNumber) throws AccountNotFoundException;

	public void saveAccount(Account account);

	public Collection<Account> getUserAccounts(String userId);

	public void saveUserAccounts(String userID, Collection<Account> accounts);

	public void updateAccount(Account account) throws AccountNotFoundException;

}
