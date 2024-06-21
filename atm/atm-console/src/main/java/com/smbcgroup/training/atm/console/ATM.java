package com.smbcgroup.training.atm.console;

import java.io.BufferedReader;
import java.io.FileWriter;
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
	

	private String loggedInUserID;
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
			return true;
		case withdraw:
			output.println("Enter withdrawal amount: ");
			return true;
		case transfer:
			output.println("Enter account number for destination of funds, the origin of funds, and transfer amount. Seperate them by commas: ");
			//String[ ] elements = input.split(",");
			return true;
		default:
			return false;
		}
	}

	private Action performActionAndGetNextAction(String input) throws ATMException, SystemExit {
		if ("exit".equals(input)) {
			throw new SystemExit(); }
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
					loggedInUserID = input;
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
				break;
			case deposit:
				try {
					BigDecimal depositBD = new BigDecimal(input);
					// getting the account balance to add to it
					BigDecimal balance = AccountAccessor.getAccountBalance(selectedAccount);
					balance = balance.add(depositBD);

					//updating the account --> this function is void
					AccountAccessor.updateAccountBalance(selectedAccount, balance);

					// now show the user their new balance

					BigDecimal viewBalance = AccountAccessor.getAccountBalance(selectedAccount);
					output.println("Balance: $" + viewBalance);

				} catch (IOException e) {
					output.println("Something went wrong.");
				}
				break;
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
					break;
			case transfer:
				try{
					String[ ] elements = input.split(",");
					String accountDestination = elements[0];
					String accountOrigin = elements[1];
					String fundsAmount = elements [2];
					BigDecimal fundsBD = new BigDecimal(fundsAmount);

					//accessing account origin and withdrawing the funds
					BigDecimal balance = AccountAccessor.getAccountBalance(accountOrigin);
					balance = balance.subtract(fundsBD);
					AccountAccessor.updateAccountBalance(accountOrigin, balance);

					//accessing account destination and depositing funds
					BigDecimal balance2 = AccountAccessor.getAccountBalance(accountDestination);
					balance2 = balance2.add(fundsBD);
					AccountAccessor.updateAccountBalance(accountDestination, balance);

					//output new balances for both accounts
					BigDecimal newBal1 = AccountAccessor.getAccountBalance(accountOrigin);
					output.println("Balance for account " + accountOrigin + " is " + newBal1);

					BigDecimal newBal2 = AccountAccessor.getAccountBalance(accountDestination);
					output.println("Balance for account " + accountDestination + " is " + newBal2);
					

				} catch (IOException e) {
						output.println("Something went wrong.");
					}
				break;
			case openNewAcct:
				try {
					String lastAcc = loggedInUserAccounts[loggedInUserAccounts.length-1];
					output.println(lastAcc);
					// parse to an integer, add 1, then turn back to a string

					int lastAccInt = Integer.parseInt(lastAcc);
					int newAccNum = lastAccInt + 1;

					// parsing back to have right type for argument for next function
					String newAcc = Integer.toString(newAccNum);

					AccountAccessor.writeStringToFile(loggedInUserID, newAcc);
					// first argument is the file name, and the file name should be the user id

					// creating a new file with this account num, balance = 0
					try {
						FileWriter myWriter = new FileWriter( newAcc + ".txt");
						myWriter.write("0");
						myWriter.close();
						} catch (IOException e) {
							System.out.println("An error occurred.");
							e.printStackTrace();
						}
					}
					catch (IOException e) {
						output.println("Something went wrong.");
					}

					break;
				}



			return null;
			}
		// end of public class ATM

		static class SystemExit extends Throwable {
		private static final long serialVersionUID = 1L;
	}

	static class ATMException extends Exception {
		private static final long serialVersionUID = 1L;

		public ATMException(String message) {
			super(message);
		}
	}

}
