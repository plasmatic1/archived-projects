
package{
	import flash.display.MovieClip;
	
	public class Player1 extends MovieClip{
		private var facing:int;
		
		public function Player1(fac:int){
			facing = fac;
			initPlayer();
		}
		
		public function getFacingValue():int{
			return facing;
		}
		
		public function initPlayer():void{
			x = 100;
			y = 100;
		}
		
		public function setFacingValue(facing:int):void{
			this.facing = facing;
		}
		
		public function setPos(xPos:int, yPos:int){
			x = xPos;
			y = yPos;
		}
		
		public function modifyPos(xMove:int, yMove:int){
			x += xMove;
			y += yMove;
		}
		
		public function getX():int{
			return x;
		}
		
		public function getY():int{
			return y
		}
		
		public function getRotation():int{
			return rotation;
		}
		
		public function setRotation(rot:int):void{
			rotation = rot;
		}
	}
}

