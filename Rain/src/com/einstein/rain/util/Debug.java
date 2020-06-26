package com.einstein.rain.util;

import java.util.ArrayList;
import java.util.List;

import com.einstein.rain.graphics.Screen;

public class Debug {
	
	public List<Integer> frames = new ArrayList<Integer>();
	
	public Debug() {

	}

	public static void drawRect(Screen screen, int x, int y, int width, int height, boolean fixed) {
		
		drawRect(screen, x, y, width, height, 0xFF0000, fixed);
	}

	public static void drawRect(Screen screen, int x, int y, int width, int height, int color, boolean fixed) {
		screen.drawRect(x, y, width, height, color, fixed);
	}
	public int avgFps(int frameRate){
		frames.add(frameRate);
		int sumOfFrames = 0;
		for(int i = 0; i < frames.size(); i++) {
			
			if(frames.size() > 1 ) {
				
				sumOfFrames = frames.get(i) + sumOfFrames ;
			}
			else if(frames.size() == 1) {
				sumOfFrames = frames.get(i);
			}	
		}
		int avgFrames = sumOfFrames / frames.size();
		System.out.println("Frame: " + sumOfFrames + " Average FrameRate Over" + frames.size() + " Seconds = " + avgFrames );
		return sumOfFrames;
		
	}
}
