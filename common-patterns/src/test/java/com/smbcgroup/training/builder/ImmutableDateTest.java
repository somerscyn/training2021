package com.smbcgroup.training.builder;

import static org.junit.Assert.*;

import org.junit.Test;

public class ImmutableDateTest {

	@Test
	public void test2021_8_26_16_45_59_999() {
		assertEquals("2021-8-26 16:45:59:999", new ImmutableDate(2021, 8, 26, 16, 45, 59, 999).toString());
	}

	@Test
	public void test2021_8_26_00_00_00_000() {
		ImmutableDate builder = new ImmutableDate.Builder().year(2021).month(8).day(26).build();
		// builder.year(2021);
		// builder.month(8);
		// builder.day(26);
		
		assertEquals("2021-8-26 0:0:0:0", builder.toString());


	//	assertEquals("2021-8-26 0:0:0:0", new ImmutableDate(2021, 8, 26, 0, 0, 0, 0).toString());
	
	}

}
