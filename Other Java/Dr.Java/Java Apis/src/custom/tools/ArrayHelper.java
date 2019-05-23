package custom.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ArrayHelper{
	public static ArrayList<Object> toArrayList(Object... elements){
		ArrayList<Object> list = new ArrayList<Object>();
		
		for(Object o : elements){
			list.add(o);
		}
		
		return list;
	}
	
	public static List<Object> toList(Object... elements){
		List<Object> list = new ArrayList<Object>();
		
		for(Object o : elements){
			list.add(o);
		}
		
		return list;
	}
	
	public static Vector<Object> toVector(Object... elements){
		Vector<Object> list = new Vector<Object>();
		
		for(Object o : elements){
			list.add(o);
		}
		
		return list;
	}
	
	public static Object[] toArray(Object... elements){
		return elements;
	}
}
