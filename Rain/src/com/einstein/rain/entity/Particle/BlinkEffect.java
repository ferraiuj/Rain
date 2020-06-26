package com.einstein.rain.entity.Particle;

import com.einstein.rain.entity.Entity;
import com.einstein.rain.graphics.Screen;

public class BlinkEffect extends Entity {
	
	private int life;
	private int time = 0;
	protected double dir;
	protected double xMove, yMove, zMove;
	protected double xAxis, yAxis, zAxis;
	protected int width, height;
	protected int color;
	protected boolean solid = false;

	public BlinkEffect(int x, int y, int life, int width, int height, int color) {
		this.entityX = x;
		this.entityY = y;
		this.xMove = x;
		this.yMove = y;
		this.life = life + (random.nextInt(50) - 10);
		this.width = width;
		this.height = height;
		this.color = color;
		this.xAxis = random.nextGaussian();
		this.yAxis = random.nextDouble() - 1.2;
		this.zMove = 0;

	}

	public void update(Screen screen) {
		time++;
		if (time >= 7400) time = 0;

		if (time > 50) remove();
		if (time % 1 == 0) {
			if (xAxis > 0) {
				xAxis += random.nextDouble() + 5;
				zMove = 2;
			}
			if (xAxis < 0) {
				xAxis -= random.nextDouble() + 5;
				zMove = -2;
			}
			if (zMove != 0) {
				xAxis *= -random.nextDouble() + .2;
			}
			
			move(xMove, yMove);
		}
		
	}

	private void move(double x, double y) {
		this.yMove += yAxis;
		this.xMove += xAxis;

	}

	public void render(Screen screen) {
		screen.renderParticle((int) xMove, (int) yMove, width, height, true, color );

	}

}
