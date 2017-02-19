package cwk2oop;

import java.awt.Image;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Symbol implements ISymbol {
	static int won;  //static variable to store the win amount in each game
	private static int wonGame; //static variable to store the number of wins
	private static int lostGame;  //static variable to store the number of loses
	private static int totalGames;  //static variable to store the number of total games
	private static int betAmount; //static variable to store the bet amount by the user

	Control controlOne = new Control();
	private String url;  //url of the image
	private int v; //value of each image

	//GET SET Methods for Image
	public String setImage(String url){
		this.url = url;
		return url;
	}
	public ImageIcon getImage(){ //the return type is an ImageIcon so it is easy to use in the GUI 
		return  new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
	}

	//GET SET Methods of the V
	public int setValue(int v){
		this.v = v;
		return v;
	}
	public int getValue(){
		return v;
	}

	//GET SET Methods of the Bet Amount
	public static int getBetAmount() {
		return betAmount;
	}
	public static void setBetAmount(int betAmount) {
		Symbol.betAmount = betAmount;
	}

	//GET SET Methods of the wonGame
	public static int getWonGame() {
		return wonGame;
	}
	public static void setWonGame(int wonGame) {
		Symbol.wonGame = wonGame;
	}

	//GET SET Methods of the Lost Game
	public static int getLostGame() {
		return lostGame;
	}
	public static void setLostGame(int lostGame) {
		Symbol.lostGame = lostGame;
	}

	//GET SET Methods of the TotalGames
	public static int getTotalGames() {
		return totalGames;
	}
	public static void setTotalGames(int totalGames) {
		Symbol.totalGames = totalGames;
	}

	//Compare method to compare the images that the user got in the reels
	public int compare( int one, int two, int three, int creditAmount, int remaining){
		Control.done = true; //Setting the flag to indicate that all the reels are done spinning
		setBetAmount(creditAmount);
		if((one == two)&& (two == three)){ //IF all three reels are the same
			won = creditAmount * one; //Calculating the won amount
			JOptionPane.showMessageDialog(null, "You have all Three! You win "+ won + " Credits"  , "You Won!", JOptionPane.INFORMATION_MESSAGE);
			won += remaining;
			wonGame++;
			totalGames++;
			return won;

			//If all three reels are different
		}else if((one != two)&& (two != three) && (one != three)){
			Control.setCredits(0);  //bet amount becomes 0
			JOptionPane.showMessageDialog(null, "You loose, you matched None! Try Again!", "You Loose!", JOptionPane.INFORMATION_MESSAGE);
			lostGame++; //It counts as a lost game
			totalGames++;

			//IF only two reels are the same
		}else{
			JOptionPane.showMessageDialog(null, "You have matched Two! You get to Spin Again!", "You Won!", JOptionPane.INFORMATION_MESSAGE);
			totalGames++; //It counts as a game, and the user doesn't loose the bet credits and get to spin again
			return 0;

		}
		return remaining;
	}   

	//method to calculate the average won amount of the user
	public int statistics(){
		if(totalGames >0){
			return  won / totalGames; 
		}else{
			return 0;
		}
	}

	//Method to save the statistics of the user to a file
	public void saveStatistics(){
		Date now = new Date(); //Getting an instance of the date, since the file name should be the date
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//Formatting the date
		File file = new File(dateFormat.format(now) + ".txt"); //Saving a new File
		PrintWriter pw = null;
		FileWriter fw = null;
		//Writing the statistics to the file
		try {
			fw = new FileWriter(file);
			pw = new PrintWriter(fw, true);
			pw.println("Total Games: " + totalGames);
			pw.println("Won Games: "+ wonGame);
			pw.println("Lost Games: " + lostGame);
			pw.println("Average Credits Per Game: " + statistics());
			fw.close();
			pw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File Could not be found!", "Error!", JOptionPane.ERROR_MESSAGE);
		}

	}
}


