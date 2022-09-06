package com.initdevelop.marslanding.game;

import com.initdevelop.marslanding.gamestate.*;

public class GameStateManager {
	
	public static int currentStateID = 0;
	public static GameState[] gameStates;
	
	public GameStateManager() {
		gameStates = new GameState[2];
		gameStates[0] = new LoginState();
		gameStates[1] = new MapState();
	}
	
	public static void setGameState(int id) {
		currentStateID = id;
	}
	
	public static int getGameStateID() {
		return currentStateID;
	}
	
}
