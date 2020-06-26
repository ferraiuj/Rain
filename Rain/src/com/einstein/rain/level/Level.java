package com.einstein.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.einstein.rain.entity.Entity;
import com.einstein.rain.entity.Particle.BlinkEffect;
import com.einstein.rain.entity.Particle.Particle;
import com.einstein.rain.entity.mob.Mouset;
import com.einstein.rain.entity.mob.Player;
import com.einstein.rain.entity.projectile.Projectile;
import com.einstein.rain.entity.spawner.ParticleSpawner;
import com.einstein.rain.entity.spawner.mobspawner.SmartSpawner;
import com.einstein.rain.graphics.MiniMap;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.input.Keyboard;
import com.einstein.rain.level.tile.Tile;

public class Level {
	
	public int width;
	public int height;
	protected int[] tilesInt;
	public Tile tile;
	public int[] tiles;
	public String path;
	public SmartSpawner smartSpawner;
	private BufferedImage image;
	public MiniMap miniMap;
	public Mouset mouset;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	//private List<ParticleSpawner> particles = new ArrayList<ParticleSpawner>();
	private List<Player> players = new ArrayList<Player>();
	public List<Tile> tilesList = new ArrayList<Tile>();

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
	}

	public Level(String path) {

		this.path = path;
		tile = new Tile();
		loadLevel(path);
		initTiles();	
		miniMap = new MiniMap();
		System.out.println("HELLO");
	}
	
	public void initTiles() {

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tilesList.add(tile.setTile(x, y, this));
				tilesList.get(x + y * 40).setTileColor(x, y, this);				
			}
		}		
	}
	protected void generateLevel() {

	}

	protected void loadLevel(String path) {
		smartSpawner = new SmartSpawner();
		try {
			image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not lead image file");
		}
	}
	public void update(Screen screen) {// update entity lists
		for (int i = 0; i < entities.size(); i++) {

			entities.get(i).update(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {

			projectiles.get(i).update(screen);
		}
		for (int i = 0; i < players.size(); i++) {

			players.get(i).update(screen);
		}
		remove();
	}
	private void remove() {

		for (int i = 0; i < entities.size(); i++) {

			if (entities.get(i).isRemoved())
				entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved())
				projectiles.remove(i);
		}
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isRemoved())
				players.remove(i);
		}
	}
	public void render(Screen screen, Mouset mouset) {

		screen.clear();
		screen.setOffset(mouset.getOffsetX(), mouset.getOffsetY());

		int xRenderArea = screen.getOffsetX() >> 4;
		int xCenterImage = (screen.getOffsetX() + screen.width + 16) >> 4;
		int yRenderArea = screen.getOffsetY() >> 4;
		int yCenterImage = (screen.getOffsetY() + screen.height + 16) >> 4;
		
		for (int y = yRenderArea; y < yCenterImage; y++) {
			for (int x = xRenderArea; x < xCenterImage; x++) {
				
				if (x < 0 || y < 0 || x >= width || y >= height) {
					tile.setTile(x, y, this).render(x, y, screen);		
				}
				else {
					tilesList.get(x + y * 40).render(x, y, screen);
					screen.drawRect(x << 4, y << 4, 16, 16, 0xFFFFFFFF, true);
				}
				 
				// // GRID VIEW
				// screen.drawRect(x << 4, y << 4 , 16, 16, 0xFFFFFFFF, false);
			}
		}
		mouset.tileMove(tilesList);
		miniMap.render(screen, this, tilesList);

		
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(screen);
		}

	}
	
	public void addMultiple(Entity e, int amount) { // some information to retrieve are stored from
		// this method due to init method of this entity
		// on this level
		e.init(this);

		if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else if (e instanceof Player) {
			players.add((Player) e);
		} else {
			entities.add(e);
		}
	}

	public void add(Entity e) { // some information to retrieve are stored from
								// this method due to init method of this entity
								// on this level
		e.init(this);

		if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else if (e instanceof Player) {
			players.add((Player) e);
		} else {
			entities.add(e);
		}
	}

	public List<Tile> getTiles(){
		return tilesList;
		
	}
	public Tile getTile(int x, int y, Level level){
		return tilesList.get(x + y * 40);
		
	}
	public List<Player> getPlayers() {
		return players;
	}

	public Player getPlayerAt(int index) {
		return players.get(index);

	}

	public Player getClientPlayer() {
		return players.get(0);
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public List<Entity> getEntities(Entity entity, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int entityX = entity.getX();
		int entityY = entity.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entityVicinity = entities.get(i);
			if (entityVicinity.equals(entity) || entityVicinity instanceof Particle
					|| entityVicinity instanceof BlinkEffect)
				continue;
			int entityVicinityX = entityVicinity.getX();
			int entityVicinityY = entityVicinity.getY();
			int distanceX = Math.abs(entityVicinityX - entityX);
			int distanceY = Math.abs(entityVicinityY - entityY);
			double distance = Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
			if (distance <= radius)
				result.add(entityVicinity);
		}
		return result;
	}

	public List<Player> getPlayers(Entity entity, int radius) {
		List<Player> result = new ArrayList<Player>();
		int entityX = entity.getX();
		int entityY = entity.getY();
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int playerX = player.getX();
			int playerY = player.getY();
			int distanceX = Math.abs(playerX - entityX);
			int distanceY = Math.abs(playerY - entityY);
			double distance = Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
			if (distance <= radius)
				result.add(player);
		}
		return result;
	}

	public List<String> getLevels() {
		// directory.listAllFilesWith();
		List<String> levelFileArray = new ArrayList<String>();

		for (int i = 0; i < levelFileArray.size(); i++) {
			levelFileArray.get(i);

		}
		return levelFileArray;
	}
}
