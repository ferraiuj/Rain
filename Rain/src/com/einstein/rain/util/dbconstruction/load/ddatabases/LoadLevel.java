package com.einstein.rain.util.dbconstruction.load.ddatabases;

import com.einstein.rain.entity.mob.Player;
import com.einstein.rain.graphics.CameraControl;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.input.Mouse;
import com.einstein.rain.level.Level;
import com.einstein.rain.level.SpawnLevel;
import com.einstein.rain.util.dbconstruction.load.datahandle.SearchFields;

public class LoadLevel {
	public Level level;
	public Player player;
	public SearchFields searchFields;

	public LoadLevel(String dbname) {
		this.player = player;
		searchFields = new SearchFields(dbname);
		findLevel();
	}

	public Level findLevel() {
		
		return level = new SpawnLevel("/levels/spawn.png");
	}
	
}
