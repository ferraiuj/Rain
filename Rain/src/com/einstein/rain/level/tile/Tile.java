package com.einstein.rain.level.tile;

import java.util.ArrayList;
import java.util.List;

import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;
import com.einstein.rain.level.Level;

public class Tile {

	public int x, y;
	public Sprite sprite;
	public boolean solid = false;
	public boolean drag = false;

	public static Tile wave = new Wave(Sprite.wave);
	public static Tile grassSnowMinimal = new GrassSnowMinimal(Sprite.grassSnowMinimal);
	public static Tile tallGrass1 = new TallGrass1(Sprite.tallGrass1);
	public static Tile fire = new Fire(Sprite.fire);
	public static Tile brickHorizontal = new BrickHorizontal(Sprite.brickHorizontal);
	public static Tile floor = new Floor(Sprite.floor);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile bookLeft = new BookLeft(Sprite.bookLeft);
	public static Tile bookMiddle = new BookMiddle(Sprite.bookMiddle);
	public static Tile bookRight = new BookRight(Sprite.bookRight);
	public static Tile wall1 = new Ash(Sprite.wall1);
	public static Tile wall2 = new Wall2(Sprite.wall2);
	public static Tile forest = new Forest(Sprite.forest);

	private int colorChange = 0xffFF00FF;
	public static final int col_spawn_grass = 0xFF30FF30;
	public static final int col_spawn_wave = 0xFF161AFF;
	public static final int col_spawn_fire = 0xFFFF0000;
	public static final int col_spawn_brick = 0xFFFF8A16;
	public static final int col_spawn_floor = 0xFF6B2703;
	public static final int col_spawn_tallGrass = 0xffFF1EEC;
	public static final int col_spawn_ash = 0xffFFFB35;
	public static final int col_spawn_bl = 0xffFFBC14;
	public static final int col_spawn_bm = 0xffBD0AFF;
	public static final int col_spawn_br = 0xff16B1FF;
	public static final int col_spawn_forest = 0xff007F0E;
	public static final int col_spawn_wall1 = 0xffFF0400;
	public static final int col_spawn_wall2 = 0xff404040;

	public Tile(Sprite sprite) {

		this.sprite = sprite;
	}

	public Tile() {

	}

	public void render(int x, int y, Screen screen) {

	}

	public void update(int x, int y, Screen screen) {

	}

	public boolean solid() {
		return false;

	}

	public boolean clickable() {
		return false;
	}

	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset, Level level) {
		solid = false;
		for (int corner = 0; corner < 4; corner++) {
			int xt = (x - corner % 2 * size + xOffset) >> 4;
			int yt = (y - corner / 2 * size + yOffset) >> 4;
			if (level.getTile(xt, yt, level).solid())
				solid = true;
		}
		return solid;
	}

	public boolean tileDraggable(int x, int y, int size, int xOffset, int yOffset, Level level) {
		drag = false;
		for (int corner = 0; corner < 4; corner++) {
			int xt = (x - corner % 2 * size + xOffset) >> 4;
			int yt = (y - corner / 2 * size + yOffset) >> 4;
			if (level.getTile(xt, yt, level).draggable())
				drag = true;
		}
		return drag;
	}

	public void getTileName() {
		System.out.println("VOid");
	}

	public Sprite getSprite() {
		return sprite;
	}

	public boolean draggable() {
		return false;
	}

	public void setTileColor(int x, int y, Level level) {
		colorChange = level.tiles[x + y * 40];
	}

	public int getTileColor(int x, int y, Level level) {
		return colorChange;
	}

	public Tile get() {
		return this;
	}

	public Tile setTile(int x, int y, Level level) {// cant change attached to too many

		if (x < 0 || y < 0 || x >= level.width || y >= level.height)
			return Tile.grassSnowMinimal;
		if (level.tiles[x + y * 40] == Tile.col_spawn_grass)
			return Tile.grassSnowMinimal;
		if (level.tiles[x + y * 40] == Tile.col_spawn_tallGrass)
			return Tile.tallGrass1;
		if (level.tiles[x + y * 40] == Tile.col_spawn_fire)
			return Tile.fire;
		if (level.tiles[x + y * 40] == Tile.col_spawn_wave)
			return Tile.wave;
		if (level.tiles[x + y * 40] == Tile.col_spawn_brick)
			return Tile.brickHorizontal;
		if (level.tiles[x + y * 40] == Tile.col_spawn_floor)
			return Tile.floor;
		if (level.tiles[x + y * 40] == Tile.col_spawn_bl)
			return Tile.bookLeft;
		if (level.tiles[x + y * 40] == Tile.col_spawn_bm)
			return Tile.bookMiddle;
		if (level.tiles[x + y * 40] == Tile.col_spawn_br)
			return Tile.bookRight;
		if (level.tiles[x + y * 40] == Tile.col_spawn_forest)
			return Tile.forest;
		if (level.tiles[x + y * 40] == Tile.col_spawn_wall1)
			return Tile.wall1;
		if (level.tiles[x + y * 40] == Tile.col_spawn_wall2)
			return Tile.wall2;
		if (level.tiles[x + y * 40] == 0xffFF050D)
			return Tile.wall1;
		if (level.tiles[x + y * 40] == 0xffFF0509)
			return Tile.wall1;

		return Tile.voidTile;
	}

}
