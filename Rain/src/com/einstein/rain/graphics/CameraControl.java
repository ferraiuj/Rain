package com.einstein.rain.graphics;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import com.einstein.rain.Game;
import com.einstein.rain.entity.mob.Mouset;
import com.einstein.rain.entity.mob.Player;
import com.einstein.rain.input.Keyboard;
import com.einstein.rain.input.Mouse;
import com.einstein.rain.level.Level;
import com.einstein.rain.level.tile.Tile;
import com.einstein.rain.util.ImageUtils;

public class CameraControl {

	// public Dimension screenSize;
	public Robot lockCursor;
	public int xScroll;
	public int yScroll;

	public Screen screen;
	public Mouse mouse;
	public int screenSpeed = 1;
	
	public CameraControl() {

		try {
			lockCursor = new Robot();
		} catch (AWTException e) {

			e.printStackTrace();
		}
	}

	

//	public void render(Screen screen, Keyboard key, Player player, Tile tile, Level level, Mouset mouset) {
//
//		if (Mouse.drag() == true) {
//			mouseCameraMove(Mouse.getX(), Mouse.getY(), screen);
//
//		} else if (key.space) {
//
//			xScroll = player.getX() - screen.width / 2;
//			yScroll = player.getY() - screen.height / 2;
//
//			mouset.centerMousePlayer(player, screen);
//		} else if (Mouse.getB() == 1) {
//
//			tile.getTile(0, 0, level).render(1, 2, screen);
//		}
//	}

	public int getOffsetX() {
		return xScroll;
	}

	public int getOffsetY() {
		return yScroll;
	}
}