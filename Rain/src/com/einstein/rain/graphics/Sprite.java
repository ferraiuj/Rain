package com.einstein.rain.graphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private int width;
	private int height;
	protected Spritesheet sheet;
	public boolean raCol = false;
	private int r, g, b;

	static Random random = new Random();

	// Tiles
	public static Sprite wave = new Sprite(16, 3, 14, Spritesheet.tiles);
	public static Sprite missle = new Sprite(16, 0, 3, Spritesheet.tiles);
	public static Sprite voidSprite = new Sprite(16);
	public static Sprite brickDiagonal = new Sprite(16, 2, 0, Spritesheet.tiles);
	public static Sprite bookLeft = new Sprite(16, 7, 13, Spritesheet.tiles);
	public static Sprite bookRight = new Sprite(16, 8, 15, Spritesheet.tiles);
	public static Sprite bookMiddle = new Sprite(16, 8, 13, Spritesheet.tiles);
	public static Sprite grassSnowMinimal = new Sprite(16, 2, 1, Spritesheet.tiles);
	public static Sprite brickHorizontal = new Sprite(16, 1, 14, Spritesheet.tiles);
	public static Sprite tallGrass1 = new Sprite(16, 2, 3, Spritesheet.tiles);
	public static Sprite floor = new Sprite(16, 2, 15, Spritesheet.tiles);
	public static Sprite fire = new Sprite(16, 6, 15, Spritesheet.tiles);
	public static Sprite wall1 = new Sprite(16, 15, 4, Spritesheet.tiles);
	public static Sprite wall2 = new Sprite(16, 0, 15, Spritesheet.tiles);
	public static Sprite forest = new Sprite(16, 4, 0, Spritesheet.tiles);

	// projectiles

	public static Sprite wizard = new Sprite(16, 0, 0, Spritesheet.wizardPro);
	public static Sprite projectile_arrow = new Sprite(16, 1, 0, Spritesheet.wizardPro);
	// particles
	//public static Sprite particle_normal = new Sprite(3, 0xFF0000, false);
	//public static Sprite blink = new Sprite(1, 0xFF0000, true);

	public static Sprite dummy = new Sprite(32, 0, 0, Spritesheet.dummy);
	public static Sprite player = new Sprite(32, 0, 0, Spritesheet.player);
	// mouse
	public static Sprite mouse = new Sprite(8, 0, 0, Spritesheet.mouse);

	protected Sprite(Spritesheet sheet, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.sheet = sheet;
		this.width = width;
		this.height = height;

	}

	public Sprite(int size, int x, int y, Spritesheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int size) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		//setColor(color);
	}

	public Sprite(int size, int color, boolean raCol) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		if (raCol) {
			color = random.nextInt(3);
			if (color == 0) {
				color = 0xB200FF;
			} else if (color == 1) {
				color = 0x1427FF;
			} else if (color == 2) {
				color = 0xFF1E3C;
			}
		}
		setColor(color);
		System.out.println(color);
	}

	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = new int [pixels.length];
		for(int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
			
		}
	}
	public static Sprite rotate(Sprite sprite, double angle) {
		
		return new Sprite(rotate(sprite.pixels, sprite.width, sprite.height, angle), sprite.width, sprite.height);
	}
	private static int[] rotate(int[] pixels, int width, int height, double angle) {
		int[] result = new int[width * height];

		double nx_x = rot_x(-angle, 1.0, 0.0);
		double nx_y = rot_y(-angle, 1.0, 0.0);
		double ny_x = rot_x(-angle, 0.0, 1.0);
		double ny_y = rot_y(-angle, 0.0, 1.0);

		double x0 = rot_x(-angle, -width / 2.0, -height / 2.0) + width / 2.0;
		double y0 = rot_y(-angle, -width / 2.0, -height / 2.0) + height / 2.0;

		for (int y = 0; y < height; y++) {
			double x1 = x0;
			double y1 = y0;
			for (int x = 0; x < width; x++) {
				int xx = (int) x1;
				int yy = (int) y1;
				int col = 0;
				if (xx < 0 || xx >= width || yy < 0 || yy >= height)
					col = 0xffFF00FF;
				else
					col = pixels[xx + yy * width];
				result[x + y * width] = col;
				x1 += nx_x;
				y1 += nx_y;
			}
			x0 += ny_x;
			y0 += ny_y;
		}
		return result;
	}

	private static double rot_x(double angle, double x, double y) {
		double cos = Math.cos(angle - Math.PI / 2);
		double sin = Math.sin(angle - Math.PI / 2);
		return x * cos + y * -sin;
	}

	private static double rot_y(double angle, double x, double y) {
		double cos = Math.cos(angle - Math.PI / 2);
		double sin = Math.sin(angle - Math.PI / 2);
		return x * sin + y * cos;
	}


	public static Sprite[] split(Spritesheet sheet) {
		int amount = (sheet.getWidth() * sheet.getHeight()) / (sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT);
		Sprite[] sprites = new Sprite[amount];
		int current = 0;
		int[] pixels = new int[sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT];

		for (int yp = 0; yp < sheet.getHeight() / sheet.SPRITE_HEIGHT; yp++) {
			for (int xp = 0; xp < sheet.getWidth() / sheet.SPRITE_WIDTH; xp++) {

				for (int y = 0; y < sheet.SPRITE_HEIGHT; y++) {
					for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {

						int xo = x + xp * sheet.SPRITE_WIDTH;
						int yo = y + yp * sheet.SPRITE_HEIGHT;

						pixels[x + y * sheet.SPRITE_WIDTH] = sheet.getPixels()[xo + yo * sheet.getWidth()];
					}
				}
				sprites[current++] = new Sprite(pixels, sheet.SPRITE_WIDTH, sheet.SPRITE_HEIGHT);
				
			}
		}

		return sprites;
	}

	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SPRITE_WIDTH];
			}
		}

	}

	public Sprite getSprite() {
		return this;
	}

	public int getSpriteSize() {
		return this.SIZE;

	}

	public boolean solid() {
		return false;
	}

}
