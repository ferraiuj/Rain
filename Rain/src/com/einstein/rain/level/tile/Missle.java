package com.einstein.rain.level.tile;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;

public class Missle extends Tile {
	
		public Missle(Sprite sprite) {
			super(sprite);

		}

		public void render(int x, int y, Screen screen) {
			screen.renderTile(x << 4, y << 4, this);
		}
			private boolean collision() {
				return false;
		}
			public boolean douse(){
				return true;
			}
	}


