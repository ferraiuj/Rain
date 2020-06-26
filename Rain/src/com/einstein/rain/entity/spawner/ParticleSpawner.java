package com.einstein.rain.entity.spawner;

import com.einstein.rain.entity.Particle.BlinkEffect;
import com.einstein.rain.entity.Particle.Creative;
import com.einstein.rain.entity.Particle.ExplosionEffect;
import com.einstein.rain.entity.Particle.TraceMouse;
import com.einstein.rain.entity.mob.Player;
import com.einstein.rain.level.Level;
import com.einstein.rain.util.Colors;

public class ParticleSpawner {

	// REMEMBER TO EXCLUDE FURTHER PARTICLES FROM getEntities IN LEVEL
	// this class can only bug if you're creating a new particle
	@SuppressWarnings("unused")
	private int life;
	private Colors color;

	public ParticleSpawner() {
		color = new Colors();
	}

	public void blinkParticle(int x, int y, int life, int amount, Level level, int width, int height) {

		for (int i = 0; i < amount; i++) {
			level.add(new BlinkEffect(x, y, life, width, height, color.fireColors()));

		}
	}

	public void explosionParticle(int x, int y, int life, int amount, Level level, int width, int height) {

		for (int i = 0; i < amount; i++) {
			level.add(new ExplosionEffect(x, y, life, width, height, color.fireColors()));

		}
	}
	public void traceMouse(int x, int y, int life, int amount, Level level, int width, int height) {

		for (int i = 0; i < amount; i++) {
			level.add(new TraceMouse(x, y, life, width, height, color.fireColors()));

		}
	}
	public void creativeParticle(int x, int y, int life, int amount, Level level, int width, int height) {

		for (int i = 0; i < amount; i++) {
			level.add(new Creative(x, y, life, width, height, color.waterColors()));

		}
	}
}