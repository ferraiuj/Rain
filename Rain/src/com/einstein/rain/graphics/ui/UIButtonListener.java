package com.einstein.rain.graphics.ui;

public class UIButtonListener {

	public void mEntered(UIButton button) {
		button.setColor(0xffcdcdcd);
	}
	public void mExited(UIButton button) {
		button.setColor(0xffaaaaaa);
	}
	public void mPressed(UIButton button) {
		button.setColor(0xffcc2222);
		//System.out.println("pressed");
	}
	public void mReleased(UIButton button) {
		button.setColor(0xffcdcdcd);
		//System.out.println("released");

	}
	
}
