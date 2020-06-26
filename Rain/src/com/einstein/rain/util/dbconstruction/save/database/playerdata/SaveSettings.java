package com.einstein.rain.util.dbconstruction.save.database.playerdata;

import java.awt.Dimension;

import com.einstein.rain.graphics.Screen;
import com.einstein.rain.input.Keyboard;
import com.jacob.serialize.serialization.database.DataBase;
import com.jacob.serialize.serialization.field.fieldtypes.FieldInt;
import com.jacob.serialize.serialization.object.Obj;

public class SaveSettings {

	private static int width = 300;
	private static int height = 168;
	private static int scale = 3;

	public SaveSettings(DataBase database) {
		database.addObject(findScreenRes());
		database.addObject(findScreenSize());
		//database.addObject(findKeys());
	}

	public Obj findScreenRes() {

		Obj screenRes = new Obj("ScreenRes");

		screenRes.addField(new FieldInt("Width", width ));
		screenRes.addField(new FieldInt("Height", height));// settings
		return screenRes;
	}
	
	public Obj findScreenSize() {
		Obj screensize = new Obj("ScreenSize");

		screensize.addField(new FieldInt("Width", width  * scale));
		screensize.addField(new FieldInt("Height", height  * scale));// settings
		return screensize;
	}

	public Obj findKeys() {
//		Obj screensize = new Obj("ScreenSize");
//
//		screensize.addField(new FieldInt("Width", width));
//		screensize.addField(new FieldInt("Height", height));// settings
//		return screensize;
		return key = new Keyboard();// settings
	}
}
