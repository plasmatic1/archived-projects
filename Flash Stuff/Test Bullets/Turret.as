package{
	import flash.display.MovieClip;
	import flash.utils.Timer;
	import flash.events.TimerEvent;
	
	public class Turret extends MovieClip{
		private var counter:Timer = new Timer(10);
		
		public function Turret(startX:int, startY:int){
			x = startX;
			y = startY;
			counter.start();
			counter.addEventListener(TimerEvent.TIMER, setRotation);
		}
		
		public function setRotation(e:TimerEvent){
			var rot:Number = Math.atan2(stage.mouseY - this.y, stage.mouseX - this.x) / Math.PI * 180;
			rotation = rot;
		}
	}
}