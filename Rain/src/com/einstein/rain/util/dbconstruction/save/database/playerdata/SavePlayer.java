package com.einstein.rain.util.dbconstruction.save.database.playerdata;

import com.einstein.rain.level.Level;
import com.einstein.rain.level.TileCoordinate;
import com.einstein.rain.util.dbconstruction.save.datahandle.CreateFieldType;
import com.jacob.serialize.serialization.database.DataBase;
import com.jacob.serialize.serialization.field.Field;
import com.jacob.serialize.serialization.field.fieldtypes.FieldInt;
import com.jacob.serialize.serialization.object.Obj;

public class SavePlayer {
	public int xPosition;
	public int yPosition;
	public Level level;
	public TileCoordinate spawn;
	public static CreateFieldType createField;
	public Obj object;
	public Field field;
	public DataBase database;
	public SavePlayer(Level level, DataBase database) { 
		
		this.level = level;
		this.xPosition = level.getPlayerAt(0).getX();
		this.yPosition = level.getPlayerAt(0).getY();
		
		this.database = database;
	}
	public DataBase savePlayer(DataBase database) {
		//savePlayerLocation(database);
		database.addObject(savePlayerLocation(database));
		return database;
		
	}
	public Obj savePlayerLocation(DataBase database) {
		
		Obj playerPosition = new Obj("PlayerPosition");
		
		playerPosition.addField(new FieldInt("PlayerPositionY", yPosition));
		playerPosition.addField(new FieldInt("PlayerPositionX", xPosition));
		playerPosition.addField(new FieldInt("PoSZ", 0));

		return playerPosition; 
	}
}
