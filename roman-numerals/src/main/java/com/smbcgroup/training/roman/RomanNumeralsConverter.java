package com.smbcgroup.training.roman;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
Java.util.hashmap;

public class RomanNumeralsConverter {
	public static BufferedReader inputReader;
	public static PrintStream output;

	public static void main(String[]args){
	
		Scanner input = new Scanner(System.in);

		System.out.println("Welcome to the Roman Numeral Converter");
		System.out.println("To insert a number, press 1. To insert a Roman Numeral, press 2.");
		int choice = input.nextInt();

		if (choice==1) {
			System.out.println("Enter number:");
			int integerValue = input.nextInt();
			toRomanNumeral(integerValue);
		}

		else {
			System.out.println("Enter Roman Numeral:");
			String romanNumeral = input.nextLine();
			toInteger(romanNumeral);
			
		}
	}
		
	public static String toRomanNumeral(Integer integerValue) {
		// given an integer integerValue

		Map<Integer, String> dictionary = new LinkedHashMap<Integer, String>();
		// < number, roman numberal >
		//private int input;
		dictionary.put(1, "I");
		dictionary.put(2, "II");
		dictionary.put(3, "III");
		dictionary.put(4, "IV");
		dictionary.put(5, "V");
		dictionary.put(6, "VI");
		dictionary.put(7, "VII");
		dictionary.put(8, "VIII");
		dictionary.put(9, "IX");
		dictionary.put(10, "X");
		dictionary.put(11, "XI");
		dictionary.put(12, "XII");
		dictionary.put(13, "XIII");
		dictionary.put(14, "XIV");
		dictionary.put(15, "XV");
		dictionary.put(16, "XVI");
		dictionary.put(17, "XVII");
		dictionary.put(18, "XVIII");
		dictionary.put(19, "XIX");
		dictionary.put(20, "XX");
		dictionary.put(30, "XXX");
		dictionary.put(40, "XL");
		dictionary.put(50, "L");
		dictionary.put(60, "LX");
		dictionary.put(70, "LXX");
		dictionary.put(80, "LXXX");
		dictionary.put(90, "XC");
		dictionary.put(100, "C");
		dictionary.put(200, "CC");
		dictionary.put(300, "CCC");
		dictionary.put(400, "CD");
		dictionary.put(500, "D");
		dictionary.put(600, "DC");
		dictionary.put(700, "DCC");
		dictionary.put(800, "DCCC");
		dictionary.put(900, "CM");
		dictionary.put(1000, "M");


		String builder;
		Integer temp;

		while (integerValue!=0) {
		
			for (Entry<Integer, String> entry : dictionary.entrySet()) {
				Integer dictNum = entry.getKey();
				String dictRN = entry.getValue();

				temp = dictNum;
				String tempRN = dictRN;

				int remainingInt;
		
				if (dictNum > integerValue) {

					//TO ROMAN NUMERAL

					builder = builder + tempRN;

					integerValue -= dictNum;

					// previous variable set at the end of each iteration
				}
			}
		}
		
		return builder;
	}
	
	public static Integer toInteger(String romanNumeral) {

		Map<Integer, Character> basicDict = new LinkedHashMap<Integer, Character>();
		// < number, roman numberal >

		basicDict.put(1, 'I');
		basicDict.put(5, 'V');
		basicDict.put(10, 'X');
		basicDict.put(50, 'L');
		basicDict.put(100, 'C');
		basicDict.put(500, 'D');
		basicDict.put(1000, 'M');

		int builder = 0;

		// going through each index/ digit of the roman numeral
		for (int i = 0; i < romanNumeral.length(); i++) {
			builder = builder + (basicDict.get(romanNumeral.charAt(i)));

		}
		return builder;
	}

}
