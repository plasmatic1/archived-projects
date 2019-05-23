package{
	import flash.display.Shape;
	
	public class Bullet extends Shape{
		private var facing:int;
		
		public function Bullet(facing:int){
			this.facing = facing;
		}
		
		public function getFacing():int{
			return facing;
		}
		
		public function setFacing(fac:int):void{
			facing = fac;
		}
	}
}