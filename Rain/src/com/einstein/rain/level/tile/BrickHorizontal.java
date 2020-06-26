package com.einstein.rain.level.tile;

import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;
import com.einstein.rain.level.Level;

public class BrickHorizontal extends Tile {
	private Level level;
	public BrickHorizontal(Sprite sprite) {
		super(sprite);

	}

	public void render(int x, int y, Screen screen) {
		
		screen.renderTile(x << 4, y << 4, this);
		//screen.drawRect(x, y, 64, 64, 0x0019FF, false);
	}
		public boolean solid() {
			return true;
	}
		public void getTileName() {
			//drawRect(x, y, 64, 64, 0x0019FF, false);
			System.out.println("brick: x = " + x + " y = " + y + " ");
		}
}

