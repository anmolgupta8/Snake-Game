import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	private int[] snakexlength = new int[750];
	private int[] snakeylength = new int[750];
	
	private int lengthOfSnake = 3;
	
	private int[] enemyxpos = new int[34];
	private int[] enemyypos = new int[23];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon titleImage; 
	private ImageIcon rightMouth;
	private ImageIcon leftMouth;
	private ImageIcon upMouth;
	private ImageIcon downMouth;
	private ImageIcon snakeImage;
	
	private ImageIcon enemyimage;
	private Random random = new Random();
	
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	private int moves = 0;
	private int score = 0;
	
	private Timer timer;
	private int delay = 100;
	
	public Gameplay() {
		enemyxpos[0] = 25;
		for(int i=1;i<34;i++) {
			enemyxpos[i] = enemyxpos[i-1]+25;
		}
		enemyypos[0] = 75;
		for(int i=1;i<23;i++) {
			enemyypos[i] = enemyypos[i-1]+25;
		}
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		
		if(moves == 0) {
			snakexlength[2] = 50;
			snakexlength[1] = 75;
			snakexlength[0] = 100;
			
			snakeylength[2] = 100;
			snakeylength[1] = 100;
			snakeylength[0] = 100;
		}
		
		// draw the title image
		titleImage = new ImageIcon("../snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		// draw background for the gameplay
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		// draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		// draw border for gameplay
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		
		// draw scores
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Score : " + score,780,30);
		
		rightMouth = new ImageIcon("../rightmouth.png");
		rightMouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		
		for(int i=0;i<lengthOfSnake;i++) {
			if(i==0 && right) {
				rightMouth = new ImageIcon("../rightmouth.png");
				rightMouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			if(i==0 && left) {
				leftMouth = new ImageIcon("../leftmouth.png");
				leftMouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			if(i==0 && up) {
				upMouth = new ImageIcon("../upmouth.png");
				upMouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			if(i==0 && down) {
				downMouth = new ImageIcon("../downmouth.png");
				downMouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			if(i!=0) {
				snakeImage = new ImageIcon("../snakeimage.png");
				snakeImage.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
		}
		
		enemyimage = new ImageIcon("../enemy.png");
		if(enemyxpos[xpos] == snakexlength[0] && enemyypos[ypos] == snakeylength[0]) {
			lengthOfSnake++;
			score++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
			for(int i=0;i<lengthOfSnake;i++) {
				if(snakexlength[i] == enemyxpos[xpos] && snakeylength[i] == enemyypos[ypos]) {
					xpos = random.nextInt(34);
					ypos = random.nextInt(23);
				}
			}
		}
		enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
		
		for(int i=1;i<lengthOfSnake;i++) {
			if(snakexlength[i] == snakexlength[0] && snakeylength[i] == snakeylength[0]) {
				right = false;
				left = false;
				up = false;
				down = false;
				
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString("Game Over !!",300,300);
				
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD,20));
				g.drawString("Enter Space to Restart",350,340);
			}
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right) {
			for(int i=lengthOfSnake-1;i>=0;i--) {
				snakeylength[i+1] = snakeylength[i];
			}
			for(int i=lengthOfSnake;i>=0;i--) {
				if(i==0) {
					snakexlength[i] = snakexlength[i]+25;
				}
				else snakexlength[i] = snakexlength[i-1];
				if(snakexlength[i]>850) snakexlength[i] = 25;
			}
			repaint();
		}
		if(left) {
			for(int i=lengthOfSnake-1;i>=0;i--) {
				snakeylength[i+1] = snakeylength[i];
			}
			for(int i=lengthOfSnake;i>=0;i--) {
				if(i==0) {
					snakexlength[i] = snakexlength[i]-25;
				}
				else snakexlength[i] = snakexlength[i-1];
				if(snakexlength[i]<25) snakexlength[i] = 850;
			}
			repaint();
		}
		if(down) {
			for(int i=lengthOfSnake-1;i>=0;i--) {
				snakexlength[i+1] = snakexlength[i];
			}
			for(int i=lengthOfSnake;i>=0;i--) {
				if(i==0) {
					snakeylength[i] = snakeylength[i]+25;
				}
				else snakeylength[i] = snakeylength[i-1];
				if(snakeylength[i]>625) snakeylength[i] = 75;
			}
			repaint();
		}
		if(up) {
			for(int i=lengthOfSnake-1;i>=0;i--) {
				snakexlength[i+1] = snakexlength[i];
			}
			for(int i=lengthOfSnake;i>=0;i--) {
				if(i==0) {
					snakeylength[i] = snakeylength[i]-25;
				}
				else snakeylength[i] = snakeylength[i-1];
				if(snakeylength[i]<75) snakeylength[i] = 625;
			}
			repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			moves = 0;
			score = 0;
			lengthOfSnake = 3;
			repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			if(!left) right = true;
			else right = false;
			up = false;
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			if(!right) left = true;
			else left = false;
			up = false;
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			if(!down) up = true;
			else up = false;
			left = false;
			right = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			if(!up) down = true;
			else down = false;
			left = false;
			right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
