package com.einstein.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.einstein.rain.entity.mob.Chaser;
import com.einstein.rain.entity.mob.Dummy;
import com.einstein.rain.entity.mob.Mouset;
import com.einstein.rain.entity.mob.Shooter;
import com.einstein.rain.entity.mob.Smart;
import com.einstein.rain.entity.spawner.mobspawner.SmartSpawner;
import com.einstein.rain.graphics.CameraControl;

public class SpawnLevel extends Level {

	public SpawnLevel(String path) {
		super(path);
		
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			//image.
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not lead image file");

		} 
		
		add(new Dummy(22, 55));
		add(new Shooter(27, 48));
		add(new Shooter(27, 55));
		smartSpawner = new SmartSpawner();
		smartSpawner.spawnSmartMobCluster(26, 55, 5, this);

		for (int i = 0; i < 5; i++) {
			add(new Chaser(5, 55));

		}
	}

	// Fire = FF0000 grassmin = 30FF30 ash = FFFB35 tallGrass1 = FF1EEC
	// grassnext2 = FFFB35 grassnext3 = 7F3300 grassnext4 = 0 wave = 161AFF
	protected void generateLevel() {

	}

}
