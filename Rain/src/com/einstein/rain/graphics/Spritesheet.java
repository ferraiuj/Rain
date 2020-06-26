package com.einstein.rain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	
	private String path;
	private int width, height;
	public final int SIZE; 
	public final int SPRITE_WIDTH, SPRITE_HEIGHT; //how many sprites on the spritesheet
	public int[] pixels;
	
	
	public static Spritesheet tiles = new Spritesheet("/textures/spritesheet/spritesheet.png", 256);
	public static Spritesheet tiles1 = new Spritesheet("/textures/spritesheet/spritesheet3.png", 256);
	public static Spritesheet tiles2 = new Spritesheet("/textures/spritesheet/spritesheet2.png", 256);
	public static Spritesheet wizardPro = new Spritesheet("/textures/spritesheet/Projectiles/Wizard.png", 48);
	public static Spritesheet mouse = new Spritesheet("/textures/spritesheet/Mouse/mouse1.png", 8);
	
	public static Spritesheet player = new Spritesheet("/textures/Spritesheet/playersheet.png", 128, 96);
	public static Spritesheet player_left = new Spritesheet(player, 0, 0, 1, 3, 32);
	public static Spritesheet player_right = new Spritesheet(player, 1, 0, 1, 3, 32);
	public static Spritesheet player_down = new Spritesheet(player, 2, 0, 1, 3, 32);
	public static Spritesheet player_up = new Spritesheet(player, 3, 0, 1, 3, 32);

	public static Spritesheet dummy = new Spritesheet("/textures/Spritesheet/player2sheet.png", 128, 96);
	public static Spritesheet dummy_left = new Spritesheet(dummy, 0, 0, 1, 3, 32);
	public static Spritesheet dummy_right = new Spritesheet(dummy, 1, 0, 1, 3, 32);
	public static Spritesheet dummy_down = new Spritesheet(dummy, 2, 0, 1, 3, 32);
	public static Spritesheet dummy_up = new Spritesheet(dummy, 3, 0, 1, 3, 32);

	private Sprite[] sprites;

	public Spritesheet(Spritesheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;

		if (width == height)
			SIZE = width;
		else
			SIZE = -1;
		SPRITE_WIDTH = w;
		SPRITE_HEIGHT = h;

		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.SPRITE_WIDTH];

			}
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int y0 = 0; y0 < spriteSize; y0++) {
					for (int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * SPRITE_WIDTH];

					}

				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}

	public Spritesheet(String path, int size) {
		this.path = path;
		SIZE = size;
		SPRITE_WIDTH = size;
		SPRITE_HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	public Spritesheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		SPRITE_WIDTH = width;
		SPRITE_HEIGHT = height;
		pixels = new int[SPRITE_WIDTH * SPRITE_HEIGHT];
		load();
	}

	public Sprite[] getSprites() {
		return sprites;
	}

	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	
	public int[] getPixels() {
	
		return pixels;
	}
	private void load() {

		try {
			
			BufferedImage image = ImageIO.read(Spritesheet.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
			System.out.println("Succesfully loaded: " + path);

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("failed to load" + path);
		}
	}
}