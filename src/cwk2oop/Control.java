package cwk2oop;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Control {  //Class to Control the GUI and the Data
	static boolean stopThread1;  //boolean flags to stop the threads
	static boolean stopThread2;
	static boolean stopThread3;
	static int reelOneValue;  //variables to store the values of the selected reels
	static int reelTwoValue; 
	static int reelThreeValue;
	private static int initialCredits;  //credits at the start of the game
	private static int credits; //credits that the user  bets
	static boolean done;  //flag to see whether all the threads have stopped
	
	
	//GET SET Methods of InitialCredits
	public static int getInitialCredits() {
		return initialCredits;
	}

	public static void setInitialCredits(int initialCredits) {
		Control.initialCredits = initialCredits;
	}
	
	//GET SET Methods of Credits
	public static int getCredits() {
		return credits;
	}

	public static void setCredits(int credits) {
		Control.credits = credits;
	}
	
	//Method to disable buttons when the reels are spinning
	public void disableButtons(JButton reel1Btn, JButton reel2Btn, JButton reel3Btn, JButton coins, JButton max, JButton one, JButton del, JButton stats, JButton spin){
		reel1Btn.setEnabled(true);
		reel2Btn.setEnabled(true);
		reel3Btn.setEnabled(true);
		coins.setEnabled(false);
		max.setEnabled(false);
		one.setEnabled(false);
		del.setEnabled(false);
		stats.setEnabled(false);
		spin.setEnabled(false);
		
	}
	
	//Methods to enable buttons when the reels have stopped spinning
	public void enableButtons(JButton reel1Btn, JButton reel2Btn, JButton reel3Btn, JButton coins, JButton max, JButton one, JButton del, JButton stats, JButton spin){
		reel1Btn.setEnabled(false);
		reel2Btn.setEnabled(false);
		reel3Btn.setEnabled(false);
		coins.setEnabled(true);
		max.setEnabled(true);
		one.setEnabled(true);
		del.setEnabled(true);
		stats.setEnabled(true);
		spin.setEnabled(true);
	}
	
	//Main Method
	public static void main(String[] args){
		Control.setInitialCredits(10);
		Control.setCredits(0);
		GUI test = new GUI();
		JFrame gameFrame = new JFrame();
		gameFrame.setTitle("Slot Game!");
		gameFrame.setSize(570,450);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		gameFrame.add(test);
	}
	
}
