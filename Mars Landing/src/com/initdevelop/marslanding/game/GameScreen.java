package com.initdevelop.marslanding.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.initdevelop.marslanding.textures.FontTexture;
import com.initdevelop.marslanding.textures.Textures;

public class GameScreen {

	public static int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public static int BW = (int)(SCREEN_WIDTH / 32);
	public static int BH = (int)(SCREEN_HEIGHT / 18);
	
	public JFrame Screen;
	public GamePanel gamePanel;
	public Container container;
	public Dimension size;
	public static GameStateManager gameStateManager;
	public boolean gameStateLoaded = false;
	
	public GameScreen() {
		Screen = new JFrame("Game");
		
		gameStateManager = new GameStateManager();
		
		gameStateLoaded = true;
		size = Toolkit.getDefaultToolkit().getScreenSize();
		Screen.setUndecorated(true);
		Screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Screen.setSize(size);
		Screen.setEnabled(true);
		Screen.setLocation(0, 0);
		Screen.setResizable(false);
		Screen.setVisible(true);
		
		gamePanel = new GamePanel();
		new FontTexture();
		new Textures();
		
		container = Screen.getContentPane();
		container.add(gamePanel);
		
		Screen.pack();
		
		
		gamePanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				GameStateManager.gameStates[GameStateManager.getGameStateID()].mouseClickEvent(e.getButton(),
						e.getX(), e.getY());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				GameStateManager.gameStates[GameStateManager.getGameStateID()].mouseDownEvent(e.getButton(),
						e.getX(), e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				GameStateManager.gameStates[GameStateManager.getGameStateID()].mouseUpEvent(e.getButton(),
						e.getX(), e.getY());
			}

			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		
		gamePanel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {}

			@Override
			public void mouseMoved(MouseEvent e) {
				GameStateManager.gameStates[GameStateManager.getGameStateID()].mouseMoveEvent(e.getX(), e.getY());
			}
			
		});
		
		Screen.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				GameStateManager.gameStates[GameStateManager.getGameStateID()].keyPressEvent(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				GameStateManager.gameStates[GameStateManager.getGameStateID()].keyDownEvent(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				GameStateManager.gameStates[GameStateManager.getGameStateID()].keyUpEvent(e);
			}
			
		});
		
		
	}
	
	public void setUpScreen() {}

	
	public class GamePanel extends JPanel {
		
		private static final long serialVersionUID = 1L;
		public boolean isLoaded = false;
		
		public GamePanel() {
			setPreferredSize(size);
			setDoubleBuffered(true);
		}
		
		public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        if (gameStateLoaded) {
	        	GameStateManager.gameStates[GameStateManager.getGameStateID()].paint(g);
	        }
		}
		
	}
	
}
