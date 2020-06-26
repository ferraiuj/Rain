package com.einstein.rain.graphics;

import java.util.Random;

import com.einstein.rain.entity.mob.Chaser;
import com.einstein.rain.entity.mob.Mob;
import com.einstein.rain.entity.mob.Mouset;
import com.einstein.rain.entity.mob.Smart;
import com.einstein.rain.entity.projectile.Projectile;
import com.einstein.rain.input.Mouse;
import com.einstein.rain.level.tile.Ash;
import com.einstein.rain.level.tile.Tile;
import com.einstein.rain.util.Colors;

public class Screen {

	public Colors color;
	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64; // Ensure map size alteration doesnt crash
									// game
	public final int MAP_SIZE_MASK = MAP_SIZE - 1; // Ensure map size alteration
													// doesnt crash game
	public int tiles[] = new int[MAP_SIZE * MAP_SIZE];
	public int xOffset, yOffset;
	private final int ALPHA_COL = 0xffFF00FF;
	private Random random = new Random(); // creates random number

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		color = new Colors();
		pixels = new int[width * height];

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff); // chooses random color
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}

	}

	public void renderSheet(int xp, int yp, Spritesheet sheet, boolean fixed) {
		if (fixed) {

			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sheet.SPRITE_HEIGHT; y++) {
			int ya = y + yp;
			for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				pixels[xa + ya * width] = sheet.pixels[x + y * sheet.SPRITE_WIDTH];
			}
		}
	}

	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {

			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != ALPHA_COL)
					pixels[xa + ya * width] = col;
				// pixels[xa + ya * width] = sprite.pixels[x + y *
				// sprite.getWidth()];
			}
		}

	}

	public int[] particlePixels;

	public void renderParticle(int xPosition, int yPosition, int particleWidth, int particleHeight, boolean fixed,
			int color) {

		if (fixed) {

			xPosition -= xOffset;
			yPosition -= yOffset;
		}
		for (int y = 0; y < particleHeight; y++) {
			int ya = y + yPosition;
			for (int x = 0; x < particleWidth; x++) {
				int xa = x + xPosition;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				int col = color;
				if (col != ALPHA_COL)
					pixels[xa + ya * width] = col;
			}
		}

	}

	public void renderTextChar(int xp, int yp, Sprite sprite, boolean fixed, int color) {

		if (fixed) {

			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != ALPHA_COL)
					pixels[xa + ya * width] = color;
				// pixels[xa + ya * width] = sprite.pixels[x + y *
				// sprite.getWidth()];
			}
		}
	}

	public void renderTile(int xPosOnMap, int yPosOnMap, Tile tile) {
		
		xPosOnMap -= xOffset;
		yPosOnMap -= yOffset;
		
		for (int ySpriteWidth = 0; ySpriteWidth < tile.sprite.SIZE; ySpriteWidth++) {
			int ySpriteBounds = ySpriteWidth + yPosOnMap;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xPosOnMap;
				if (xa < -tile.sprite.SIZE || xa >= width || ySpriteBounds < 0 || ySpriteBounds >= height)
					break;
				if (xa < 0)
					xa = 0;
				pixels[xa + ySpriteBounds * width] = tile.sprite.pixels[x + ySpriteWidth * tile.sprite.SIZE];
				int col = tile.getSprite().pixels[x + ySpriteWidth * 16];
				if ((tile instanceof Ash) && col == 0xffFF0A1A)
					col = 0xff660000;
				if (col != ALPHA_COL)
					pixels[xa + ySpriteBounds * width] = col;

			}
		}
	}

	public void renderProjectile(int xp, int yp, Projectile p) {

		xp -= xOffset;
		yp -= yOffset;

		for (int y = 0; y < p.getSpriteSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp;
				if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col = p.getSprite().pixels[x + y * p.getSprite().SIZE];
				if (col != ALPHA_COL)
					pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderMob(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getSpriteSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getSpriteSize(); x++) {
				int xa = x + xp;
				if (xa < -sprite.getSpriteSize() || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col = sprite.getSprite().pixels[x + y * sprite.getSprite().SIZE];
				if (col != ALPHA_COL)
					pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderMob(int xPosOnMap, int yPosOnMap, Mob mob) {
		xPosOnMap -= xOffset;
		yPosOnMap -= yOffset;
		for (int yPixels = 0; yPixels < 32; yPixels++) {
			int ya = yPixels + yPosOnMap;
			// int ys = yPixels;
			for (int xPixels = 0; xPixels < 32; xPixels++) {
				int xa = xPixels + xPosOnMap;
				// int xs = xPixels;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col = mob.getSprite().pixels[xPixels + yPixels * 32];
				if ((mob instanceof Chaser) && col == 0xff0400FF)
					col = 0xff000000;
				if ((mob instanceof Smart) && col == 0xff0400FF)
					col = 0xffFF8132;
				if (col != ALPHA_COL)
					pixels[xa + ya * width] = col;
			}
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;

	}
	public int getOffsetX() {
		return xOffset;
	

	}
	public int getOffsetY() {
		return yOffset;

	}
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void drawRect(int xp, int yp, int width, int height, int color, boolean fixed) {
		if (fixed) {

			xp -= xOffset;
			yp -= yOffset;
		}

		for (int y = yp; y <= yp + height; y++) {

			if (xp >= this.width || y < 0 || y >= this.height)
				continue;
			if (xp > 0)
				pixels[xp + y * this.width] = color;
			if (xp + width >= this.width)
				continue;
			if (xp + width > 0)
				pixels[(xp + width) + y * this.width] = color;
		}
		for (int x = xp; x < xp + width; x++) {

			if (x < 0 | x >= this.width || yp >= this.height)
				continue;
			if (yp > 0)
				pixels[x + yp * this.width] = color;
			if (yp + height >= this.height)
				continue;
			if (yp + height > 0)
				pixels[x + (yp + height) * this.width] = color;
		}

	}

	public void drawMap(int xp, int yp, int color) {
		pixels[xp + yp * this.width] = color;
	}

	public void drawPlayer(int xp, int yp, int width, int height, int color, boolean fixed) {

		for (int x = 0; x < width; x++) {
				pixels[xp + yp * this.width] = color;	
		}
	}
}