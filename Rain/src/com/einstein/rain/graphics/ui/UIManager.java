package com.einstein.rain.graphics.ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.einstein.rain.level.Level;

public class UIManager {


	private List<UIPanel> panels = new ArrayList<UIPanel>();

	public UIManager() {
		System.out.println("hey");
		
	}
	public void addPanel(UIPanel panel) {
		panels.add(panel);
	}

	public void update() {
		
		for (UIPanel panel : panels) {
			
			panel.update();
		}
	}

	public void render(Graphics g) {
		
		for (UIPanel panel : panels) {
			panel.render(g);
		}
	}
}
