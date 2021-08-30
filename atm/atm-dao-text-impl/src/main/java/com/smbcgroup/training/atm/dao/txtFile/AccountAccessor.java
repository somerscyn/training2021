package com.smbcgroup.training.atm.dao.txtFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class AccountAccessor {

	static String dataLocation = "../atm-dao-text-impl/data/";

	public static String[] getUserAccounts(String userId) throws IOException {
		return resourceToString(dataLocation + "users/" + userId).split(",");
	}

	public static BigDecimal getAccountBalance(String accountNumber) throws IOException {
		return new BigDecimal(resourceToString(dataLocation + "accounts/" + accountNumber));
	}

	public static void updateAccountBalance(String accountNumber, BigDecimal balance) throws IOException {
		writeStringToFile(dataLocation + "accounts/" + accountNumber, balance.toPlainString());
	}
	
	public static void createNewAccount(String accountNumber, String balance) throws IOException {
		File newAcctFile = new File(dataLocation + "accounts/" + accountNumber + ".txt");
		FileWriter fw = new FileWriter(newAcctFile, true);
		fw.write(balance);
		fw.close();
	}
	
	public static void addNewAccountToUserID(String userId, String accountNumber) throws IOException {
		FileWriter fw = new FileWriter(dataLocation + "users/" + userId + ".txt", true);
		fw.write("," + accountNumber);
		fw.close();
		
	}

	private static String resourceToString(String fileName) throws IOException {
		return Files.readString(Path.of(fileName + ".txt"));
	}

	private static void writeStringToFile(String fileName, String newContents) throws IOException {
		Files.writeString(Path.of(fileName + ".txt"), newContents);
	}
	
	public static void main(String args[]) throws IOException {
		//System.out.println(getUserAccounts("rdelaney")[1]);
		//System.out.println(getAccountBalance("222333"));
		//Random randomAccountGenerator = new Random();
		//int newAcctNumber = randomAccountGenerator.nextInt(999999);
		//System.out.println(newAcctNumber);
		
		
		//File newAcctFile = new File(dataLocation + "accounts/" + 444444 + ".txt");
		//FileWriter fw = new FileWriter(newAcctFile, true);
		//fw.write("100.00");
		//System.out.println("Successfully wrote to the file.");
		//fw.close();
		//System.out.println(getAccountBalance("444444"));
		
		
		
	}

}
