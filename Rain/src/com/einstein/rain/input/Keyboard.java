package com.einstein.rain.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	public boolean[] keys = new boolean[120];
	public boolean up, right, down, left, blink, space;
	
	
	public void update(){
		
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		blink = keys[KeyEvent.VK_E];
		space = keys[KeyEvent.VK_SPACE];
		
	}

	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
	}

	
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
