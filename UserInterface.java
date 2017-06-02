//Quang Vu

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserInterface extends JFrame{

	private GameBoard board;
	private JButton stayButton, upButton, downButton, leftButton, rightButton, upLeftButton, upRightButton,
	downLeftButton, downRightButton, teleButton, destroyButton;
	private JTextArea text;
	private JTextField scoreText;

	public UserInterface(){
		setupUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 900);
		setFocusable(true);
		setVisible(true);
	}

	private void setupUI(){
		//create the components
		board = new GameBoard();
		destroyButton = new JButton("Destroy" + "(" + board.getDestroyNum() + ")");
		upButton = new JButton("Up");
		downButton = new JButton("Down");
		leftButton = new JButton("Left");
		rightButton = new JButton("Right");
		upLeftButton = new JButton("Up Left");
		upRightButton = new JButton("Up Right");
		downLeftButton = new JButton("Down Left");
		downRightButton = new JButton("Down Right");
		stayButton = new JButton("Stay");
		teleButton = new JButton("Teleport(Random)");
		text= new JTextArea();
		scoreText = new JTextField(40); 

		setLayout(new BorderLayout());

		//a panel for buttons
		JPanel buttons = new JPanel();
		buttons.add(upLeftButton);
		buttons.add(upButton);
		buttons.add(upRightButton);	
		buttons.add(leftButton);
		buttons.add(stayButton);
		buttons.add(rightButton);
		buttons.add(downLeftButton);
		buttons.add(downButton);
		buttons.add(downRightButton);
		buttons.add(teleButton);
		buttons.add(destroyButton);

		//text area
		JPanel textPanel = new JPanel();
		text.append("Keyboard: \n");
		text.append("Q: Up Left, W: Up, E: Up Right, A: Left, S: Stay, D: Right,"
				+ " Z: Down Left, X: Down, C: Down Right, Space: Teleport \n");
		text.append("Additional Features:\n");
		text.append("The game is over if the player hits a rubble.\n");
		text.append("Destroy a random robot(3 times).");
		text.setEditable(false);
		scoreText.setEditable(false);
		textPanel.add(text);
		textPanel.add(scoreText);

		//adding components 
		add(board, BorderLayout.CENTER);
		add(buttons, BorderLayout.NORTH);
		add(textPanel, BorderLayout.SOUTH);

		//destroy a random robot button
		destroyButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				board.randomDestroy();
				destroyButton.setText("Destroy" + "(" + board.getDestroyNum() + ")");
				scoreText.setText("Score: " + board.getScore());
				UserInterface.this.requestFocus();
				
				if(board.isWin()){
					win();
				}		
			}
		});
		
		//moving up button
		upButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				board.pUp();		
				winOrLose();
			}
		});

		//moving down button
		downButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				board.pDown();	
				winOrLose();
			}
		});

		//moving up left button
		upLeftButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				board.pUpLeft();
				winOrLose();
			}
		});

		//moving up right button
		upRightButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				board.pUpRight();
				winOrLose();
			}
		});

		//moving left
		leftButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				board.pLeft();
				winOrLose();
			}
		});

		//moving right
		rightButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				board.pRight();
				winOrLose();
			}
		});

		//moving down left button
		downLeftButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				board.pDownLeft();
				winOrLose();
			}
		});

		//moving down right button
		downRightButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				board.pDownRight();
				winOrLose();
			}
		});

		//staying button
		stayButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				board.getPlayer().move(0, 0);
				winOrLose();
			}
		});

		//teleport button
		teleButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				board.playerTele();
				winOrLose();
			}
		});

		//W key for going up
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_W){
					board.pUp();		
					winOrLose();
				}		
			}
		});

		//Q key for going up left
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_Q){
					board.pUpLeft();
					winOrLose();
				}		
			}
		});

		//E key for going up right
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_E){
					board.pUpRight();
					winOrLose();
				}		
			}
		});

		//A key for going left
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_A){
					board.pLeft();
					winOrLose();
				}		
			}
		});

		//S key for staying
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_S){
					board.getPlayer().move(0, 0);
					winOrLose();
				}		
			}
		});

		//D key for going right
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_D){
					board.pRight();
					winOrLose();
				}		
			}
		});

		//Z key for going down left
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_Z){
					board.pDownLeft();
					winOrLose();
				}		
			}
		});

		//X key for going down
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_X){
					board.pDown();
					winOrLose();
				}		
			}
		});

		//C key for going down right
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_C){
					board.pDownRight();
					winOrLose();
				}		
			}
		});

		//Space key for teleport
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_SPACE){
					board.playerTele();
					winOrLose();
				}		
			}
		});

	}

	//if game is over, the user has the option to restart
	private void gameOver(){
		int result = JOptionPane.showConfirmDialog(null, "Game Over. Restart?");
		if(result == JOptionPane.YES_OPTION){
			UserInterface.this.setVisible(false);
			UserInterface.this.dispose();
			new UserInterface();
		}
		else if(result == JOptionPane.NO_OPTION){
			System.exit(0);
		}
	}

	//if the user wins, the user has the option to restart
	private void win(){
		int result = JOptionPane.showConfirmDialog(null, "You Win. Restart?");
		if(result == JOptionPane.YES_OPTION){
			UserInterface.this.setVisible(false);
			UserInterface.this.dispose();
			new UserInterface();
		}
		else if(result == JOptionPane.NO_OPTION){
			System.exit(0);
		}
	}

	//check if win or lose method
	public void winOrLose(){
		board.robotsMove();	
		scoreText.setText("Score: " + board.getScore());
		UserInterface.this.requestFocus();

		if(board.isGameOver()){
			gameOver();
		}
		else if(board.isWin()){
			win();
		}		
	}

	public static void main(String[] args) {
		UserInterface user = new UserInterface();
	}

}
