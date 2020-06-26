package com.einstein.rain.util.dbconstruction.save.database;

import java.util.ArrayList;
import java.util.List;
//import com.einstein.rain.entity;
import com.einstein.rain.entity.Entity;
import com.einstein.rain.entity.mob.Player;
import com.einstein.rain.level.Level;
import com.einstein.rain.util.dbconstruction.save.SaveDatabase;
import com.jacob.serialize.DataType;
import com.jacob.serialize.serialization.array.Array;

import com.jacob.serialize.serialization.array.arraytypes.ArrayString;
import com.jacob.serialize.serialization.database.DataBase;
import com.jacob.serialize.serialization.field.Field;
import com.jacob.serialize.serialization.field.fieldtypes.FieldBool;
import com.jacob.serialize.serialization.field.fieldtypes.FieldByte;
import com.jacob.serialize.serialization.field.fieldtypes.FieldChar;
import com.jacob.serialize.serialization.field.fieldtypes.FieldDouble;
import com.jacob.serialize.serialization.field.fieldtypes.FieldFloat;
import com.jacob.serialize.serialization.field.fieldtypes.FieldInt;
import com.jacob.serialize.serialization.field.fieldtypes.FieldLong;
import com.jacob.serialize.serialization.field.fieldtypes.FieldShort;
import com.jacob.serialize.serialization.object.Obj;

public class PlayerData  {

	public int xPosition;
	public int yPosition;
	public int playerModel;
	public short fuck = 100;
	public boolean fuckBool = true;
	public byte fuckByte = 1;
	public long fuckLong = 1000000;
	public double fuckDouble = 100;
	public float fuckFloat = 100.01f;
	public SaveDatabase save;
	public Obj position;
	public Obj attributes;
	//public DataBase database;

	// public Field field;
	// public Obj position;
	// public Array array;
	// public DataBase database;
	

	// private List<Attributes> attribute = new ArrayList<Attributes>();
	protected PlayerData(Level level) {
		this.xPosition = level.getPlayerAt(0).getX();
		this.yPosition = level.getPlayerAt(0).getY();
		position = new Obj("Position");
		attributes = new Obj("Attributes");
		
		System.out.println(level.getPlayerAt(0).getX());
		System.out.println(level.getPlayerAt(0).getY());
		

	}

	public void setPlayerStats() {

		// Field positionX = new FieldShort("xpos", (short) 2);
		// Field positionY = new FieldShort("ypos", (short) 43);

		// Position object
		//Save.createIntField("zPos", 150);
		//Save.createIntField("xPos", xPosition);
		//Save.createIntField("yPos", yPosition);
		Field field = new FieldInt(null, playerModel);
		position.addField(createIntField("yPosition", yPosition));
		position.addField(createIntField("xPos", xPosition));
		position.addField(createIntField("zPos", 150));

		//attributes.addField(Save.createShortField("ShortField", fuck));
		//attributes.addField(Save.createBoolField("BoolField", fuckBool));
		//attributes.addField(Save.createByteField("ByteField", fuckByte));
		attributes.addField(createLongField("ByteField", fuckLong));
		attributes.addField(createDoubleField("ByteField", fuckDouble));
		attributes.addField(createFloatField("FloatField", fuckFloat));
		
		database.addObject(position);
		database.addObject(attributes);
		
		//byte[] stream = new byte[database.getDBSize()];
		//----------------
		//this must run
		//database.getDBBytes(stream, 0);
		//before this to build the initial structure
	//	database.dbPointers();
		//--------------
		

		// System.out.println(this.xPosition + " " + this.yPosition);
	}
	public void getPlayerInventory() {
		
	}
	public void getPlayerAttributes() {
		
	}
}