package com.einstein.rain.entity;

import java.util.Random;

import com.einstein.rain.entity.spawner.ParticleSpawner;
import com.einstein.rain.graphics.AnimatedSprite;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;
import com.einstein.rain.graphics.Spritesheet;
import com.einstein.rain.level.Level;
import com.einstein.rain.level.tile.Tile;
import com.einstein.rain.util.MathUtil;

public class Entity { // abstract is like template
	protected ParticleSpawner spawnParticle;
	public int entityX; // entity has x and y if sprite
	public int entityY;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	protected Sprite sprite;
	protected MathUtil math;
	public int time1 = 0;
	public boolean isZero = false;
	protected boolean next = false;
	protected int aw = 0; 
	public Tile tile;
	
	protected AnimatedSprite left;
	protected AnimatedSprite right;
	protected AnimatedSprite up;
	protected AnimatedSprite down;
	
	protected AnimatedSprite animSprite = down;
	
	public Entity() {
		tile = new Tile();
		math = new MathUtil();
		spawnParticle = new ParticleSpawner();
	}
	public Entity(int x, int y, Sprite sprite) {
		this.entityX = x;
		this.entityY = y;
		this.sprite = sprite;	
	}
	public void update(Screen screen) {
		
	}
	public void render(Screen screen) {
		
		if (sprite != null) screen.renderSprite((int) entityX, (int) entityY, sprite, true);
	}

	public void remove() {
		// remove from level
		removed = true;
	}

	public int getX() {
		
		return entityX;
	}

	public int getY() {
		return entityY;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void init(Level level) {
		this.level = level;
	}

	public boolean getNext() {
		return this.next;
	}
//	public AnimatedSprite getDirection(double getX, double getY) {
//
//		if (-135 < Math.toDegrees(math.getRadian(getX, getY)) && Math.toDegrees(math.getRadian(getX, getY)) < -45) {
//			
//			return animSprite = up;
//		}
//		else if (-45 < Math.toDegrees(math.getRadian(getX, getY))
//				&& Math.toDegrees(math.getRadian(getX, getY)) < 45) {
//			
//			return animSprite = right;		
//		} 
//		else if (45 < Math.toDegrees(math.getRadian(getX, getY))
//				&& Math.toDegrees(math.getRadian(getX, getY)) < 135) {
//			return animSprite = down;
//		} 
//		else if (-135 > Math.toDegrees(math.getRadian(getX, getY))
//				&& Math.toDegrees(math.getRadian(getX, getY)) > -179
//				|| 135 < Math.toDegrees(math.getRadian(getX, getY))
//						&& Math.toDegrees(math.getRadian(getX, getY)) < 180) {
//			return animSprite = left;
//		}
//		return animSprite = down;
//	}
}
