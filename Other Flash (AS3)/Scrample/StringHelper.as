package{
	public class StringHelper{
		public function StringHelper(){
			
		}
	
		public function stringToArray(input:String):Array{
			var finalArray:Array = new Array();
		
			for(var i:int = 0; i < input.length; i++){
				finalArray.push(input.slice(i, i+1));
			}
		
			return finalArray;
		}
	
		public function arrayToString(input:Array):String{
			var finalString:String = "";
			
			for(var j:int = 0; j < input.length; j++){
				finalString += input[j];
			}
			
			return finalString;
		}
	
		public function replace(input:String, toBeReplaced:String, replacedWith:String):String{
			var sh:StringHelper = new StringHelper();
			var tempArray:Array = new Array();
			
			tempArray = sh.stringToArray(input);
			
			for(var k:int = 0; k < input.length; k++){
				if(tempArray[k] == toBeReplaced){
					tempArray[k] = replacedWith;
					trace("replaced: " + toBeReplaced + " with " + replacedWith);
				}
				trace("nothing was replaced");
			}
		
			var finalString:String = sh.arrayToString(tempArray);
			trace(finalString);
			
			return finalString;
		}
		
		public function randomChar():String{
			var sh:StringHelper = new StringHelper();
			
			var charlistString:String = "qwertyuiopasdfghjklzxcvbnm,./;:'_-+=\"\{\}\[\]\(\)\\!@#$%^&*";
			var charlist:Array = new Array();
			charlist = sh.stringToArray(charlistString);
			trace("created charlist");
			
			var finalString:String = charlist[Math.round(Math.random()*charlist.length)-1];
			trace(finalString);
			
			return finalString;
		}
	}
}