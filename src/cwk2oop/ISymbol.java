package cwk2oop;

import javax.swing.ImageIcon;
 
public interface ISymbol {  //Interface

	public String setImage(String url);
	public ImageIcon getImage();
	public int setValue(int v);
	public int getValue();
}
