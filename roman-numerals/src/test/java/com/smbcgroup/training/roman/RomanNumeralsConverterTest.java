package com.smbcgroup.training.roman;

import static org.junit.Assert.*;

import org.junit.Test;

public class RomanNumeralsConverterTest {
	
	RomanNumeralsConverter converter = new RomanNumeralsConverter();

	@Test
	public void test1ToI() {
		assertEquals("I", converter.toRomanNumeral(1));
	}

	public void testNumtoRN(){
		assertEquals(10, 'X');

	}

}
