package com.einstein.rain.util.dbconstruction.load.ddatabases;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.einstein.rain.graphics.Screen;
import com.einstein.rain.input.Keyboard;
import com.einstein.rain.util.dbconstruction.load.Load;
import com.einstein.rain.util.dbconstruction.load.datahandle.SearchFields;

public class LoadSettings{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchFields fields;

	public Keyboard key;
	public Screen screen;
	public Dimension size;

	public short width = 0;
	public short height = 1;
	private static final int SCALE = 3;

	public LoadSettings(String dbname) { 

		fields = new SearchFields(dbname);
	
		
		findScreenRes(width, height);
		findScreenSize(width, height);
		findKeys();
	}

	public Dimension findScreenRes(short width, short height) {
	 int widthh = 300;// 300
		int heightt = 168;//
		int pixelsWidth = fields.getIntField(width);
		int pixelsHeight = fields.getIntField(height);
		return size = new Dimension(widthh * SCALE, heightt * SCALE);// settings
	}

	public Screen findScreenSize(short width, short height) {
		 int widthh = 300;// 300
			int heightt = 168;//
		int wid = fields.getIntField(width);
		int hei = fields.getIntField(height);
		return screen = new Screen(widthh, heightt);// settings
	}
	public Keyboard findKeys() {
		
		return key = new Keyboard();// settings
	}
	
	public int[] loadScreen(BufferedImage image) {
		int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		return pixels;
	}
}
