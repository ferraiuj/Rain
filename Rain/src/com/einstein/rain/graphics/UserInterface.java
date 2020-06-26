package com.einstein.rain.graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.einstein.rain.Game;
import com.einstein.rain.entity.Entity;
import com.einstein.rain.entity.mob.Player;
import com.einstein.rain.graphics.ui.UIActionListener;
import com.einstein.rain.graphics.ui.UIButton;
import com.einstein.rain.graphics.ui.UIButtonListener;
import com.einstein.rain.graphics.ui.UILabel;
import com.einstein.rain.graphics.ui.UIManager;
import com.einstein.rain.graphics.ui.UIPanel;
import com.einstein.rain.graphics.ui.UIProgressBar;
import com.einstein.rain.level.Level;
import com.einstein.rain.util.ImageUtils;
import com.einstein.rain.util.Vector2i;
import com.einstein.rain.util.dbconstruction.load.Load;
import com.einstein.rain.util.dbconstruction.save.database.PlayerData;
import com.jacob.databaseapp.Start;
import com.einstein.rain.util.dbconstruction.save.Save;

public class UserInterface {

	private UIManager uiManager;
	private UIProgressBar uiHealthBar;
	private UIButton button;
	private UIPanel panel;
	protected int health; // temporary variable
	private BufferedImage image, imageHover;
	private Level level;
	private int time;
	public UserInterface(final Level level) {
		this.level = level;
		uiManager = new UIManager();
		addMainPanel();
		addNameLabel();
		addButton();
		addHealthBar();
		addExitButton();
		// Overriding default function of button pressed

		// button.setButtonListener(new UIButtonListener(){
		// public void mPressed(UIButton button) {
		// super.mPressed(button);
		// button.performAction();
		// button.ignoreNextPress();
		// }
		// });
	}
	public void update() {
		uiManager.update();
		uiHealthBar.setProgress((time++ % 100) / 100.0);
	}
	public void render(Graphics g) {
		uiManager.render(g);
	}
	public void addMainPanel() {
		panel = (UIPanel) new UIPanel(new Vector2i(0 * 3, 0 * 3), new Vector2i(80 * 3, 168 * 3)).setColor(0xff4f4f4f);
		uiManager.addPanel(panel);
	}

	public void addHealthBar() {
		health = 100;
		
		uiHealthBar = new UIProgressBar(new Vector2i(10, 220), new Vector2i(80 * 3 - 20, 25));
		
		uiHealthBar.setColor(0xff6a6a6a);
		uiHealthBar.setForeGroundColor(0xffff3a3a);
		
		panel.addComponent(uiHealthBar);

		UILabel hpLabel = new UILabel(new Vector2i(uiHealthBar.position).add(new Vector2i(2, 18)), "HP");
		
		hpLabel.setColor(0xffffffff);
		hpLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
		
		panel.addComponent(hpLabel);
		
		uiHealthBar.setProgress(health / 100.0);
	}

	public void addNameLabel() {
		
		UILabel nameLabel = new UILabel(new Vector2i(40, 200), "Josef");
		
		nameLabel.setColor(0xffbbbbbb);
		nameLabel.setFont(new Font("Helvetica", Font.PLAIN, 24));
		nameLabel.dropShadow = true;
		panel.addComponent(nameLabel);
	}

	public void addExitButton() {
		try {
			image = ImageIO.read(new File("res/UI/xButton.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		imageHover = ImageUtils.bright(image, -255);
		UIButton imageButton = new UIButton(new Vector2i(10, 360), image, new UIActionListener() {

			public void perform() {

				Save data = new Save(level);
				data.construct();

				System.exit(0);
			}
		});
		imageButton.setButtonListener(new UIButtonListener() {
			public void mEntered(UIButton button) {
				button.setImage(imageHover);
			}

			public void mExited(UIButton button) {
				button.setImage(image);
			}
		});
		panel.addComponent(imageButton);
		// adding this reduces fps by about 300
	}

	public void addButton() {
		button = new UIButton(new Vector2i(10, 260), new Vector2i(120, 40), new UIActionListener() {

			public void perform() {
				Start data = new Start();
				data.dataBaseAppStart();
				System.out.println("Button Pressed");
			}
		});
		button.setText("hello");
		panel.addComponent(button);
	}

}
