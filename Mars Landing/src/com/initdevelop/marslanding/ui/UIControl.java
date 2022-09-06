package com.initdevelop.marslanding.ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class UIControl {
	
	int relx = 0;
	int rely = 0;
	
	int width = 10;
	int height = 10;
	
	String name = "default";
	String text = "text";
	
	public void paint(Graphics g) {}
	
	public void mouseDownEvent(int mouseInput, int x, int y) {}
	public void mouseUpEvent(int mouseInput, int x, int y) {}
	public void mouseClickEvent(int mouseInput, int x, int y) {}
	public void mouseEnterEvent(int x, int y) {}
	public void mouseExitEvent(int x, int y) {}
	
	public void mouseMoveEvent(int x, int y) {}
	public void mouseDragEvent(int mouseInput, int xi, int yi, int xf, int yf) {}
	
	public void keyDownEvent(KeyEvent keyInput) {}
	public void keyUpEvent(KeyEvent keyInput) {}
	public void keyPressEvent(KeyEvent keyInput) {}
	
}
