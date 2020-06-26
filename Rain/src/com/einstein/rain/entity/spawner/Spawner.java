package com.einstein.rain.entity.spawner;

import com.einstein.rain.entity.Entity;
import com.einstein.rain.graphics.Sprite;
import com.einstein.rain.level.Level;

public abstract class Spawner extends Entity {

	public enum Type {
		MOB, PARTICLE, ITEM;
	}

	private Type type;

	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level); 
		this.entityX = x;
		this.entityY = y;
		this.type = type;

	}

	public Sprite getSprite() {
		return sprite;
	}
	

}
