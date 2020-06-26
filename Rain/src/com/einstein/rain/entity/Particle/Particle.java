package com.einstein.rain.entity.Particle;

import com.einstein.rain.entity.Entity;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;

public class Particle extends Entity {

	private Sprite sprite;
	private int life;
	private int time = 0;

	protected double xx, yy, zz;
	protected double xa, ya, za;
	protected boolean solid = false;

	public Particle(int x, int y, int life) {
		this.entityX = x;
		this.entityY = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(50) - 10);
		
		
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2.0;
	}
	
	public void update(Screen screen) {
		time++;
		if (time >= 7400) time = 0;
		if (time > life) remove();
		za -= 0.1;
		if (zz < 0) {
			zz = 0;
			za *= -0.7;
			xa *= .5;
			ya *= .5;
		}

		move(xx + xa, (yy + ya) + (zz + za));

	}

	private void move(double x, double y) {

		if (collision(x, y)) {
			this.xa *= -.05;
			this.ya *= -.05;
			this.za *= -.05;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;

	}

	public boolean collision(double x, double y) {

		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * 14) / 16;
			double yt = (y - c / 2 * 14) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy, level).solid()) solid = true;
 
		}
		return solid;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) (yy - zz), sprite, true);

	}

}
