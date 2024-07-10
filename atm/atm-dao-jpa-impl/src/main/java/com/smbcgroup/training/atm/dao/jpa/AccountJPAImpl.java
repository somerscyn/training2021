package com.smbcgroup.training.atm.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.smbcgroup.training.atm.Account;
import com.smbcgroup.training.atm.User;
import com.smbcgroup.training.atm.dao.AccountDAO;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.dao.UserNotFoundException;

public class AccountJPAImpl implements AccountDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("derby-entities");

	@Override
	public User getUser(String userId) throws UserNotFoundException {
		EntityManager em = emf.createEntityManager();
		try {
			UserEntity entity = em.find(UserEntity.class, userId);
			if (entity == null)
				throw new UserNotFoundException();
			return entity.convertToUser();
		} finally {
			em.close();
		}
	}

	@Override
	public Account getAccount(String accountNumber) throws AccountNotFoundException {
		EntityManager em = emf.createEntityManager();
		try {
			AccountEntity entity = em.find(AccountEntity.class, accountNumber);
			if (entity == null)
				throw new AccountNotFoundException();

			// convert from account entity to account
		new Account ();
		Account placeholder = new Account();

		placeholder.setAccountNumber(accountNumber);
		placeholder.setBalance(entity.getBalance());

			
			System.out.println("Balance: " + entity.getBalance());
			return placeholder;
		} finally {
			em.close();
		}
		
	}

	@Override
	public void updateAccount(Account account) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			AccountEntity entity = new AccountEntity();
			entity.setAccountNumber(account.getAccountNumber());
			entity.setBalance(account.getBalance());
			em.merge(entity);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@Override
	public void saveAccount(Account account) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			AccountEntity entity = new AccountEntity();
			entity.setAccountNumber(account.getAccountNumber());
			entity.setBalance(account.getBalance());
			em.merge(entity);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		throw new UnsupportedOperationException("Unimplemented method 'saveAccount'");
	}

	@Override
	public Collection<Account> getUserAccounts(String userId) {
		// EntityManager em = emf.createEntityManager();
		// em.getTransaction().begin();

		// Collection<Account> collections;
		

		// try {
		// 	AccountEntity entity = new AccountEntity();
		// 	entity.getAccountNumber();


		// } finally {
		// 	em.close();
		// }

		// List<String> listNums = new ArrayList<String>();



		// // TODO 
		// // list vs collection vs arraylist 
		// // this function is typed in 

		// //given the userID
		throw new UnsupportedOperationException("Unimplemented method 'getUserAccounts'");
	}

	@Override
	public void saveUserAccounts(String userID, Collection<Account> accounts) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'saveUserAccounts'");
	}


}
