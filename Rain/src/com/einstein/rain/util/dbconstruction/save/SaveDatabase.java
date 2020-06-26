package com.einstein.rain.util.dbconstruction.save;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.einstein.rain.level.Level;
import com.einstein.rain.util.dbconstruction.load.ddatabases.LoadLocation;
import com.einstein.rain.util.dbconstruction.save.database.playerdata.SaveLocation;
import com.einstein.rain.util.dbconstruction.save.database.playerdata.SavePlayer;
import com.einstein.rain.util.dbconstruction.save.database.playerdata.SaveSettings;
import com.jacob.serialize.DataType;
import com.jacob.serialize.deserialization.dfield.DField;
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

public class SaveDatabase {

	public byte byteValue;
	public boolean boolValue;
	public char charValue;
	public double doubleValue;
	public float floatValue;
	public int intValue;
	public long longValue;
	public short shortValue;
	public DataBase database;
	public SaveLocation location;
	public SaveSettings settings;
	public SavePlayer player;
	public Object object;
	public Field field;
	public Level level;

	protected SaveDatabase(Level level, DataBase database) { 

		this.level = level;
//		location = new SaveLocation(level);
//		settings = new SaveSettings();
//		player = new SavePlayer(level);

		// saveLocations(LOCATIONS_FILE);
		// savePlayerData(PLAYER_DATA_FILE);
		// saveSettings(SETTINGS_FILE);
		// saveUIDetails(UIDETAILS_FILE);
		// saveLevelData(LEVEL_DATA_FILE);

	}

	// BELOW IS WHERE WE CAN MAKE CHANGES TO HOW AND WHICH FILES LOAD
	// we could make all these DataBase object returns one but i want to keep it
	// like this for now in case i want to change how individual save data is
	// saved
	

	// public void createIntField(String fieldName, int value, Field
	// fieldObjName) {
	//
	// fieldObjName = new FieldInt(fieldName, (int) value);
	// byte[] stream = new byte[fieldObjName.getDBSize()];
	// saveToFile("playerData.save",);
	// }

	public static Field createIntField(String fieldName, int intValue) {
		Field result = new Field();
		result = new FieldInt(fieldName, intValue);
		return result;
	}

	public static Field createByteField(String fieldName, byte byteValue) {

		Field result = new Field();
		result = new FieldByte(fieldName, byteValue);
		return result;
	}

	public static Field createBoolField(String fieldName, boolean boolValue) {

		Field result = new Field();
		result = new FieldBool(fieldName, boolValue);
		return result;
	}

	public Field createCharField(String fieldName, Field fieldObjName, char charValue) {

		fieldObjName = new FieldChar(fieldName, charValue);
		return fieldObjName;
	}

	public static Field createDoubleField(String fieldName, double doubleValue) {

		Field result = new Field();
		result = new FieldDouble(fieldName, doubleValue);
		return result;
	}

	public static Field createFloatField(String fieldName, float floatValue) {

		Field result = new Field();
		result = new FieldFloat(fieldName, floatValue);
		return result;
	}

	public static Field createShortField(String fieldName, short shortValue) {

		Field result = new Field();
		result = new FieldShort(fieldName, shortValue);
		return result;
	}

	public static Field createLongField(String fieldName, long longValue) {

		Field result = new Field();
		result = new FieldLong(fieldName, longValue);
		return result;
	}

	public void createArray() {

	}

	public void createObject() {

	}

	public void createDatabase() {

	}

	public void destroyField() {

	}

	public void destroyArray() {

	}

	public void destroyObject() {

	}

	public void destroyDatabase() {
	}

	

	static void printBytesHex(byte[] data) {

		for (int i = 0; i < data.length; i++) {
			System.out.printf("0x%x ", data[i]);
		}
	}

}
