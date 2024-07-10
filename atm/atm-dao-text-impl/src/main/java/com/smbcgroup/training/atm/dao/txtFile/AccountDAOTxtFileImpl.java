package com.smbcgroup.training.atm.dao.txtFile;

import java.io.IOException;
import java.util.Collection;

import com.smbcgroup.training.atm.Account;
import com.smbcgroup.training.atm.User;
import com.smbcgroup.training.atm.dao.AccountDAO;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.dao.UserNotFoundException;

public class AccountDAOTxtFileImpl implements AccountDAO {

	@Override
	public User getUser(String userId) throws UserNotFoundException {
		User user = new User();
		user.setUserId(userId);
		try {
			user.setAccounts(AccountAccessor.getUserAccounts(userId));
		} catch (IOException e) {
			throw new UserNotFoundException("User file not found", e);
		}
		return user;
	}

	@Override
	public Account getAccount(String accountNumber) throws AccountNotFoundException {
		try {
			Account account = new Account();
			account.setAccountNumber(accountNumber);
			account.setBalance(AccountAccessor.getAccountBalance(accountNumber));
			return account;
		} catch (IOException e){
			throw new AccountNotFoundException("Account file not found", e);}
		}

	@Override
	public void updateAccount(Account account) throws AccountNotFoundException {
		try {
			AccountAccessor.updateAccountBalance(account.getAccountNumber(), account.getBalance());
		} catch (IOException e) {
			throw new AccountNotFoundException("Account file not found", e);
		}
	}

	// public void createAccount(String userId, String accountNumber) throws AccountNotFoundException{
	// 	try{

	// 		String [] allAccts = AccountAccessor.getUserAccounts(userId);

	// 		for (String acct: allAccts){
	// 			if (acct.big > allAccts[i -1]){

	// 			}

	// 		}
	// 		String lastAcc = loggedInUserAccounts[loggedInUserAccounts.length-1];
	// 				output.println(lastAcc);

	// 				int lastAccInt = Integer.parseInt(lastAcc);
	// 				int newAccNum = lastAccInt + 1;

	// 				String newAcc = Integer.toString(newAccNum);

		
	// 	}
	// }

	@Override
	public void saveAccount(Account account) {
		try {
			account.setBalance(AccountAccessor.getAccountBalance(account.getAccountNumber()));
	
		} catch (IOException e){
			throw new UnsupportedOperationException("Unimplemented method 'saveAccount'");		}
		
	}

	@Override
	public Collection<Account> getUserAccounts(String userId) {
		try {
			String[]accountsAll = AccountAccessor.getUserAccounts(userId);
			///Collection<Account> = accountsAll.length;

			// public static String[] getUserAccounts(String userId) throws IOException {
			// 	return resourceToString(dataLocation + "users/" + userId).split(",");
			// }

			// need to change from arraylist to array
			return null;
		} 
		
		catch (IOException e){
		
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getUserAccounts'");}
	}

	@Override
	public void saveUserAccounts(String userID, Collection<Account> accounts) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'saveUserAccounts'");
	}

}
