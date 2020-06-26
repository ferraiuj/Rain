package com.einstein.rain.graphics.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.einstein.rain.util.Vector2i;

public class UIPanel extends UIComponents {
	
	private List<UIComponents > components = new ArrayList<UIComponents>();
	private Vector2i size;

	
	public UIPanel(Vector2i position, Vector2i size) {
		super(position);
		this.position = position;
		this.size = size;
		color = new Color(0xffcacaca);
	}
	public void addComponent(UIComponents component) {
		component.init(this);
		components.add(component);
	}
	public void update() {
		for (UIComponents component : components ) {
			 component.setOffset(position);
			 component.update();
		}
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(position.x, position.y, size.x, size.y);
		
		for (UIComponents component : components ) {
			 component.render(g);
		}
	}
}
