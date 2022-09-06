package com.initdevelop.marslanding.game;

import java.util.Date;

public class IO {

	public static Date date;
	
	public static void print(Object obj) {
		date = new Date();
		System.out.println("[GAME OUTPUT] [" + IO.date.toString() + "] " + String.valueOf(obj));
	}
	
}
