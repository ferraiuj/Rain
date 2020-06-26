package com.einstein.rain.util;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import static com.einstein.rain.util.MathUtil.*;

public class ImageUtils {
	
	private ImageUtils() {

	}

	//Returns a NEW image
	public static BufferedImage bright(BufferedImage original, int amount) {
		BufferedImage result = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);

		byte[] pixels = ((DataBufferByte) original.getRaster().getDataBuffer()).getData();
		int[] pixelsUpdate = ((DataBufferInt) result.getRaster().getDataBuffer()).getData();

		int offset = 0;
		
		for (int yy = 0; yy < original.getHeight(); yy++) {
			for (int xx = 0; xx < original.getWidth(); xx++) {
				
				int a = Byte.toUnsignedInt(pixels[offset++]);
				int r = Byte.toUnsignedInt(pixels[offset++]);
				int g = Byte.toUnsignedInt(pixels[offset++]);
				int b = Byte.toUnsignedInt(pixels[offset++]);

				r = clamp(r + amount, 0, 255);
				g = clamp(g + amount, 0, 255);
				b = clamp(b + amount, 0, 255);

				
				pixelsUpdate[xx + yy * result.getWidth()] = a << 24 | b << 16 | g << 8 | r;
			}

		}
		return result;
	}
	public static BufferedImage updateMap(BufferedImage original, int color, int mouseX, int mouseY) {
		BufferedImage result = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);

		byte[] pixels = ((DataBufferByte) original.getRaster().getDataBuffer()).getData();
		int[] pixelsUpdate = ((DataBufferInt) result.getRaster().getDataBuffer()).getData();

		int offset = 0;
		
		for (int yy = 0; yy < original.getHeight(); yy++) {
			for (int xx = 0; xx < original.getWidth(); xx++) {
				
			}
		}
		pixelsUpdate[mouseX + mouseY * result.getWidth()] = color; //dont accidentally delete level
		return result;
	}
	public int[] setColor(int [] pixels, int color) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
		return pixels;
	}
}