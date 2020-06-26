package com.einstein.rain.graphics.ui;

import java.awt.Color;
import java.awt.Graphics;

import com.einstein.rain.util.Vector2i;

public class UIComponents {

	public Vector2i position, size;
	protected Vector2i offset;
	public Color color;
	public boolean active = true;
	protected UIPanel panel;

	public UIComponents(Vector2i position) {
		this.position = position;
		offset = new Vector2i();
	}

	public UIComponents(Vector2i position, Vector2i size) {
		this.position = position;
		this.size = size;
		offset = new Vector2i();
	}

	void init(UIPanel panel) {
		this.panel = panel;
	}

	public UIComponents setColor(int color) {
		this.color = new Color(color);
		return this;
	}

	public void update() {

	}

	public void render(Graphics g) {

	}
	public Vector2i getAbsolutePosition() {
		return new Vector2i (position).add(offset);
	}

	void setOffset(Vector2i offset) {
		this.offset = offset;
	}

}
