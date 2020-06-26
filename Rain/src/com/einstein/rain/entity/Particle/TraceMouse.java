package com.einstein.rain.entity.Particle;

import com.einstein.rain.Game;
import com.einstein.rain.entity.Entity;
import com.einstein.rain.entity.mob.Player;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.input.Mouse;

public class TraceMouse extends Entity {
	
	private int life;
	private int time = 0;
	protected double dir;
	protected double xMove, yMove, zMove;
	protected double xAxis, yAxis, zAxis;
	protected double width, height;
	protected int color;
	protected boolean solid = false;
	protected int a = 1;
	protected int b = 1;
	protected int goldenRatio;
	protected int angle;
	private boolean fixed = false;
	public TraceMouse(int x, int y, int life, int width, int height, int color) {
		
		this.entityX = x;
		this.entityY = y;
		this.xMove = x;
		this.yMove = y;
		this.life = life + (random.nextInt(50) - 10);
		this.width = width;
		this.height = height;
		this.color = color;
		this.xAxis = 0;
		this.yAxis = 0;
		this.zMove = 0;
		
	}

	public void update(Screen screen) {
		time++;
	
		if (time >= 7400)
			time = 0;

		if (time > 100)
			remove();
		if (time % 5 == 0) {
			
			//xAxis +=3;
			//yAxis +=3;
			//fixed = false;
			//System.out.println(getMouseX);
//			if (xAxis > 0) {
//				xAxis += random.nextDouble() + 5;
//				zMove = 2;
//			}
//			if (xAxis < 0) {
//				xAxis -= random.nextDouble() + 5;
//				zMove = -2;
//			}

			move(xMove, yMove);
			angle++;
		}
		// System.out.println(x + " " + ya);
	}

	private void move(double x, double y) {
		this.yMove += yAxis;
		this.xMove += xAxis;
	}
	public void render(Screen screen) {
		
		screen.renderParticle((int) xMove, (int) yMove, (int) width, (int) height, fixed, color);
	}

}
