package ctimer.me.tlwv2;

import java.util.HashMap;
import java.util.Map;

import com.sun.prism.PhongMaterial.MapType;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;

/*
 * "pb" is the css styling for the progressbar
 * "lb" is the css styling for the label
 */
public class TimerBar extends StackPane {
	
	private ProgressBar bar;
	private Label text;

	public TimerBar() {
		bar.setProgress(0);
		text.setText("0.0");
		
		this.getChildren().add(bar);
		this.getChildren().add(text);
		
		bar.setId("pb");
		text.setId("lb");
		
		bar.applyCss();
		text.applyCss();
	}
	
	public void setProgress(double progress, String lbltxt, ProgressType type, double... params){
		String text = lbltxt + ": ";
		
		bar.setProgress(progress);
		text.setText(type.textOf(params));
	}

	public TimerBar(Node... children) {
		super(children);
		// TODO Auto-generated constructor stub
	}
	
	public enum ProgressType{
		/*
		 * Displays as Precentage
		 * args: double as precentvalue
		 */
		PRECENT(1, "p1%", "percent"), 
		
		/*
		 * Displays as plain text
		 * args: double as displaytext
		 */
		PLAIN(1, "p1", "plain"), 
		
		/*
		 * Displays as fraction
		 * args: param1 / param2
		 */
		FRACTION(2, "p1 / p2", "fraction");
		
		private int argcount;
		private String argfocus;
		private String type;
		
		ProgressType(int argcount, String argfocus, String type){
			this.argcount = argcount;
			this.argfocus = argfocus;
			this.type = type;
		}
		
		public String textOf(double... params){
			String endstr = argfocus;
			
			if(params.length < argcount || params.length > argcount){
				return "invalid argument count";
			}
			
			Map<String, Double> ptable = new HashMap<String, Double>();
			
			for(int i = 0; i < params.length; i++){
				double param = params[i];
				String name = "p" + i;
				
				ptable.put(name, param);
			}
			
			if(type.equals("precent")){
				double p = ptable.get("p1");
				p *= 100;
				ptable.put("p1", p);
			}
			
			for(String s : ptable.keySet()){
				endstr = endstr.replaceAll(s, String.valueOf(ptable.get(s)));
			}
			
			return endstr;
		}
	}
}
