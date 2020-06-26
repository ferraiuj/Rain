package com.einstein.rain.level.tile;

import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;

public class TallGrass1 extends Tile {
	public TallGrass1(Sprite sprite) {
		super(sprite);

	}

	public void render(int x, int y, Screen screen) {
	//	screen.drawRect(x, y, 64, 64, 0x0019FF, false);
		screen.renderTile(x << 4, y << 4, this);
		
	}
	public boolean searchable()
	{
		return true;
	}
	public void getTileName() {
		System.out.println("TallGrass");
	}
}
