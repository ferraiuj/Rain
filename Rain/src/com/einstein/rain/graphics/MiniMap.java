package com.einstein.rain.graphics;

import java.util.List;

import com.einstein.rain.level.Level;
import com.einstein.rain.level.tile.Tile;

public class MiniMap {

	public MiniMap() {

	}

	public void render(Screen screen, Level level, List<Tile> tiles) {

		for (int y = 0; y < level.height; y++) {
			for (int x = 0; x < level.width; x++) {
				screen.drawMap(x, y, tiles.get(x + y * 40).getTileColor(x, y, level));
			}
		}
		screen.drawPlayer(level.getPlayerAt(0).getX() >> 4, level.getPlayerAt(0).getY() >> 4, 2, 2, 0xFFFFFFFF, false);
	}

	public void update(int xScroll, int yScroll) {
		for (int y = yScroll; y < level.height; y++) {
			for (int x = xScroll; x < level.width; x++) {
				screen.drawMap(x, y, level.tile.getTileColor(x, y, level));

			}
		}
	}

}
