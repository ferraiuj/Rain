package com.einstein.rain.entity.mob;

import com.einstein.rain.Game;
import com.einstein.rain.entity.mob.playerabilities.Blink;
import com.einstein.rain.entity.projectile.PewProjectile;
import com.einstein.rain.entity.projectile.Projectile;
import com.einstein.rain.graphics.AnimatedSprite;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;
import com.einstein.rain.input.Keyboard;
import com.einstein.rain.input.Mouse;

public class Player extends Mob {

	private String name;
	private Keyboard input;
	private Sprite sprite;
	public Mouset mouset;
	private Blink blink;
	private double getMousetX;
	private double getMousetY;
	private AnimatedSprite animSprite = up;
	private int fireRate = 0;
	public double speed = 3;
	private double getRadian;

	@Deprecated
	public Player(String name, Keyboard input) {
		this.name = name;
		this.input = input;

	}
	public Player() {

	}
	public Player(String name, int x, int y, Keyboard input, Mouset mouset) {
		this.name = name;
		this.entityX = x;
		this.entityY = y;
		this.input = input;
		this.mouset = mouset;
		blink = new Blink();	
		sprite = Sprite.player;
		fireRate = PewProjectile.FIRE_RATE;
	}
	public String getName() {
		return name;
	}
	public double getRadian(double mousetX, double mousetY ) {
		getMousetX = (mouset.getMousetX() - getX());
		getMousetY = (mouset.getMousetY() - getY());
		getRadian = Math.atan2(getMousetY, getMousetX);
		//System.out.println("get radian " + getRadian + " getMousetX = " + getMousetX + " getMousetY = " + getMousetY);
		//System.out.println("get playerX" + getX() + " getPlayerY = " + getY());
		return getRadian;
	}

	public void update(Screen screen) {
			if (input.space) {		
			mouset.centerMousePlayer(this, screen);
		}
		double xMoveOffset = 0, yMoveOffset = 0;

		if (walking) {
			animSprite.update();

		} else {
			animSprite.setFrame(0);
		}

		if (fireRate > 0)
			fireRate--;
		// Use a timer for skills and abilities//
		// Move skills and special abilities to their own folder

		spawnParticle.creativeParticle(this.entityX + 6, this.entityY + 14, 50, 1, level, 5, 5);

		if (input.blink) {
			blink.blink(this.level, this, this.entityX + 6, this.entityY + 14, getRadian(getMousetX, getMousetY));
		}
		blink.updateCooldown();
		

		if (input.up) {
			yMoveOffset -= speed;

		}
		if (input.down) {
			yMoveOffset += speed;

		}
		if (input.left) {
			xMoveOffset -= speed;

		}
		if (input.right) {
			xMoveOffset += speed;

		}
		if (xMoveOffset != 0 || yMoveOffset != 0) {
			move(xMoveOffset, yMoveOffset);
			walking = true;
		} else {
			walking = false;
		}

		clear();
		updateShooting();

		// health = 50;
		// uiHealthBar.setProgress(health / 100.0);
	}
	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved())
				level.getProjectiles().remove(i);
		}
	}
	private void updateShooting() {

		if (Mouse.getB() == 1 && fireRate <= 0) {
			shoot(entityX, entityY, getRadian(getMousetX, getMousetY));
			fireRate = PewProjectile.FIRE_RATE;
		}
	}
	public void render(Screen screen) {
		sprite = getPlayerDirection(getMousetX, getMousetY);
		screen.renderMob((int) (entityX - 8), (int) (entityY - 16), sprite, true);
	}

	public void abilities() {

	}
//	public void getDirection(double getX, double getY) {
//		
//	}
	public AnimatedSprite getPlayerDirection(double getX, double getY) {
	
		if (-135 < Math.toDegrees(getRadian(getX, getY)) && Math.toDegrees(getRadian(getX, getY)) < -45) {
			
			animSprite = up;	
			
		}
		else if (-45 < Math.toDegrees(getRadian(getX, getY))
				&& Math.toDegrees(getRadian(getX, getY)) < 45) {
			
			animSprite = right;		
			
		} 
		else if (45 < Math.toDegrees(getRadian(getX, getY))
				&& Math.toDegrees(getRadian(getX, getY)) < 135) {
			animSprite = down;
			
		} 
		else if (-135 > Math.toDegrees(getRadian(getX, getY))
				&& Math.toDegrees(getRadian(getX, getY)) > -179
				|| 135 < Math.toDegrees(getRadian(getX, getY))
						&& Math.toDegrees(getRadian(getX, getY)) < 180) {
			animSprite = left;
			
		}
		return animSprite;
	}
}
