package com.einstein.rain.util;

import java.awt.Color;
import java.util.Random;

public class Colors {
	
	private int red;
	private int green;
	private int blue;

	public Colors() {
//		this.red = red;
//		this.blue = blue;
//		this.green = green;
	}
	public int fireColors() {
		//Color randomColor = new Color(red, green, blue);
		int[] colors = {0xFF0000, 0xFF5D00, 0xFFC700};
		int random =0;
		Random randomColor = new Random();
		for(int i = 0; i < colors.length; i++) {
			random = colors[randomColor.nextInt(colors.length)];
		}
		//int random = randomColor.nextInt(0xffFFFF00 - 0xffFFEE00 ) + 0xffFFEE00;
		return	random;
	}
	public int waterColors() {
		//Color randomColor = new Color(red, green, blue);
		int[] colors = {0x1668FF, 0x19B2FF, 0x1D00FF};
		int random = 0;
		Random randomColor = new Random();
		for(int i = 0; i < colors.length; i++) {
			random = colors[randomColor.nextInt(colors.length)];
		}
		return	random;
	}
}
