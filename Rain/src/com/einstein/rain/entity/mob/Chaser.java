package com.einstein.rain.entity.mob;

import java.util.List;
import java.util.Random;

import com.einstein.rain.graphics.AnimatedSprite;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;
import com.einstein.rain.graphics.Spritesheet;

public class Chaser extends Mob {
	private AnimatedSprite left = new AnimatedSprite(Spritesheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(Spritesheet.dummy_right, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(Spritesheet.dummy_up, 32, 32, 3);
	private AnimatedSprite down = new AnimatedSprite(Spritesheet.dummy_down, 32, 32, 3);

	private AnimatedSprite animSprite = down;

	private double xa;
	private double ya;

	Random random = new Random();
	public double speed = random.nextInt(3) + 1;

	public Chaser(int x, int y) {
		this.entityX = (x << 4) + random.nextInt(50);
		this.entityY = (y << 4) + random.nextInt(50);
		sprite = Sprite.dummy;
	}

	private void move() {
		xa = 0;
		ya = 0;
		List<Player> players = level.getPlayers(this, 50);
		if (players.size() > 0) {
			Player player = players.get(0);

			if (entityX > player.getX() + speed) xa -= speed;
			if (entityX < player.getX() - speed) xa += speed;
			if (entityY > player.getY() + speed) ya -= speed;
			if (entityY < player.getY() - speed) ya += speed;
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}

	}

	public void update(Screen screen) {
		move();
		if (walking)
			animSprite.update();
		else
			animSprite.setFrame(0);
		if (ya < 0) {
			animSprite = up;
			dir = Direction.UP;
		} else if (ya > 0) {
			animSprite = down;
			dir = Direction.DOWN;
		}
		if (xa < 0) {
			animSprite = left;
			dir = Direction.LEFT;
		} else if (xa > 0) {
			animSprite = right;
			dir = Direction.RIGHT;
		}

	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) (entityX - 8), (int) (entityY - 16), this);
	}

}
