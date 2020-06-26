package com.einstein.rain.entity.mob.playerabilities;

import com.einstein.rain.entity.Entity;
import com.einstein.rain.entity.mob.Mob;
import com.einstein.rain.entity.spawner.ParticleSpawner;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.level.Level;

public class Blink extends Entity {
	protected ParticleSpawner spawnParticle;
	private int cooldown = 240;
	private int blinkTimer = 240;

	public Blink() {
		spawnParticle = new ParticleSpawner();
	}
	public void blink(Level level, Mob mob, int spawnX, int spawnY, double radian) {

		double xMoveOffset = 0, yMoveOffset = 0;

		if (-135 < Math.toDegrees(radian) && Math.toDegrees(radian) < -45) {

			if (!mob.collision(0, yMoveOffset - 32) && blinkTimer == cooldown) {
				
				spawnParticle.blinkParticle(spawnX, spawnY, 20, 10, level, 20, 1);
				blinkTimer = 0;
				mob.entityY -= 32;
			}
		} else if (-45 < Math.toDegrees(radian) && Math.toDegrees(radian) < 45) {

			if (!mob.collision(xMoveOffset + 32, 0) && blinkTimer == cooldown) {
				
				spawnParticle.blinkParticle(spawnX, spawnY, 20, 50, level, 1, 1);
				blinkTimer = 0;
				mob.entityX += 32;
			}
		} else if (45 < Math.toDegrees(radian) && Math.toDegrees(radian) < 135) {

			if (!mob.collision(0, yMoveOffset + 32) && blinkTimer == cooldown) {
				
				spawnParticle.blinkParticle(spawnX, spawnY, 20, 50, level, 1, 1);
				blinkTimer = 0;
				mob.entityY += 32;

			}
		} else if (-135 > Math.toDegrees(radian) && Math.toDegrees(radian) > -179
				|| 135 < Math.toDegrees(radian) && Math.toDegrees(radian) < 180) {
			if (!mob.collision(xMoveOffset - 32, 0) && blinkTimer == cooldown) {
				 spawnParticle.blinkParticle(spawnX, spawnY, 20, 50, level, 1, 1);			
				blinkTimer = 0;
				mob.entityX -= 32;
			}
		}
	}
	public boolean updateCooldown() {
		boolean ready = false;
		if (blinkTimer < cooldown) {
			blinkTimer++;	
		} else {
			ready = true;
		}
		return ready;
	}

	public void render(Screen screen) {
		// TODO Auto-generated method stub

	}
}
