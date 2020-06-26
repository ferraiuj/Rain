package com.einstein.rain.util.dbconstruction.save.database.playerdata;

import com.einstein.rain.util.dbconstruction.load.datahandle.SearchFields;
import com.einstein.rain.util.dbconstruction.save.Save;
import com.einstein.rain.util.dbconstruction.save.SaveDatabase;
import com.einstein.rain.util.dbconstruction.save.datahandle.CreateFieldType;
import com.jacob.serialize.deserialization.ddatabase.DDataBase;
import com.jacob.serialize.serialization.database.DataBase;
import com.jacob.serialize.serialization.field.Field;
import com.jacob.serialize.serialization.field.fieldtypes.FieldInt;
import com.jacob.serialize.serialization.object.Obj;
import com.einstein.rain.level.Level;
import com.einstein.rain.level.TileCoordinate;
import com.einstein.rain.util.dbconstruction.load.Load;

public class SaveLocation extends Save{
	public int xPosition;
	public int yPosition;
	public Level level;
	public TileCoordinate spawn;
	public static CreateFieldType createField;
	public Obj object;
	public Field field;
	//public DataBase database;

	public SaveLocation(Level level) {
		super(level);
		this.xPosition = level.getPlayerAt(0).getX();
		this.yPosition = level.getPlayerAt(0).getY();
		//savePlayerLocation();
	}

	public DataBase savePlayerLocation(DataBase database) {
		
		Obj playerPosition = new Obj("PlayerPosition");
		
		playerPosition.addField(new FieldInt("PlayerPositionY", yPosition));
		playerPosition.addField(new FieldInt("PlayerPositionX", xPosition));
		playerPosition.addField(new FieldInt("PoSZ", 0));
		database.addObject(playerPosition);
		return database;
	}
}
