package com.einstein.rain.util;

import com.einstein.rain.Game;
import com.einstein.rain.input.Mouse;

public class MathUtil {

	private enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}

	public MathUtil() {

	}

	public static int min(int value, int min) {
		return value < min ? min : value;
	}

	public static int max(int value, int max) {
		return value > max ? max : value;
	}

	public static int clamp(int value, int min, int max) {
		if (value < min) {
			return min;
		} else if (value > max) {
			return max;
		} else
			return value;
	}
	public double getRadian(double getX, double getY) {
		double getRadian;
		getRadian = Math.atan2(getY, getX);
		return getRadian;
	}
	public double getSlopeX(double xOne, double xTwo) {
		double slopeX;
		slopeX = xTwo - xOne;
		//System.out.println("getX = " + Mouse.getX() + "getY = " + Mouse.getY() + "getRadian = " + getRadian);
		return slopeX;
	}
	public double getSlopeY(double yOne, double yTwo ) {
		double slopeY;
		slopeY = yTwo - yOne;
		//System.out.println("getX = " + Mouse.getX() + "getY = " + Mouse.getY() + "getRadian = " + getRadian);
		return slopeY;
	}

}
