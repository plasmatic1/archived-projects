package{
	public class Helper{
		public Helper(){
			
		}
		
		public Array toArray(Object... parameters){
			var a:Array = new Array();
			
			for each(Object o in parameters){
				a.push(o);
			}
			
			return a;
		}
	}
}