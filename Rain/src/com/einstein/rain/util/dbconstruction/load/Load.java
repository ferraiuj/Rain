package com.einstein.rain.util.dbconstruction.load;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.einstein.rain.graphics.Font;
import com.einstein.rain.graphics.UserInterface;
import com.einstein.rain.input.Mouse;
import com.einstein.rain.util.dbconstruction.FindDirectory;
import com.einstein.rain.util.dbconstruction.load.ddatabases.LoadEntities;
import com.einstein.rain.util.dbconstruction.load.ddatabases.LoadLevel;
import com.einstein.rain.util.dbconstruction.load.ddatabases.LoadLocation;
import com.einstein.rain.util.dbconstruction.load.ddatabases.LoadSettings;
import com.jacob.serialize.deserialization.ddatabase.DDataBase;

public class Load extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static int width = 300;// 300
	private static int height = 168;// 168
	private static int scale = 3;

	private static final long serialVersionUID = 1L;
	// private Thread thread;
	// private boolean running = false;

	public Font font;
	public Mouse mouse;
	public FindDirectory direct;
	public LoadLocation loadLocation;
	public LoadSettings loadSettings;
	public LoadLevel loadLevel;
	public LoadEntities loadEntities;
	protected UserInterface userInterface;
	protected static final int TILE_SIZE = 16;

	private static final String PLAYER_DATA_FILE = "PlayerData.pd";
	private static final String SETTINGS_FILE = "Settings.pd";
	private static final String UIDETAILS_FILE = "UIDetails.pd";
	private static final String LEVEL_DATA_FILE = "LevelData.pd";
	private static final String LOCATIONS_FILE = "Locations.pd";
	
	public DDataBase database;
	
	public Load() {

		loadSettings = new LoadSettings(SETTINGS_FILE);
		loadLevel = new LoadLevel(LEVEL_DATA_FILE);
		loadEntities = new LoadEntities(PLAYER_DATA_FILE, loadLevel.level, loadSettings.key, loadSettings.screen);
		userInterface = new UserInterface(loadLevel.level);
		font = new Font();
		mouse = new Mouse(loadEntities.mouset, loadLevel.level);
	}

	public void update() {

		loadSettings.key.update();
		loadLevel.level.update(loadSettings.screen);
	}

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	public void render(BufferStrategy bs) {

		loadLevel.level.render(loadSettings.screen, loadEntities.mouset);
		//font.render(50, 50, 0, "ACED", loadSettings.screen);
		loadSettings.loadScreen(image);
		// loadEntities.levelRender(loadSettings.screen);
		Graphics g = bs.getDrawGraphics();
		
		//g.setColor(new Color(0xffFF00FF));
		//g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, width * scale, height * scale, null);
		//userInterface.render(g);

		g.dispose();
		bs.show();

	}

	// Use these below classes when i use pointer arrays
	public DDataBase getPlayerData() {

		database = DDataBase.DeserializeFile(PLAYER_DATA_FILE, (short) 0);
		return database;

	}

	public DDataBase getLocations() {

		database = DDataBase.DeserializeFile(LOCATIONS_FILE, (short) 0);
		return database;

	}

	public DDataBase getSettings() {
		database = DDataBase.DeserializeFile(SETTINGS_FILE, (short) 0);
		return database;

	}

	public DDataBase getLevelData() {
		database = DDataBase.DeserializeFile(LEVEL_DATA_FILE, (short) 0);
		return database;

	}

	public DDataBase getUIDetails() {
		database = DDataBase.DeserializeFile(UIDETAILS_FILE, (short) 0);
		return database;

	}

	@Override
	public void run() {

	}

}
