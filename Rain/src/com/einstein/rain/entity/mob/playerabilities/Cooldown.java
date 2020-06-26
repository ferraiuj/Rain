package com.einstein.rain.entity.mob.playerabilities;

public class Cooldown {
	
	public Cooldown() {
		
	}
	public boolean updateCooldown(int time) {
		boolean ready = false;
			if (time < cooldown) {
				time++;
			}
			
			return ready;
		}
	
}
