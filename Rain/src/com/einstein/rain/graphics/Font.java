package com.einstein.rain.graphics;

public class Font {

	private static Spritesheet font = new Spritesheet("/Fonts/Arial.png", 16);
	// private static Spritesheet font_characters = new Spritesheet("font", 16);
	private static Sprite[] characters = Sprite.split(font);
	private static String charIndex = "ABCDEFGHIJKLM" + //
			"NOPQRSTUVWXYZ" + //
			"abcdefghijklm" + //
			"nopqrstuvwxyz" + //
			"0123456789.,'" + //
			"`\"\";:!@$%()-+";
	// TODO: EXTRACT SHEET CELLS INTO INDIVIDUAL SPRITES

	public Font() {

	}

	public void render(int x, int y, String text, Screen screen) {
		render(x, y, 0,0, text, screen);
	}

	public void render(int x, int y, int color, String text, Screen screen) {
		render(x, y, 0, color, text, screen);
	}
	public void render(int x, int y, int spacing, int color, String text, Screen screen ) {
		
		int xOffset = 0;
		int line = 0;
		for (int i = 0; i < text.length(); i++) {	
			xOffset += 16 + spacing;
			int yOffset = 0;
			char currentChar = text.charAt(i);
			if (currentChar == 'g' || currentChar == 'y' || currentChar == 'j' || currentChar == 'p' || currentChar == ',') yOffset = 3;
			if(currentChar == '\n') {
				xOffset = 0;
				line++;
			}
			int index = charIndex.indexOf(currentChar);
			if (index == -1) continue;

			screen.renderTextChar(x + xOffset, y + line * 20  + yOffset, characters[index], false, color);
		}

	}
}
