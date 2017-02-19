package cwk2oop;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class StatiGUI extends JPanel {
	//This is the GUI Class for the Statistics Window
	//This uses GridBag layout too.
	Symbol symbolForGUI = new Symbol();
	GridBagConstraints gbc2 = new GridBagConstraints();
	JLabel won;
	JLabel lost;
	JLabel wonAns;
	JLabel lostAns;
	JLabel avg;
	JLabel avgAns;
	JLabel total;
	JLabel totalAns;
	JLabel message;
	JLabel txtMessage;
	JButton save;
	Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
	Border borderOne = BorderFactory.createLineBorder(Color.BLACK, 3);

	public StatiGUI(){
		setLayout(new GridBagLayout());
		gbc2.insets = new Insets(5,15,5,5);

		total = new JLabel ("Total Games :");
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		add(total,gbc2);

		totalAns = new JLabel ("0");
		totalAns.setBorder(borderOne);
		gbc2.gridx = 1;
		gbc2.gridy = 0;
		add(totalAns,gbc2);

		won = new JLabel ("Won Games :");
		gbc2.gridx = 0;
		gbc2.gridy = 1;
		add(won,gbc2);

		lost = new JLabel ("Lost Games :");
		gbc2.gridx = 0;
		gbc2.gridy = 2;
		add(lost,gbc2);

		wonAns = new JLabel ("");
		wonAns.setBorder(borderOne);
		gbc2.gridx = 1;
		gbc2.gridy = 1;
		add(wonAns,gbc2);

		lostAns = new JLabel ("");
		lostAns.setBorder(borderOne);
		gbc2.gridx = 1;
		gbc2.gridy = 2;
		add(lostAns,gbc2);

		avg = new JLabel ("Average Credits Per Game :");
		gbc2.gridx = 0;
		gbc2.gridy = 3;
		add(avg,gbc2);

		avgAns = new JLabel ("0");
		avgAns.setBorder(borderOne);
		gbc2.gridx = 1;
		gbc2.gridy = 3;
		add(avgAns,gbc2);

		save = new JButton("Save Satistics");
		gbc2.gridx = 0;
		gbc2.gridy = 5;
		add(save, gbc2);
		save.addActionListener(new SaveActionListener());
		
		message = new JLabel("");
		message.setBorder(borderOne);
		gbc2.gridx = 0;
		gbc2.gridy = 6;
		add(message,gbc2);
		message.setIcon(setPic());
		
		txtMessage = new JLabel("You've been Losing more than Winning! Try Harder!");
		gbc2.gridx = 0;
		gbc2.gridy = 7;
		add(txtMessage,gbc2);
	}


	//ActionListener for Save Statistics Button
	class SaveActionListener implements ActionListener{
		@Override
		public void actionPerformed (ActionEvent e){
			symbolForGUI.saveStatistics(); //Save method is invoked
			JOptionPane.showMessageDialog(null, "Your Statistics has been Saved to the File", "Saving Done!", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	ImageIcon setPic(){ //Setting the picture according to users win/lose average
		ImageIcon smiley = new ImageIcon(new ImageIcon("images/smiley.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		ImageIcon sad = new ImageIcon(new ImageIcon("images/sad.jpeg").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		int ave = symbolForGUI.statistics();
			if(ave > 0){
				return smiley;
			}else{
				return sad;
			}
	}
	
	String setTxt(){ //Setting the text according to users win/lose average
		int ave = symbolForGUI.statistics();
			if(ave > 0){
				return "Great! You've been Winning more than Losing! Keep Playing!";
			}else{
				return "Oh Snap! You've been losing more than Winning! Try Harder!";
			}
	}
	}

