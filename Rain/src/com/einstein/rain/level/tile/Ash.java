package com.einstein.rain.level.tile;

import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;

public class Ash extends Tile {
	public Ash(Sprite sprite) {
		super(sprite);

	}

	public void render(int x, int y, Screen screen) {

		screen.renderTile(x << 4, y << 4, this);
		screen.drawRect(x << 4, y << 4, 16,16 , 0xFF0000, true);
	}

	public boolean solid() {
		return true;

	}

	public void getTileName() {
		System.out.println("Ash");
	}
}
