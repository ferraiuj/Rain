package com.einstein.rain.graphics.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.einstein.rain.input.Mouse;
import com.einstein.rain.util.Vector2i;

public class UIButton extends UIComponents {

	private Image image;
	private UIButtonListener buttonListener;
	private UIActionListener actionListener;
	private boolean inside = false;
	private boolean pressed = false;
	private boolean ignorePressed = false;
	private boolean ignoreAction = false;
	public UILabel label;

	public UIButton(Vector2i position, Vector2i size, UIActionListener actionListener) {
		super(position, size);
		this.actionListener = actionListener;
		Vector2i labelPos = new Vector2i(position);
		labelPos.x += 4;
		labelPos.y += size.y - 8;
		label = new UILabel(labelPos, "");
		label.active = false;
		label.setColor(0xff444444);
		init();
	}

	public UIButton(Vector2i position, BufferedImage image, UIActionListener actionListener) {
		super(position, new Vector2i(image.getWidth(), image.getHeight()));
		this.actionListener = actionListener;
		setImage(image);
		init();
	}

	private void init() {
		setColor(0xffAAAAAA);
		buttonListener = new UIButtonListener();

	}

	void init(UIPanel panel) {
		super.init(panel);
		if (label != null) panel.addComponent(label);
	}

	public void setButtonListener(UIButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setText(String text) {
		if (text == "") {
			label.active = false;

		} else
			label.text = text;
	}

	public void performAction() {
		actionListener.perform();

	}

	public void ignoreNextPress() {
		ignoreAction = true;
	}

	public void update() {
		Rectangle rect = new Rectangle(getAbsolutePosition().x, getAbsolutePosition().y, size.x, size.y);
		boolean leftMouseButtonDown = Mouse.getB() == MouseEvent.BUTTON1;
		if (rect.contains(new Point(Mouse.getX(), Mouse.getY()))) {
			if (!inside) {
				if (leftMouseButtonDown) {
					ignorePressed = true;
				} else
					ignorePressed = false;

				buttonListener.mEntered(this);
			}
			inside = true;
			if (!pressed && !ignorePressed && leftMouseButtonDown) {

				buttonListener.mPressed(this);
				pressed = true;

			} else if (Mouse.getB() == MouseEvent.NOBUTTON) {
				if (pressed) {
					buttonListener.mReleased(this);
					if (!ignoreAction) {
						actionListener.perform();
					} else {
						ignoreAction = false;
					}
					pressed = false;
				}
				ignorePressed = false;
			}
		} else {

			if (inside) {
				buttonListener.mExited(this);
				pressed = false;
			}
			inside = false;

		}

	}

	public void render(Graphics g) {
		int x = position.x;
		int y = position.y;
		if (image != null) {
			g.drawImage(image, x, y, null);
		} else {
			g.setColor(color);
			g.fillRect(x, y, size.x, size.y);

			if (label != null) label.render(g);
		}
	}
}
