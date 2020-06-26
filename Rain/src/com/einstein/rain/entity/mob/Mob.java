package com.einstein.rain.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.einstein.rain.Game;
import com.einstein.rain.entity.Entity;
import com.einstein.rain.entity.projectile.PewProjectile;
import com.einstein.rain.entity.projectile.Projectile;
import com.einstein.rain.graphics.AnimatedSprite;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Spritesheet;
import com.einstein.rain.level.Level;
import com.einstein.rain.level.tile.Tile;

public abstract class Mob extends Entity {

	// extend to inherit variables
	protected boolean moving = false;
	protected boolean walking = false;
	protected int health;
	protected boolean solid = false;
	protected AnimatedSprite left = new AnimatedSprite(Spritesheet.player_left, 32, 32, 3);
	protected AnimatedSprite right = new AnimatedSprite(Spritesheet.player_right, 32, 32, 3);
	protected AnimatedSprite up = new AnimatedSprite(Spritesheet.player_up, 32, 32, 3);
	protected AnimatedSprite down = new AnimatedSprite(Spritesheet.player_down, 32, 32, 3);

	protected enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	protected Direction dir;

	public void move(double moveX, double moveY) {
		if (moveX != 0 && moveY != 0) {
			move(moveX, 0);
			move(0, moveY);
			return;
		}
		if (moveX > 0)
			dir = Direction.RIGHT;
		if (moveX < 0)
			dir = Direction.LEFT;
		if (moveY > 0)
			dir = Direction.DOWN;
		if (moveY < 0)
			dir = Direction.UP;

		while (moveX != 0) {

			if (Math.abs(moveX) > 1) {

				if (!collision(abs(moveX), moveY)) {

					this.entityX += abs(moveX);
				}
				moveX -= abs(moveX);

			} else {

				if (!collision(abs(moveX), moveY)) {

					this.entityX += moveX;
				}
				moveX = 0;
			}
			// System.out.println("MoveX " + moveX);
		}
		while (moveY != 0) {

			if (Math.abs(moveY) > 1) {

				if (!collision(moveX, abs(moveY))) {

					this.entityY += abs(moveY);
				}
				moveY -= abs(moveY);
			} else {

				if (!collision(moveX, abs(moveY))) {

					this.entityY += moveY;
				}
				moveY = 0;
			}
		}
	}
	public void mobMove(double moveX, double moveY) {
		if (moveX != 0 && moveY != 0) {
			mobMove(moveX, 0);
			mobMove(0, moveY);
			return;
		}

		if (moveX > 0)
			dir = Direction.RIGHT;
		if (moveX < 0)
			dir = Direction.LEFT;
		if (moveY > 0)
			dir = Direction.DOWN;
		if (moveY < 0)
			dir = Direction.UP;

		while (moveX != 0) {

			if (Math.abs(moveX) > 1) {

				if (!mobCollision(abs(moveX), moveY)) {

					this.entityX += abs(moveX);
				}
				moveX -= abs(moveX);
			} else {
				if (!mobCollision(abs(moveX), moveY)) {

					this.entityX += moveX;
				}
				moveX = 0;
			}
		}
		while (moveY != 0) {

			if (Math.abs(moveY) > 1) {

				if (!mobCollision(moveX, abs(moveY))) {

					this.entityY += abs(moveY);
				}
				moveY -= abs(moveY);
			} else {
				if (!mobCollision(moveX, abs(moveY))) {

					this.entityY += moveY;
				}
				moveY = 0;
			}
		}
	}

	private int abs(double value) {
		if (value < 0)
			return -1;
		return 1;
	}

	public abstract void update(Screen screen);

	public abstract void render(Screen screen);

	protected void shoot(double x, double y, double dir) {

		Projectile pew = new PewProjectile(x, y, dir);
		level.add(pew);
	}

	public boolean collision(double xDir, double yDir) {
		solid = false;
		for (int corner = 0; corner < 4; corner++) {
			double xTileEstimate = ((entityX + xDir) - corner % 2) / 16;
			double yTileEstimate = ((entityY + yDir) - corner / 2) / 16;

			int xTileRounded = (int) Math.ceil(xTileEstimate);
			int yTileRounded = (int) Math.ceil(yTileEstimate);

			if (corner % 2 == 0)
				xTileRounded = (int) Math.floor(xTileEstimate);
			if (corner / 2 == 0)
				yTileRounded = (int) Math.floor(yTileEstimate);

			//if (tile.getTile(xTileRounded, yTileRounded, level).solid())
			if (level.getTile(xTileRounded, yTileRounded, level).solid())
				solid = true;

		}
		return solid;
	}
	public boolean mobCollision(double moveX, double moveY) {

		List<Boolean> cornerList = new ArrayList<Boolean>();
		solid = false;
		for (int corner = 0; corner < 4; corner++) {

			double xTileEstimate = ((entityX + moveX) - corner % 2) / 16;
			double yTileEstimate = ((entityY + moveY) - corner / 2) / 16;

			int xTileRounded = (int) Math.ceil(xTileEstimate);
			int yTileRounded = (int) Math.ceil(yTileEstimate);
			if (corner % 2 == 0)
				xTileRounded = (int) Math.floor(xTileEstimate);
			if (corner / 2 == 0)
				yTileRounded = (int) Math.floor(yTileEstimate);
			if (level.getTile(xTileRounded, yTileRounded, level).solid())
				solid = true;
			cornerList.add(corner, solid);
		}
		return solid;

	}

	public AnimatedSprite getDirection(double getX, double getY) {

		if (-135 < Math.toDegrees(math.getRadian(getX, getY)) && Math.toDegrees(math.getRadian(getX, getY)) < -45) {

			return animSprite = new AnimatedSprite(Spritesheet.player_left, 32, 32, 3);
		} else if (-45 < Math.toDegrees(math.getRadian(getX, getY))
				&& Math.toDegrees(math.getRadian(getX, getY)) < 45) {

			return animSprite = new AnimatedSprite(Spritesheet.player_right, 32, 32, 3);
		} else if (45 < Math.toDegrees(math.getRadian(getX, getY))
				&& Math.toDegrees(math.getRadian(getX, getY)) < 135) {
			return animSprite = new AnimatedSprite(Spritesheet.player_up, 32, 32, 3);
		} else if (-135 > Math.toDegrees(math.getRadian(getX, getY))
				&& Math.toDegrees(math.getRadian(getX, getY)) > -179
				|| 135 < Math.toDegrees(math.getRadian(getX, getY))
						&& Math.toDegrees(math.getRadian(getX, getY)) < 180) {
			return animSprite = new AnimatedSprite(Spritesheet.player_down, 32, 32, 3);
		}
		return animSprite = new AnimatedSprite(Spritesheet.player_down, 32, 32, 3);
	}
}

// System.out.println(x + "  " + y + "xDir " + xDir + " yDir " +
// yDir + "  xt  " + xt + "  yt  " + yt);
