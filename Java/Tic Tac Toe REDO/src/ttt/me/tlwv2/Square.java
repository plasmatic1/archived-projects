package ttt.me.tlwv2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Square {
	private String state;
	private Image img;
	
	public static final String e = "empty";
	public static final String n = "null";
	public static final String o = "o";
	public static final String x = "x";
	
	public Square(){
		this("empty");
	}
	
	public Square(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		
		if(state.equals(e))
			img = TTTImage.EMPTY.getImage();
		else if(state.equals(n))
			img = TTTImage.NULL.getImage();
		else if(state.equals(x))
			img = TTTImage.XSLOT.getImage();
		else if(state.equals(o))
			img = TTTImage.OSLOT.getImage();
	}

	public Image getImage() {
		return img;
	}
	
	public ImageView getImageView(){
		ImageView iv = new ImageView(img);
		iv.setFitWidth(img.getWidth());
		iv.setFitHeight(img.getHeight());
		
		return iv;
	}
}
