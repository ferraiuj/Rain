package com.einstein.rain.entity.mob;

import com.einstein.rain.graphics.AnimatedSprite;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;
import com.einstein.rain.graphics.Spritesheet;

public class Dummy extends Mob {

	private AnimatedSprite left = new AnimatedSprite(Spritesheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(Spritesheet.dummy_right, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(Spritesheet.dummy_up, 32, 32, 3);
	private AnimatedSprite down = new AnimatedSprite(Spritesheet.dummy_down, 32, 32, 3);

	private AnimatedSprite animSprite = down;

	private int time = 0;
	private int xa = 0;
	private int ya = 0;

	public Dummy(int x, int y) {
		this.entityX = x << 4;
		this.entityY = y << 4;
		sprite = Sprite.dummy;
	}

	public void update(Screen screen) {
		time++;
		if (time % (random.nextInt(50) + 30) == 0) {
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
			if (random.nextInt(4) == 0) {
				xa = 0;
				ya = 0;
			}
		}

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

		if (xa != 0 || ya != 0) {
			//move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		//System.out.println(xa);

	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) (entityX - 16), (int) (entityY - 16), sprite, true);
	}
}
