package com.smbcgroup.training.atm.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigDecimal;

import com.smbcgroup.training.atm.dao.txtFile.AccountAccessor;

public class ATM {
	// running main here, so creating a new instance of the ATM class
	public static void main(String[] args) throws IOException {
		new ATM(System.in, System.out).beginSession();
	}

	private static enum Action {
		login, changeAccount, checkBalance, deposit, withdraw, transfer, openNewAcct, accSummary, tranHistory;
	}
	
	    // declaring necessary objects/also calling them ????

	private BufferedReader inputReader;
	private PrintStream output;
	private String[] loggedInUserAccounts;
	private String selectedAccount;
	// initializing which enum is stored here, bc we need to log in first 

	private Action selectedAction = Action.login;

	// creating an object to input/ output to users -- essentially our reader
	private ATM(InputStream input, PrintStream output) {
		// reader for bringing content in

		this.inputReader = new BufferedReader(new InputStreamReader(input));
		this.output = output;
	}

	private void beginSession() throws IOException {
		try {
			output.println("Welcome!");
			while (true)
				triggerAction();
		}
		// prints this message to users if something goes wrong in the code

		catch (SystemExit e) {

		} catch (Exception e) {
			output.println("An unexpected error occurred.");
			e.printStackTrace();
		} finally {
			output.println("Goodbye!");
			inputReader.close();
		}
	}

	private void triggerAction() throws IOException, SystemExit {
		try {
			String input = null;
			if (promptUserInput())
				input = inputReader.readLine();
			selectedAction = performActionAndGetNextAction(input);
		} catch (ATMException e) {
			output.println(e.getMessage());
		}
	}

	private boolean promptUserInput() {
		if (selectedAction == null) {
			output.println("What would you like to do?");
			return true;
		}
		switch (selectedAction) {
		case login:
			output.println("Enter user ID: ");
			return true;
		case changeAccount:
			output.println("Enter account number: (" + String.join(", ", loggedInUserAccounts) + ")");
			return true;
		case deposit:
			output.println("Enter deposit amount: ");
		case withdraw:
			output.println("Enter withdrawal amount: ");
		case transfer:
			output.println("Enter account number, destination of funds: ");
		// TODO: prompts for other actions(?)
		default:
			return false;
		}
	}

	private Action performActionAndGetNextAction(String input) throws ATMException, SystemExit {
		if ("exit".equals(input))
			throw new SystemExit();
		if (selectedAction == null) {
			try {
				return Action.valueOf(input);
			} catch (IllegalArgumentException e) {
				throw new ATMException("Invalid command.");
			}
		}
		switch (selectedAction) {
		case login:
			try {
				loggedInUserAccounts = AccountAccessor.getUserAccounts(input);
				return Action.changeAccount;
			} catch (IOException e) {
				e.printStackTrace();
				throw new ATMException("Invalid user ID.");
			}
		case changeAccount:
			if (!input.matches("^\\d{6}$"))
				throw new ATMException("Invalid account number.");
			for (String userAccount : loggedInUserAccounts) {
				if (userAccount.equals(input)) {
					selectedAccount = input;
					return null;
				}
			}
			throw new ATMException("Account number not found.");
		case checkBalance:
			try {
				BigDecimal balance = AccountAccessor.getAccountBalance(selectedAccount);
				output.println("Balance: $" + balance);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		case deposit:
			try {
				BigDecimal depositBD = new BigDecimal(input);
				// getting the account balance to add to it 
				BigDecimal balance = AccountAccessor.getAccountBalance(selectedAccount);
				balance = balance.add(depositBD);

				//updating the account --> this function is void
				AccountAccessor.updateAccountBalance(selectedAccount, balance);

				// now show the user thier new balance 

				BigDecimal viewBalance = AccountAccessor.getAccountBalance(selectedAccount);
				output.println("Balance: $" + viewBalance);

			} catch (IOException e) {
				output.println("Something went wrong.");
			}
		case withdraw:
			try {
				BigDecimal withdrawBD = new BigDecimal(input);
				BigDecimal balance = AccountAccessor.getAccountBalance(selectedAccount);
				balance = balance.subtract(withdrawBD);

				AccountAccessor.updateAccountBalance(selectedAccount, balance);

				BigDecimal viewBalance = AccountAccessor.getAccountBalance(selectedAccount);
				output.println("Balance: $" + viewBalance);
					
				} catch (IOException e) {
					output.println("Something went wrong.");
				}
		case transfer:
			try{
					// input = where the funds will go
					// move money from selected account to the input
					// code here
			} catch (IOException e) {
					output.println("Something went wrong.");
				}
		case openNewAcct:
			try{
				//this would be once the user is already established
				// so do two things
				// 1: write the acc # in the contents of the user file
				// 2: create a new file for the account, with balance at 0 to start
			} catch (IOException e) {
				output.println("Something went wrong.");
			}
		case accSummary:
			try{
				// would need to create a hashmap of all previous transactions?
		} catch (IOException e) {
				output.println("Something went wrong.");
			}



			// transfer, openNewAcct, accSummary, tranHistory;

			break;
		// TODO: handle other actions
		}
		return null;
	}

	private static class SystemExit extends Throwable {
		private static final long serialVersionUID = 1L;
	}

	private static class ATMException extends Exception {
		private static final long serialVersionUID = 1L;

		public ATMException(String message) {
			super(message);
		}
	}

}
