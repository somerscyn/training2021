package com.smbcgroup.training.builder;

public class ImmutableDate {

	private int year = 1979;
	private int month;
	private int day = 1;
	private int hour;
	private int minute;
	private int second;
	private int millisecond;

	private ImmutableDate(ImmutableDateBuilder builder){
		this.year = builder.year;
		this.month = builder.month;
		this.day = builder.day;
		this.hour = builder.hour;
		this.minute = builder.minute;
		this.second = builder.second;
		this.millisecond = builder.millisecond;
		
	}


	public ImmutableDate(int i, int j, int k, int l, int m, int n, int o) {


		//TODO Auto-generated constructor stub
	}


	public int getYear(){
		return year;
	}

	public int getMonth(){
		return month;
	}

	public int getDay(){
		return day;
	}

	public int getHour(){
		return hour;
	}

	public int getMinute(){
		return minute;
	}

	public int getMillisecond(){
		return millisecond;
	}

	@Override
	public String toString() {
		return String.format("%d-%d-%d %d:%d:%d:%d", year, month, day, hour, minute, second, millisecond);
	}

	public static class Builder{
		private int year;
		private int month;
		private int day;
		private int hour;
		private int minute;
		private int second;
		private int millisecond;

		public Builder year(int year) {
			this.year = year;
			return this;
		}
		public Builder month(int month) {
			this.month = month;
			return this;
		}
		public Builder day(int day) {
			this.day = day;
			return this;
		}
		public Builder hour(int hour) {
			this.hour = hour;
			return this;
		}
		public Builder minute(int minute) {
			this.minute = minute;
			return this;
		}
		public Builder second(int second) {
			this.second = second;
			return this;
		}
		public Builder millisecond(int millisecond) {
			this.millisecond = millisecond;
			
		}

		public ImmutableDate build(){
			return new ImmutableDate(year, month, day, hour, minute, second, millisecond);
		}

		

		}

		public Builder(int year, int month, int day, int hour, int minute, int second, int millisecond) {
			this.year = year;
			this.month = month;
			this.day = day;
			this.hour = hour;
			this.minute = minute;
			this.second = second;
			this.millisecond = millisecond;
		}

		public ImmutableDate build(){
			ImmutableDate immDate = new ImmutableDate(this);
			return immDate;

		}

	}

}
