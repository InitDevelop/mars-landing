package com.initdevelop.marslanding.util;

import java.util.ArrayList;
import java.util.Random;

public class RandomManager extends Random {
	private static final long serialVersionUID = 1L;
	
	private int cursor;
	private ArrayList<Integer> randIntArray;
	
	public RandomManager() {
		randIntArray = new ArrayList<Integer>();
		cursor = -1;
	}
	
	public int getNextInt() {
		cursor++;
		int retVal = nextInt();
		randIntArray.add(retVal);
		return retVal;
	}
	
	public int findInt(int index) {
		if (index > cursor) {
			for (int i = cursor; i <= index; i++) {
				getNextInt();
			}
		}
		return randIntArray.get(index);
	}
	
}
