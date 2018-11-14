package ttt.me.tlwv2;

import javafx.scene.image.Image;

public enum TTTImage {
	EMPTY("../../assets/empty.png", 50, 50, true),
	
	OSLOT("../../assets/o.png", 50, 50, true),
	
	XSLOT("../../assets/x.png", 50, 50, true),
	
	NULL("../../assets/null.png", 50, 50, true);
	
	private String path;
	private double width;
	private double height;
	private boolean pr;
	
	private TTTImage(String path, double width, double height, boolean pr){
		this.path = path;
		this.width = width;
		this.height = height;
		this.pr = pr;
	}
	
	public Image getImage(){
		return new Image(getClass().getResourceAsStream(path), width, height, pr, false);
	}
}
