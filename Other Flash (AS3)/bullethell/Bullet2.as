package{
	import flash.display.MovieClip;
	
	public class Bullet2 extends MovieClip{
		private var facing:int;
		
		public function Bullet2(facing:int, startX:int, startY:int){
			trace("made bullet commence");
			this.facing = facing;
			x = startX;
			y = startY;
			start();
		}
		
		public function getFacing():int{
			return facing;
		}
		
		public function setFacing(fac:int):void{
			facing = fac;
		}
		
		public function setPos(xPos:int, yPos:int){
			x = xPos;
			y = yPos;
		}
		
		public function modifyPos(xPos:int, yPos:int){
			x += xPos;
			y += yPos;
		}
		
		public function start():void{
			if(facing == 2){
				rotation = 90;
			}
			else if(facing == 3){
				rotation = 180;
			}
			else if(facing == 4){
				rotation = -90;
			}
		}
	}
}