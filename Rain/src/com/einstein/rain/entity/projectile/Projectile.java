package com.einstein.rain.entity.projectile;

import java.util.Random;

import com.einstein.rain.entity.Entity;
import com.einstein.rain.entity.mob.Mob;
import com.einstein.rain.graphics.Sprite;
import com.einstein.rain.level.tile.Tile;

public abstract class Projectile extends Entity {

	protected final double xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	
	protected double newX, newY;
	protected double distance;
	protected double speed, damage, range;
	protected Mob mob;

	protected final Random random = new Random();

	public Projectile(double x, double y, double dir) {
		xOrigin = x;
		yOrigin = y; 
		angle = dir;
		this.x = x;
		this.y = y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getSpriteSize() {
		return sprite.SIZE;

	}

	protected void move() {

	}

}
