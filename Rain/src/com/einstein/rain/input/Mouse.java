package com.einstein.rain.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.einstein.rain.entity.mob.Mouset;
import com.einstein.rain.graphics.CameraControl;
import com.einstein.rain.graphics.Screen;
import com.einstein.rain.graphics.Sprite;
import com.einstein.rain.level.Level;

import javafx.scene.Camera;

public class Mouse implements MouseListener, MouseMotionListener {

	public Level level;
	public Mouset mouset;
	public Screen screen;
	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;
	private static boolean drag = false;
	private static boolean press = false;
	private static boolean release = false;
	private static boolean clicked = false;
	private int tempY;
	public double xm, ym;

	public Mouse(Mouset mouset, Level level) {
		this.level = level;
		this.mouset = mouset;
	}

	public static int getX() {
		return mouseX;
	}

	public static int getY() {
		return mouseY;
	}

	public static int getB() {
		return mouseB;
	}

	public static boolean drag() {
		return drag;
	}

	public static boolean press() {
		return press;
	}

	public static boolean release() {
		return release;
	}

	public static boolean clicked() {
		return clicked;
	}

	public void getMouseEntityX() {
		// camera.moveCamera();
	}

	public void getMouseEntityY() {

	}

	public void mouseMoveOperation() {

	}

	public void mouseDragged(MouseEvent e) {

		mouseX = e.getX();
		mouseY = e.getY();
		mouset.mouseCameraMove(mouseX, mouseY);
		drag = true;
		release = false;
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		release = false;
		mouset.mousetMoved(mouseX, mouseY);
	}

	public void mouseClicked(MouseEvent e) {
		
		clicked = true;
		release = false;
		System.out.println("clicked");
	}

	public void mouseEntered(MouseEvent e) {
		mouseX = (e.getX());
		mouseY = e.getY();
		System.out.println("FUCKING ENTERED");
	}

	public void mouseExited(MouseEvent e) {
		mouset.mouseReset();
	}

	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		mouset.mousetPressed();
		press = true;
		release = false;
		mouseB = e.getButton();
	}

	public void mouseReleased(MouseEvent e) {
		
		mouseX = e.getX();
		mouseY = e.getY();
		mouseB = MouseEvent.NOBUTTON;
		drag = false;
		press = false;
		release = true;
		System.out.println("released");
		mouset.mousetReleased(mouseX, mouseY);

	}

}
