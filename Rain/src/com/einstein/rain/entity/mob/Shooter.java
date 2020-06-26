package com.einstein.rain.entity.mob;

import java.util.List;

import com.einstein.rain.entity.Entity;
import com.einstein.rain.entity.Particle.Particle;
import com.einstein.rain.graphics.AnimatedSprite;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;
import com.einstein.rain.graphics.Spritesheet;
import com.einstein.rain.util.Debug;
import com.einstein.rain.util.Vector2i;

public class Shooter extends Mob {

	private AnimatedSprite left = new AnimatedSprite(Spritesheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(Spritesheet.dummy_right, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(Spritesheet.dummy_up, 32, 32, 3);
	private AnimatedSprite down = new AnimatedSprite(Spritesheet.dummy_down, 32, 32, 3);

	private AnimatedSprite animSprite = down;
	private int time = 0;
	private int xa = 0, ya = 0;

	public Shooter(int x, int y) {
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

		} else if (ya > 0) {
			animSprite = down;

		}
		if (xa < 0) {
			animSprite = left;

		} else if (xa > 0) {
			animSprite = right;

		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}

		List<Entity> entities = level.getEntities(this, 20000000);
		entities.add(level.getClientPlayer());
		double min = 0;
		Entity closest = null;
		if (closest instanceof Particle) {
			closest.remove();
		}

		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			double distance = Vector2i.getDistance(new Vector2i(entityX, entityY), new Vector2i(e.getX(), e.getY()));
			if (i == 0 || distance < min) {
				min = distance;
				closest = e;

			}
		}
 
		if (closest != null) {

			double dx = closest.getX() - entityX;
			double dy = closest.getY() - entityY;
			double dir = Math.atan2(dy, dx);
			if (time % 60 == 0) {
				shoot(entityX, entityY, dir);
			}
		}
		// System.out.println(closest);

	}

	public void render(Screen screen) {
		//Debug.drawRect(screen, -40, 40, 60, 40, 0xff00ff0, false);
		sprite = animSprite.getSprite();
		screen.renderMob(entityX - 16, entityY - 16, this);

	}

}
