package com.einstein.rain.level.tile;

import com.einstein.rain.entity.mob.Chaser;
import com.einstein.rain.entity.mob.Player;
import com.einstein.rain.entity.mob.Smart;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;
import com.einstein.rain.level.Level;

public class Wave extends Tile {

	public Wave(Sprite sprite) {
		super(sprite);

	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
		//screen.drawRect(x, y, 64, 64, 0xFF0000, false);		// RENDER HERE.
	}
	
	public boolean solid() {
		return true;

	}
	public boolean clickable() {
		return true;

	}
	public void getTileName() {
		System.out.println("Wave");
	}


}