package com.einstein.rain.graphics;

public class AnimatedSprite extends Sprite {

	private int frame = 0;
	private Sprite sprite;
	private int rate = 5;
	private int time = 0;
	private int length = -1;

	public AnimatedSprite(Spritesheet sheet, int width, int height, int length) {
		super(sheet, width, height);
		this.length = length;
		sprite = sheet.getSprites()[0];
		if (length > sheet.getSprites().length) System.err.println("error! length of anim is too long");

	}

	public void update() { 
		time++;
		if (time % rate == 0) {
			if (frame >= length - 1)
				frame = 0;
			else
				frame++;
			sprite = sheet.getSprites()[frame];
		}
		
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setFrameRate(int frames) {
		rate = frames;
	}

	public void setFrame(int index) {
		if (index > sheet.getSprites().length - 1) {
			System.err.println("ERROR, Index out of bounds in" + this);
			return;

		}
		sprite = sheet.getSprites()[index];
	}
}