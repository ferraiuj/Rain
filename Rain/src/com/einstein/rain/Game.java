package com.einstein.rain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;

import com.einstein.rain.util.Debug;
import com.einstein.rain.util.dbconstruction.FindDirectory;
import com.einstein.rain.util.dbconstruction.load.Load;
import com.einstein.rain.util.dbconstruction.save.Save;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static int width = 300;// 300
	private static int height = 168;// 168
	private static int scale = 3;

	/*
	 * things that are affected by changing the width and the height of the
	 * client need to scale to be able to accept different W/H of client The
	 * problem originates from complicated mathematical equations that require
	 * the height and width to be a certain size eg. tan2 before we can figure
	 * out how to solve this problem we have to learn how each error is related
	 * to the width and height
	 */

	public static String title = "Rain";
	public JFrame frame;
	private Thread thread; // GAME
	private boolean running = false; // GAME
	private Load load;
	public FindDirectory direct;
	private List<String> paths = new ArrayList<String>();
	private Debug debug;
	public static BufferedImage cursorImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

	public Game() {

//		direct = new FindDirectory();
//		paths = direct.listAllFilesWith();
//
//		System.out.println(paths.size());
//		for (int i = 0; i < paths.size(); i++) {
//			paths.get(i);
//		}
		frame = new JFrame();
		load = new Load();
		debug = new Debug();
		setPreferredSize(load.loadSettings.size);
		addKeyListener(load.loadSettings.key);
		addMouseListener(load.mouse);
		addMouseMotionListener(load.mouse);

	}

	public static int getWindowWidth() {
		return width * scale;
	}

	public static int getWindowHeight() {
		return height * scale;
	}
	/* synchronized prevents potentially deadly overlapping or threads */

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() { // synchronized prevents potentially
										// deadly overlapping or threads
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private static long timeStamp = 0;
	public void run() {

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();

		while (running == true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				load.update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				timeStamp++;
				frame.setTitle(title + "  |  " + updates + " ups,  " + frames + "  fps");	
				debug.avgFps(frames);
				updates = 0;
				frames = 0;
			
			}
		}
	}
	public static void timeStamp() {
		System.out.println(timeStamp);
	}
	public void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		load.render(bs);

	}

	public static void main(String[] args) {

		Game game = new Game();
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.frame.setCursor(blankCursor);

		game.start();
	}
}