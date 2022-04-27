import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class BlockGame {

	static class MyFrame extends JFrame{
		
		//constant
		static int BALL_WIDTH = 20;
		static int BALL_HEIGHT = 20;
		static int BLOCK_ROWS = 5;
		static int BLOCK_COLUMNS = 10;
		static int BLOCK_WIDTH = 40;
		static int BLOCK_HEIGHT = 20;
		static int BLOCK_GAP = 3;
		static int BAR_WIDTH = 80;
		static int BAR_HEIGHT = 20;
		static int CANVAS_WIDTH = 416 + (BLOCK_GAP * BLOCK_COLUMNS)-BLOCK_GAP;
		static int CANVAS_HEIGHT = 600;
		
		
		//variable
		static MyPanel myPanel = null;
		static int score = 0;
		static Timer timer = null;
		static Block[][] blocks = new Block[BLOCK_ROWS][BLOCK_COLUMNS];
		static Bar bar = new Bar();
		static Ball ball = new Ball();
		static int barXTarget = bar.x;//Target Value - interpolation
		static int dir = 0;//0 : Up-Right 1:Down-Right 2 : Up-Left 3 : Down-Left
		static int ballSpeed = 5;
		
		
		static class Ball{
			int x = CANVAS_WIDTH/2 - BALL_WIDTH/2;
			int y = CANVAS_HEIGHT/2 - BALL_HEIGHT/2;
			int width = BALL_WIDTH;
			int height = BALL_HEIGHT;
			
		}
		static class Bar{
			int x = CANVAS_WIDTH/2 - BAR_WIDTH/2;
			int y = CANVAS_HEIGHT - 100 ;
			int width = BAR_WIDTH;
			int height = BAR_HEIGHT;
		}
		
		static class Block{
			int x = 0;
			int y = 0;
			int width = BLOCK_WIDTH;
			int height = BLOCK_HEIGHT;
			int color = 0;//0:white 1:yellow 2:blue 3:magenta 4:red
			boolean isHidden = false;//after collision, block will be hidden.
		}
		
		static class MyPanel extends JPanel{
			public MyPanel() {
				this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
				this.setBackground(Color.BLACK);
			}
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				Graphics2D g2d = (Graphics2D) g;
				
				drawUI(g2d);
			}
			private void drawUI(Graphics2D g2d) {
				//draw Blocks
				for(int i=0;i<BLOCK_ROWS;i++) {
					for(int j=0;j<BLOCK_COLUMNS;j++) {
						if(blocks[i][j].isHidden) {
							continue;
						}
						if(blocks[i][j].color==0) {
							g2d.setColor(Color.white);
						}
						else if(blocks[i][j].color==1) {
							g2d.setColor(Color.yellow);
						}
						else if(blocks[i][j].color==2) {
							g2d.setColor(Color.blue);
						}
						else if(blocks[i][j].color==3) {
							g2d.setColor(Color.magenta);
						}
						else if(blocks[i][j].color==4) {
							g2d.setColor(Color.red);
						}
						g2d.fillRect(blocks[i][j].x,blocks[i][j].y,
								blocks[i][j].width,blocks[i][j].height);
					}
					//draw score
					g2d.setColor(Color.white);
					g2d.setFont(new Font("TimerRoman",Font.BOLD,20));
					g2d.drawString("score : "+score,CANVAS_WIDTH/2-30,25);
					
					//draw ball
					g2d.setColor(Color.white);
					g2d.fillOval(ball.x, ball.y, BALL_WIDTH, BALL_HEIGHT);
					
					//draw Bar
					g2d.setColor(Color.white);
					g2d.fillRect(bar.x, bar.y, bar.width, bar.height);
				}	
			}
		}
		
		public MyFrame(String title) {
			super(title);
			this.setVisible(true);
			this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
			this.setLocation(400, 300);
			this.setLayout(new BorderLayout());
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			initData();
			myPanel = new MyPanel();
			this.add("Center",myPanel);
			
			setKeyListener();
			startTimer();
			
		}
		public void initData() {
			for(int i=0;i<BLOCK_ROWS;i++) {
				for(int j=0;j<BLOCK_COLUMNS;j++) {
					blocks[i][j] = new Block();
					blocks[i][j].x = BLOCK_WIDTH*j + BLOCK_GAP*j;
					blocks[i][j].y = 100+ BLOCK_HEIGHT*i + BLOCK_GAP*i;
					blocks[i][j].width = BLOCK_WIDTH;
					blocks[i][j].height = BLOCK_HEIGHT;
					blocks[i][j].color = 4-i;
					blocks[i][j].isHidden = false;
							
				}
			}
		}
		public void setKeyListener() {
			this.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) { //key Event
					if(e.getKeyCode()==KeyEvent.VK_LEFT) {
						System.out.println("Pressed Left Key");
						barXTarget -=20;
						if(bar.x < barXTarget) { //repeate key pressed...
							barXTarget = bar.x;
							
						}
						
					}
					else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
						System.out.println("Pressed Right Key");
						barXTarget +=20;
						if(bar.x > barXTarget) { //repeate key pressed...
							barXTarget = bar.x;
						}
					}
				}
			});
			
			
			
		}
		public void startTimer() {
			
			timer = new Timer(20, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) { //Timer Event
					movement();
					checkCollision();//충돌방지
					checkCollisionBlock();
					myPanel.repaint();//redraw!
				}
			});
			timer.start();
		}
		public void movement() {
			
		}
		public void checkCollision(){
			
		}
		public void checkCollisionBlock(){
			
		}
	}	
	
	public static void main(String[] args) {
		new MyFrame("Block Game");
	}
}
