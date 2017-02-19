package cwk2oop;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

//Main GUI Class
public class GUI extends JPanel {
	//JLabels, JButtons in the GUI
	JLabel betArea;  
	JLabel creditArea;
	JLabel betAreaAns;
	JLabel creditAreaAns;
	JLabel welcomeMsg;
	JLabel betBorder;
	JLabel creditBorder;
	JButton addCoins;
	JButton betMax;
	JButton start;
	JButton betOne;
	JButton reset;
	JButton statistics;
	JButton reel1;
	JButton reel2;
	JButton reel3;
	GridBagConstraints gbc = new GridBagConstraints(); //GridBag Layout Constraints

	//ImageIcons for the reels
	ImageIcon reelOne = new ImageIcon(new ImageIcon("images/lemon.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
	ImageIcon reelTwo = new ImageIcon(new ImageIcon("images/bell.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
	ImageIcon reelThree = new ImageIcon(new ImageIcon("images/redseven.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));

	//Borders for the JLabels
	Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
	Border borderOne = BorderFactory.createLineBorder(Color.BLACK, 2);
	Control controlForGUI = new Control(); //Making an instance of the Control Class

	//Constructor of the GUI Class
	public GUI(){
		setLayout(new GridBagLayout()); //initiating the gridbag layout
		gbc.insets = new Insets(5,15,5,5);

		//Defining JLabels and JButtons in the GUI
		betArea = new JLabel ("Your Bets");
		gbc.gridx = 2;
		gbc.gridy = 3;
		add(betArea,gbc);

		betAreaAns = new JLabel ("0");
		gbc.gridx = 2;
		gbc.gridy = 4;
		add(betAreaAns,gbc);

		creditArea = new JLabel ("Your Credits");
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(creditArea, gbc);

		creditAreaAns = new JLabel("");
		creditAreaAns.setText(String.valueOf(Control.getInitialCredits())); //Setting the value of the Initial Credits to the JLabel
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(creditAreaAns, gbc);

		reel1 = new JButton("");
		reel1.setIcon(reelOne);
		reel1.setBorder(border);
		gbc.gridx = 0;
		gbc.gridy = 5;
		add(reel1,gbc);
		reel1.setEnabled(false);
		reel1.addActionListener(new StopReel1());  

		reel2 = new JButton("");
		reel2.setIcon(reelTwo);
		reel2.setBorder(border);
		gbc.gridx = 1;
		gbc.gridy= 5;
		add(reel2,gbc);
		reel2.setEnabled(false);
		reel2.addActionListener(new StopReel2());

		reel3 = new JButton("");
		reel3.setIcon(reelThree);
		reel3.setBorder(border);
		gbc.gridx = 2;
		gbc.gridy= 5;
		add(reel3,gbc);
		reel3.addActionListener(new StopReel3());
		reel3.setEnabled(false);

		start = new JButton("Start");
		gbc.gridx = 2;
		gbc.gridy = 6;
		add(start,gbc);
		start.addActionListener(new StartActionListener()); 

		reset = new JButton("Reset");
		gbc.gridx = 2;
		gbc.gridy = 7;
		add(reset,gbc);
		reset.addActionListener(new Reset());

		betOne = new JButton("Bet One");
		gbc.gridx = 0;
		gbc.gridy = 6;
		add(betOne,gbc);
		betOne.addActionListener(new BetActionListener());  

		statistics = new JButton("Statistics");
		gbc.gridx = 1;
		gbc.gridy = 6;
		add(statistics,gbc);
		statistics.addActionListener(new StatActionListener());  

		betMax = new JButton("Bet Max");
		gbc.gridx = 0;
		gbc.gridy = 7;
		add(betMax,gbc);
		betMax.addActionListener(new BetMaxActionListener());

		addCoins = new JButton("Add Coins");
		addCoins.setBackground(Color.BLUE);
		addCoins.setForeground(Color.WHITE);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.VERTICAL;
		add(addCoins,gbc);
		addCoins.addActionListener(new AddCoinActionListener());  

		betBorder = new JLabel("                                                  ");
		betBorder.setBorder(borderOne);
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.VERTICAL;
		add(betBorder, gbc);

		betBorder = new JLabel("                                                  ");
		betBorder.setBorder(borderOne);
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(betBorder, gbc);

		welcomeMsg = new JLabel("Welcome to the Slot Game!! ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		welcomeMsg.setFont(new Font("Courier New", Font.CENTER_BASELINE, 20));
		add(welcomeMsg, gbc);
	}

	class StartActionListener implements ActionListener{ //Inner class for start button
		@Override
		public void actionPerformed (ActionEvent e){
			if(Control.getCredits() > 0){ //If user has bet more than 1 credit

				Control.stopThread1 = false; //Set the stop thread flags to false
				Control.stopThread2 = false;
				Control.stopThread3 = false;
				Control.done = false;
				changeButtons(); //Disable the buttons

				ReelThread1 thread1 = new ReelThread1(); //Create objects from the thread classes
				ReelThread2 thread2 = new ReelThread2();
				ReelThread3 thread3 = new ReelThread3();

				thread1.start(); //start the threads.
				thread2.start();
				thread3.start();
			}else{
				JOptionPane.showMessageDialog(null, "Add more Credits to Bets to keep playing the game", "Not enough Bet Credits", JOptionPane.ERROR_MESSAGE);
				controlForGUI.enableButtons(reel1, reel2, reel3, betMax, betOne, start, reset, statistics, addCoins);
			}
		}
	}

	class ReelThread2 extends Thread{  //Inner Class for the Second Thread
		@Override
		public void run(){
			Reel r1 = new Reel(); //Creating a new Reel Object
			while (!Control.stopThread2){
				int k = ThreadLocalRandom.current().nextInt(0, 5 + 1); //Generating a random number to select a random image
				reel2.setIcon(r1.symbols.get(k).getImage()); //Setting the random image generated on the reel
				Control.reelTwoValue = r1.symbols.get(k).getValue(); //Getting the value of the image when the reel is stopped
				try {
					sleep(100); //Pausing the thread for 1 second to generate the shuffling effect
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(null, "Thread Interupted", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	//Everything the same as Thread 2
	class ReelThread3 extends Thread{
		@Override
		public void run(){
			Reel r2 = new Reel();
			while (!Control.stopThread3){
				int m = ThreadLocalRandom.current().nextInt(0, 5 + 1);
				reel3.setIcon(r2.symbols.get(m).getImage());
				Control.reelThreeValue = r2.symbols.get(m).getValue();
				try {
					sleep(100);
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(null, "Thread Interupted", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		}
	}
	//Everything the same as Thread 2
	class ReelThread1 extends Thread{
		@Override
		public void run(){
			Reel r3 = new Reel();
			while (!Control.stopThread1){
				int i = ThreadLocalRandom.current().nextInt(0, 5 + 1);
				reel1.setIcon(r3.symbols.get(i).getImage());
				Control.reelOneValue = r3.symbols.get(i).getValue();
				try {
					sleep(100);
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(null, "Thread Interupted", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}

	class StopReel1 implements ActionListener{ //Inner class for stopReel button(Reel1)
		@Override
		public void actionPerformed (ActionEvent e){
			Control.stopThread1 = true; //set the flag to true
			startCompare(); //Invoke the compare method and see if other two reels have stopped spinning
			changeButtons(); 
		}
	}
	//Same as StopReel1
	class StopReel2 implements ActionListener{
		@Override
		public void actionPerformed (ActionEvent e){
			Control.stopThread2 = true;
			startCompare();
			changeButtons();
		}
	}
	//Same as StopReel1
	class StopReel3 implements ActionListener{
		@Override
		public void actionPerformed (ActionEvent e){
			Control.stopThread3 = true;
			startCompare();
			changeButtons();
		}
	}

	//Inner class for Bet Button
	class BetActionListener implements ActionListener{
		@Override
		public void actionPerformed (ActionEvent e){
			if(Control.getInitialCredits() <= 0){ //Checking to see if user has enough credits to bet
				JOptionPane.showMessageDialog(null, "You don't have enough Credits to do this", "Add more Credits", JOptionPane.ERROR_MESSAGE);
			}else{
				int temp = Control.getCredits();
				Control.setCredits(++temp); //Increase the bet amount
				betAreaAns.setText(String.valueOf(Control.getCredits()));
				temp = Control.getInitialCredits();
				Control.setInitialCredits(--temp); //decrease the credits amount
				creditAreaAns.setText(String.valueOf(Control.getInitialCredits()));
			}
		}

	}

	//Inner Class for BetMax Button
	//Same implementation like Bet One Button
	class BetMaxActionListener implements ActionListener{
		@Override
		public void actionPerformed (ActionEvent e){
			if((Control.getInitialCredits() <= 0) || ((Control.getInitialCredits() - 3) < 0 )){
				JOptionPane.showMessageDialog(null, "You don't have enough Credits to do this", "Add more Credits", JOptionPane.ERROR_MESSAGE);
			}else{
				int temp = Control.getCredits();
				temp = Control.getCredits() + 3;  //Increasing bet by 3
				Control.setCredits(temp);
				temp = Control.getInitialCredits();
				temp = Control.getInitialCredits() - 3;  //Decreasing coin amount by 3
				Control.setInitialCredits(temp);
				betAreaAns.setText(String.valueOf(Control.getCredits()));
				creditAreaAns.setText(String.valueOf(Control.getInitialCredits()));
			}
		}
	}

	//Inner class for AddCoin Button
	class AddCoinActionListener implements ActionListener{
		@Override
		public void actionPerformed (ActionEvent e){
			int temp;
			temp = Control.getInitialCredits();
			Control.setInitialCredits(++temp); //Increase the credit amount
			creditAreaAns.setText(String.valueOf(Control.getInitialCredits()));
		}
	}

	//Inner class for Statistics Button
	class StatActionListener implements ActionListener{
		Symbol symbolForStatistics = new Symbol(); //Making an object from the Symbol Class
		StatiGUI objectForStatiGUI = new StatiGUI(); //Making an object from the Statistics GUI Class
		@Override
		public void actionPerformed (ActionEvent e){
			JFrame statisticsFrame = new JFrame(); //Making the new JFrame for the statistics GUI
			statisticsFrame.setTitle("Statistics");
			statisticsFrame.setSize(570,500);
			statisticsFrame.add(objectForStatiGUI);
			statisticsFrame.setVisible(true);

			//Displaying the information generated from the symbol class in the Statistics GUI
			objectForStatiGUI.wonAns.setText(String.valueOf(Symbol.getWonGame())); 
			objectForStatiGUI.lostAns.setText(String.valueOf(Symbol.getLostGame()));
			objectForStatiGUI.avgAns.setText(String.valueOf(symbolForStatistics.statistics()));
			objectForStatiGUI.totalAns.setText(String.valueOf(Symbol.getTotalGames()));
			objectForStatiGUI.message.setIcon(objectForStatiGUI.setPic());
			objectForStatiGUI.txtMessage.setText(objectForStatiGUI.setTxt());

		}
	}
	//Inner class for the Reset Button
	class Reset implements ActionListener{
		@Override
		public void actionPerformed (ActionEvent e){
			int nowCredits = Control.getCredits(); //Getting the current amount of bet credits
			Control.setCredits(0); //setting the bet credits to 0
			int temp = Control.getInitialCredits();
			temp += nowCredits; //Increasing the coin amount by the bet amount
			Control.setInitialCredits(temp);
			betAreaAns.setText(String.valueOf(0));
			creditAreaAns.setText(String.valueOf(Control.getInitialCredits()));
		}
	}

	//Method to check if the reels are spinning or not and change the buttons accordingly
	void changeButtons(){
		Control buttons = new Control();
		if(Control.done){ //If all the reels have stopped spinning, enable buttons
			buttons.enableButtons(reel1, reel2, reel3, addCoins, betMax, betOne, reset, statistics, start);
		}else{ //if not disable
			buttons.disableButtons(reel1, reel2, reel3, addCoins, betMax, betOne, reset, statistics, start);
		}
	}

	//Method to compare the images that the user has got and calculate the won/lose amount
	public void startCompare(){	
		Symbol objectForCompare = new Symbol();
		if((Control.stopThread1) && (Control.stopThread2) && (Control.stopThread3)){ //Checking to see if the reels are still spinning
			int temp = objectForCompare.compare(Control.reelOneValue,Control.reelTwoValue,Control.reelThreeValue,Control.getCredits(),Control.getInitialCredits());
			if(temp != 0){
				Control.setCredits(0);
				betAreaAns.setText(String.valueOf(Control.getCredits()));
				Control.setInitialCredits(temp);
				creditAreaAns.setText(String.valueOf(Control.getInitialCredits()));
			}

		}
	}
}






