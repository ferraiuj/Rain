package com.einstein.rain.util.dbconstruction.load.ddatabases;

import com.einstein.rain.level.Level;
import com.einstein.rain.level.TileCoordinate;
import com.einstein.rain.util.dbconstruction.load.datahandle.SearchFields;
import com.jacob.serialize.deserialization.ddatabase.DDataBase;

public class LoadLocation {

	public DDataBase database;

	public TileCoordinate spawn;
	public short playerY = 0;
	public short playerX = 1;
	public short playerZ = 2;
	public short pointer = 0;
	public SearchFields searchFields;

	public LoadLocation(String dbname, Level level) {
		
		searchFields = new SearchFields(dbname);
		spawnPlayer(playerX, playerY);
	}
	public TileCoordinate spawnPlayer(short playerX, short playerY) {
		
	int xPos = searchFields.getIntField(playerX);
	int yPos = searchFields.getIntField(playerY);
		//int xPos = 300;
		//int yPos = 300;
		return spawn = new TileCoordinate(xPos / 16, yPos / 16);
	}
}
