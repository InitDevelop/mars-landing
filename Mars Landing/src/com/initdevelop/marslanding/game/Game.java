package com.initdevelop.marslanding.game;

public class Game {
	
	public static GameThread gameThread;
	public static GameScreen gameScreen;
	public static TickManager tickManager;
	boolean frameLoaded = false;
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		gameThread = new GameThread();
		gameThread.start();
		
		tickManager = new TickManager();
		tickManager.start();
		
	}
	
	public class TickManager extends Thread {
		
		public int TICKS = 0;
		
		public void run() {
			while(true) {
				try {
					sleep(20);
					TICKS++;
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
				if (TICKS % 50 == 0) {
					IO.print("Game Ticks : " + TICKS + " TICKS");
				}
				
				try {
					if (frameLoaded) {
						gameScreen.gamePanel.repaint();
						GameStateManager.gameStates[GameStateManager.getGameStateID()].update();
					}
				} catch (Exception e) {}
			}
		}
	}
	
	public class GameThread extends Thread {
		public void run() {
			gameScreen = new GameScreen();
			IO.print("GameScreen Loading Completed.");
			frameLoaded = true;
		}
	}
	
}
