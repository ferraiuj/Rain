package com.einstein.rain.entity.projectile;

import com.einstein.rain.Game;
import com.einstein.rain.entity.spawner.ParticleSpawner;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;
import com.einstein.rain.input.Mouse;

public class PewProjectile extends Projectile {
	protected ParticleSpawner spawnParticle;
	public static final int FIRE_RATE = 5;
	public PewProjectile(double x, double y, double dir) {
		super(x, y, dir);
		spawnParticle = new ParticleSpawner();
		range = random.nextInt(100) + 150;
		damage = 20;
		speed = 1;
		sprite = Sprite.rotate(Sprite.projectile_arrow, angle);
		Math.cos(angle);
		
		newX = speed * Math.cos(angle);
		newY = speed * Math.sin(angle); 
	}

	public void update(Screen screen) {	
		if (tile.tileCollision((int) (x + newX), (int) (y + newY), -7, 3, 3, level)) {

			// Particle p = new Particle((int) x, (int) y, 50);
			// level.add(p);
			//level.add(new ParticleSpawner((int) x + 5, (int) y + 6, 50, 15, level, 10, 15));
			spawnParticle.explosionParticle((int)x + 5, (int)y + 6, 50, 5, level, 2, 2);
			spawnParticle.blinkParticle((int)x + 5, (int)y + 6, 50, 5, level, 2, 2);
			
			//level.add(new ParticleSpawner((int) x + 5, (int) y + 6, 50, 15, level, 10, 15));
			remove();
		}
		//System.out.println(Math.toDegrees(angle));
		move();

	}

	protected void move() {
		x += newX;
		y += newY;
		if (distance() > range) remove();
	}

	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x, (int) y, this);
	}
}
