package com.einstein.rain.entity.Particle;

import com.einstein.rain.entity.Entity;
import com.einstein.rain.entity.mob.Player;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;
import com.einstein.rain.util.Colors;

public class ExplosionEffect extends Entity {

	
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

	public ExplosionEffect(int x, int y, int life, int width, int height, int color) {
		this.entityX = x;
		this.entityY = y;
		this.xMove = x;
		this.yMove = y;
		this.life = life + (random.nextInt(50) - 10);
		this.width = width;
		this.height = height;
		this.color = color;
		this.xAxis = random.nextGaussian() + 10;
		this.yAxis = random.nextDouble() - 1.2;
		this.zMove = 0;

	}

	public void update(Screen screen) {
		time++;

		if (a + b / a == a / b) {
			goldenRatio = a / b;

		} else
			a++;
		if (time >= 7400)
			time = 0;

		if (time > life)
			remove();
		if (time % 4 == 0) {
			 
			xAxis = (Math.cos(angle) * 20);
			yAxis = (Math.sin(angle) * 20) - random.nextDouble();
			
			if (xAxis > 0) {
				xAxis += random.nextDouble() + 5;
				zMove = 2;
			}
			if (xAxis < 0) {
				xAxis -= random.nextDouble() + 5;
				zMove = -2;
			}
			//xAxis += random.nextDouble() + 5;
			//xAxis += Math.cos(Math.PI ) ;
//				width += (Math.cos(angle) + 1);
//				 height += (Math.sin(angle));
//			if (Math.cos(angle) > 0) {
//				width += (Math.cos(angle));
//				//xAxis += 2;
//				// xAxis += random.nextDouble() + 5;
//				// xAxis += Math.cos(angle );
//				// yAxis += Math.sin(angle);
//				// angle++;
////				zMove = 2;
//			}
//			if (Math.cos(angle) < 0) {
//				//xAxis -= 2;
//				width -= (Math.cos(angle));
//				// xAxis += Math.cos(angle );
//				// yAxis += Math.sin(angle);
//				// angle++;
//				// width--;
//				// height--;
//				// xAxis -= Math.cos(Math.PI );;
//				// yAxis += Math.pow(Math.PI * 1, 2);
//				// xAxis -= random.nextDouble() + 5;
////				zMove = -2;
//			}
//
//			if (Math.sin(angle) > 0) {
//				 height += (Math.sin(angle));
//				//yAxis += 2;
//			}
//			if (Math.sin(angle) < 0) {
//				 height -= (Math.sin(angle));
//				//yAxis -= 2;
//			}

//			if (zMove != 0) {
//				xAxis *= -random.nextDouble() + .2;
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

		screen.renderParticle((int) xMove, (int) yMove, (int)width, (int)height, true, color);
	}

}
