package cwk2oop;

import java.util.ArrayList;
import java.util.Collections;

public class Reel{
	Control control = new Control();
	ArrayList <Symbol> symbols = new ArrayList<>(); //Declaring the array of symbols
	//Inserting the symbols to the array
	public Reel(){
		Symbol cherry = new Symbol();
		cherry.setValue(2);
		cherry.setImage("images/cherry.png");
		symbols.add(cherry);

		Symbol bell = new Symbol();
		bell.setValue(6);
		bell.setImage("images/bell.png");
		symbols.add(bell);

		Symbol seven = new Symbol();
		seven.setValue(7);
		seven.setImage("images/redseven.png");
		symbols.add(seven);

		Symbol plum = new Symbol();
		plum.setValue(4);
		plum.setImage("images/plum.png");
		symbols.add(plum);

		Symbol lemon = new Symbol();
		lemon.setValue(3);
		lemon.setImage("images/lemon.png");
		symbols.add(lemon);

		Symbol watermelon = new Symbol();
		watermelon.setValue(5);
		watermelon.setImage("images/watermelon.png");
		symbols.add(watermelon);

		Collections.shuffle(symbols);

	}
	public ArrayList<Symbol> spin(){
		return symbols;
	}
	
}



