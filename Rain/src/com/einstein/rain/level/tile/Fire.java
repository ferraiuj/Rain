package com.einstein.rain.level.tile;

import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;

public class Fire extends Tile {
	
	public Fire(Sprite sprite) {
		super(sprite);

	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
		//screen.drawRect(x, y, 64, 64, 0x0019FF, false);
	}
		private boolean collision() {
			return true;
	}
		public boolean douse(){
			return true;
		}
}