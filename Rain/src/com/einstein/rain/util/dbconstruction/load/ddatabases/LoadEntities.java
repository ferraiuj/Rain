package com.einstein.rain.util.dbconstruction.load.ddatabases;

import com.einstein.rain.entity.mob.Mouset;
import com.einstein.rain.entity.mob.Player;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.input.Keyboard;
import com.einstein.rain.level.Level;
import com.einstein.rain.level.TileCoordinate;
import com.einstein.rain.util.dbconstruction.load.datahandle.SearchFields;
import com.jacob.serialize.deserialization.ddatabase.DDataBase;

public class LoadEntities {

	public DDataBase database;

	public TileCoordinate spawn;
	public short playerY = 0;
	public short playerX = 1;
	public short playerZ = 2;
	public short pointer = 0;
	public Player player;
	public Mouset mouset;
	public Keyboard key;
	public Level level;
	public SearchFields searchFields;
	
	public LoadEntities(String dbname, Level level, Keyboard key, Screen screen) {
		this.key = key;
		this.level = level;
		searchFields = new SearchFields(dbname);
		
		spawnMouset(playerX, playerY, screen);
		spawnPlayer(playerX, playerY);
		
	}
	public Player spawnPlayer(short playerX, short playerY) {
		
		int xPos = searchFields.getIntField(playerX);
		int yPos = searchFields.getIntField(playerY);
		TileCoordinate spawn = new TileCoordinate((xPos / 16), (yPos / 16));
		player = new Player("Jacob", spawn.x(), spawn.y(), key, mouset);
		level.add(player);
		return player;
	}
	public Mouset spawnMouset(short mouseX, short mouseY, Screen screen) {
		int xPos = searchFields.getIntField(mouseX);
		int yPos = searchFields.getIntField(mouseY);
		mouset = new Mouset(xPos, yPos, screen);
		level.add(mouset);
		return mouset;
	}
}
