package com.einstein.rain.entity.mob;

import java.util.List;
import java.util.Random;

import com.einstein.rain.graphics.AnimatedSprite;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;
import com.einstein.rain.graphics.Spritesheet;
import com.einstein.rain.level.Node;
import com.einstein.rain.util.Vector2i;

public class Smart extends Mob {

	private AnimatedSprite left = new AnimatedSprite(Spritesheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(Spritesheet.dummy_right, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(Spritesheet.dummy_up, 32, 32, 3);
	private AnimatedSprite down = new AnimatedSprite(Spritesheet.dummy_down, 32, 32, 3);

	private AnimatedSprite animSprite = down;

	private double xMove;
	private double yMove;

	Random random = new Random();

	public double speed = 4;

	private List<Node> path = null;

	public Smart(int x, int y) {
			
		this.entityX = (x << 4) + random.nextInt(50);
		this.entityY = (y << 4) + random.nextInt(50);
			
		sprite = Sprite.dummy;
	}

	private void move() {
		
		xMove = 0;
		yMove = 0;
		
		int px = level.getPlayerAt(0).getX();
		int py = level.getPlayerAt(0).getY();

		Vector2i start = new Vector2i(getX() >> 4, getY() >> 4);
		Vector2i dest = new Vector2i((px >> 4), (py >> 4));
		List<Player> players = level.getPlayers(this, 160);
		if (players.size() > 0) {
			path = dest.findPath(start, dest, level);
		}
		if (path != null) {
			if (path.size() > 0) {
				
				Vector2i vec = path.get(path.size() - 1).tile;

				if (entityX >= (vec.getX() << 4) + speed) xMove -= speed;
				if (entityX < (vec.getX() << 4)) xMove += speed;
				if (entityY >= (vec.getY() << 4) + speed) yMove -= speed;
				if (entityY <(vec.getY() << 4)) yMove += speed;
				
			}
		}
		if (xMove != 0 || yMove != 0) {
			move(xMove, yMove);
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
		if (yMove < 0) {
			animSprite = up;
			dir = Direction.UP;
		} else if (yMove > 0) {
			animSprite = down;
			// dir = Direction.DOWN;
		}
		if (xMove < 0) {
			animSprite = left;
			dir = Direction.LEFT;
		} else if (xMove > 0) {
			animSprite = right;
			dir = Direction.RIGHT;
		}
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		
		screen.renderMob((entityX - 8), (entityY - 16), this);
	}

}
