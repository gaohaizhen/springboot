package cn.demo.day1;

import java.util.Date;

public class EnumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WeekDay weekDay2 = WeekDay.FRI;
        // System.out.println(weekDay2);
        // System.out.println(weekDay2.name());
        System.out.println(weekDay2.ordinal());
        // System.out.println(WeekDay.valueOf("SUN").toString());
        // System.out.println(WeekDay.values().length);
		
		new Date(300){};
	}

	public enum WeekDay{
		SUN(1),MON(),TUE,WED,THI,FRI,SAT;
		private WeekDay(){System.out.println("first");}
		private WeekDay(int day){System.out.println("second");}
	}
	
}
