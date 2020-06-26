package com.einstein.rain.util.dbconstruction.save.datahandle;

import com.jacob.serialize.serialization.field.Field;
import com.jacob.serialize.serialization.field.fieldtypes.FieldBool;
import com.jacob.serialize.serialization.field.fieldtypes.FieldByte;
import com.jacob.serialize.serialization.field.fieldtypes.FieldChar;
import com.jacob.serialize.serialization.field.fieldtypes.FieldDouble;
import com.jacob.serialize.serialization.field.fieldtypes.FieldFloat;
import com.jacob.serialize.serialization.field.fieldtypes.FieldInt;
import com.jacob.serialize.serialization.field.fieldtypes.FieldLong;
import com.jacob.serialize.serialization.field.fieldtypes.FieldShort;

public class CreateFieldType {
	
	private CreateFieldType() {
		
	}
	public Field createInt(String fieldName, int intValue) {
		Field result = new Field();
		result = new FieldInt(fieldName, intValue);
		return result;
	}

	public static Field createByte(String fieldName, byte byteValue) {

		Field result = new Field();
		result = new FieldByte(fieldName, byteValue);
		return result;
	}

	public static Field createBool(String fieldName, boolean boolValue) {

		Field result = new Field();
		result = new FieldBool(fieldName, boolValue);
		return result;
	}

	public Field createChar(String fieldName, Field fieldObjName, char charValue) {

		fieldObjName = new FieldChar(fieldName, charValue);
		return fieldObjName;
	}

	public static Field createDouble(String fieldName, double doubleValue) {

		Field result = new Field();
		result = new FieldDouble(fieldName, doubleValue);
		return result;
	}

	public static Field createFloat(String fieldName, float floatValue) {

		Field result = new Field();
		result = new FieldFloat(fieldName, floatValue);
		return result;
	}

	public static Field createShort(String fieldName, short shortValue) {

		Field result = new Field();
		result = new FieldShort(fieldName, shortValue);
		return result;
	}

	public static Field createLong(String fieldName, long longValue) {

		Field result = new Field();
		result = new FieldLong(fieldName, longValue);
		return result;
	}
}
