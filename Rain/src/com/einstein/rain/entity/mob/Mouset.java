package com.einstein.rain.entity.mob;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.List;

import com.einstein.rain.Game;
import com.einstein.rain.entity.Entity;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;
import com.einstein.rain.input.Keyboard;
import com.einstein.rain.input.Mouse;
import com.einstein.rain.level.Level;
import com.einstein.rain.level.tile.Tile;

public class Mouset extends Entity {
	public Screen screen;
	public Sprite sprite;
	public Mouse mouse;
	private int xOffset;
	private int yOffset;
	public int xScroll;
	public int yScroll;
	private int mouseSpeed = 2;
	public int screenSpeed = 1;
	public Robot lockCursor;

	public Mouset(int x, int y, Screen screen) {
		this.entityX = x;
		this.entityY = y;
		this.xScroll = x - screen.width / 2;
		this.yScroll = y - screen.height / 2;
		this.screen = screen;
		sprite = Sprite.mouse;
		try {
			lockCursor = new Robot();
		} catch (AWTException e) {

			e.printStackTrace();
		}
	}
//	public void update(int entityX, int entityY, Screen screen, Tile tile) {
//		
//		if (Mouse.getB() == 1) {
//			//tile.getTile(entityX, entityY , level).render(10, 10, screen);
//			spawnParticle.traceMouse((int) this.entityX - screen.getOffsetX(), (int) this.entityY - screen.getOffsetY(),
//					50, 1, level, 5, 5);
//		}
//	}
	public void render(Screen screen) {
		screen.renderMob((int) entityX, (int) entityY, sprite, true);

	}

	public void centerMousePlayer(Player player, Screen screen) {
		entityX = (player.getX());
		entityY = (player.getY());
		xScroll = player.getX() - screen.width / 2;
		yScroll = player.getY() - screen.height / 2;
		
	}

	public void mouseReset() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		lockCursor.mouseMove(screenSize.width / 2, screenSize.height / 2);
	}

	public void mousetPressed() {
		if (tile.tileDraggable(entityX, entityY, -5, 0, 0, level)) {
			System.out.println("draggable");
			// screen.renderTile(entityX, entityY, tile.getTile(entityX, entityY, level));
			//tile.getTile(entityX, entityY, level).update(entityX, entityY, screen);
			// tile.getTile(entityX, entityY, level).getTileName();
		} else
			System.out.println("not draggable");
	}

	public void mousetReleased(int mouseX, int mouseY) {
	}

	public void mousetMoved(int entityX, int entityY) {

		if (this.xOffset > entityX) {
			this.entityX -= mouseSpeed;
		} else if (this.xOffset < entityX) {
			this.entityX += mouseSpeed;
		}
		if (this.yOffset > entityY) {
			this.entityY -= mouseSpeed;
		} else if (this.yOffset < entityY) {
			this.entityY += mouseSpeed;
		}
		this.yOffset = entityY;
		this.xOffset = entityX;
	}

	public void mouseCameraMove(int entityX, int entityY) {

		if (xOffset > entityX) {
			this.xScroll += screenSpeed;
		} else if (xOffset < entityX) {

			this.xScroll -= screenSpeed;
		}
		if (yOffset > entityY) {
			this.yScroll += screenSpeed;

		} else if (yOffset < entityY) {
			this.yScroll -= screenSpeed;
		}
		xOffset = entityX;
		yOffset = entityY;
	}

	public int tileX;
	public int tileY;

	public void tileMove(List<Tile> tiles) {

		int mouseX = getMousetX() >> 4;
		int mouseY = getMousetY() >> 4;
		if (Mouse.drag() == true) {
			// mousetMoved(Mouse.getX(), Mouse.getY());
			// tile.getTile(tileX, tileY, level).render(mouseX, mouseY, screen);
			//tiles.get(mouseX + mouseY * 40).setTileColor(tileX, tileY, level);
			//tiles.get(mouseX + mouseY * 40).render(mouseX, mouseY, screen);
			
			// System.out.println(tile.getTile(tileX, tileY, level).getTileColor(mouseX,
			// mouseY, level));
		} else if (Mouse.getB() == 1) {
			// need to write to create a new tile object here
			tiles.set(mouseX + mouseY * 40, tiles.get(tileX + tileY * 40));
			
			
			// Game.timeStamp();
			// tile.getTile(mouset.getMousetX() >> 4, mouset.getMousetY() >> 4,
			// level).render((mouset.getMousetX() + 16) >> 4, mouset.getMousetY() >> 4,
			// screen);
		} else if (!Mouse.press()) {
			
			//tiles.get(mouseX + mouseY * 40).setTileColor(tileX, tileY, level);
			//tiles.get(mouseX + mouseY * 40).render(tileX, tileY, screen);
			//Game.timeStamp();
			//tile.getTile(tileX, tileY, level).render(getMousetX() >> 4, getMousetY() >> 4, screen);
		}
		if(Mouse.getB() == 3) {
			tileX = getMousetX() >> 4;
			tileY = getMousetY() >> 4;
		}
//		 else if (Mouse.clicked()) {
//
//			Game.timeStamp();
//			
//			tile.getTile(tileX, tileY, level).render(getMousetX() >> 4, getMousetY() >> 4, screen); 

		// level.updateLevel(ImageUtils.updateMap(img, 0xFF30FF30, mouset.getMousetX()
		// >> 4, mouset.getMousetY() >> 4));
	}

	public int getOffsetX() {
		return xScroll;
	}

	public int getOffsetY() {
		return yScroll;
	}

	public int getMousetX() {
		return this.entityX;
	}

	public int getMousetY() {
		return this.entityY;
	}
}
