package com.initdevelop.marslanding.game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Vector;

import com.initdevelop.marslanding.ui.UIControl;

public class GameState {
	
	public String stateName;
	public int stateID;
	public Vector<UIControl> controls = new Vector<UIControl>();
	protected int BW;
	protected int BH;
	
	public GameState() {
		BW = GameScreen.BW;
		BH = GameScreen.BH;
	}
	
	public void paint(Graphics g) {}
	public void paintComponents(Graphics g) {
		for (UIControl uc : controls) {
			uc.paint(g);
		}
	}
	
	public void update() {
		
	}
	
	public void mouseDownEvent(int mouseInput, int x, int y) {
		for (UIControl uc : controls) {
			uc.mouseDownEvent(mouseInput, x, y);
		}
	}
	
	public void mouseUpEvent(int mouseInput, int x, int y) {
		for (UIControl uc : controls) {
			uc.mouseUpEvent(mouseInput, x, y);
		}
	}
	
	public void mouseClickEvent(int mouseInput, int x, int y) {
		for (UIControl uc : controls) {
			uc.mouseClickEvent(mouseInput, x, y);
		}
	}
	
	public void mouseEnterEvent(int x, int y) {}
	public void mouseExitEvent(int x, int y) {}
	
	public void mouseMoveEvent(int x, int y) {
		for (UIControl uc : controls) {
			uc.mouseMoveEvent(x, y);
		}
	}
	
	
	public void keyDownEvent(KeyEvent keyInput) {
		for (UIControl uc : controls) {
			uc.keyDownEvent(keyInput);
		}
	}
	
	public void keyUpEvent(KeyEvent keyInput) {
		for (UIControl uc : controls) {
			uc.keyUpEvent(keyInput);
		}
	}
	
	public void keyPressEvent(KeyEvent keyInput) {
		for (UIControl uc : controls) {
			uc.keyPressEvent(keyInput);
		}
	}
	
	protected int W(int n) { return BW * n; }
	protected int W() { return BW; }
	
	protected int H(int n) { return BH * n; }
	protected int H() { return BH; }
	
}
