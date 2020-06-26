package com.einstein.rain.level.tile;

import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;

public class Floor extends Tile {
	public Floor(Sprite sprite) {
		super(sprite);
		

	}

	public void render(int x, int y, Screen screen) {
		
		screen.renderTile(x << 4, y << 4, this);
	//	screen.drawRect(x, y, 64, 64, 0x0019FF, false);
		
	}
	public void getTileName() {
		System.out.println("Floor");
	}
	public boolean solid() {
		return false;

	}
}

