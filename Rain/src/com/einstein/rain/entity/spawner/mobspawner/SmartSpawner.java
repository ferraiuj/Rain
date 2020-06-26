package com.einstein.rain.entity.spawner.mobspawner;

import com.einstein.rain.entity.mob.Chaser;
import com.einstein.rain.entity.mob.Smart;
import com.einstein.rain.entity.spawner.Spawner;
import com.einstein.rain.entity.spawner.Spawner.Type;
import com.einstein.rain.level.Level;

public class SmartSpawner {

	public SmartSpawner() {
		//super(x, y, Type.MOB, amount, level);
		//this.level = level;
	}
	public void spawnSmartMobCluster(int x, int y, int amount, Level level) {
		
		for (int i = 0; i < amount; i++) {
			
			level.add(new Smart(x, y));
		}
	}
}
