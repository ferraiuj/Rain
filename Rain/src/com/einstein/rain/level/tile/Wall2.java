package com.einstein.rain.level.tile;

import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;

public class Wall2 extends Tile {

	public Wall2(Sprite sprite) {
		super(sprite);
		//System.out.println("floor");
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
		//screen.drawRect(x, y, 64, 64, 0x0019FF, false);
	}
	public void update(int x, int y, Screen screen) {
		screen.renderTile((x + 1) << 4, y << 4, this);
	}
	public boolean solid() {
		return true;

	}
	public boolean draggable() {
		return true;
	}
	public void getTileName() {
		System.out.println("Wall2");
	}
}
