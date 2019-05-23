package{
	import flash.display.MovieClip;
	import flash.utils.Timer;
	import flash.events.TimerEvent;
	
	public class Enemy extends MovieClip{
		private var speeds:Array = new Array();
		private var counter:Timer = new Timer(10);
		private var speed:Number = 0;
		private var originalSpeed:Number = 0;
		
		public function Enemy(xPos:int, yPos:int, speeds:Array){
			x = xPos;
			y = yPos;
			this.speeds = speeds;
			initalize();
			
			counter.start();
			counter.addEventListener(TimerEvent.TIMER, update);
		}
		
		protected function initalize(){
			speed = speeds[0] + (Math.random()*(speeds[1] - speeds[0]));
			originalSpeed = speed;
		}
		
		protected function update(e:TimerEvent){
			y += speed;
		}
		
		public function setPos(xVal:int, yVal:int){
			x = xVal;
			y = yVal;
		}
		
		public function movePos(xVal:int, yVal:int){
			x += xVal;
			y += yVal;
		}
		
		public function setSpeed(inp:Number){
			speed = inp;
		}
		
		public function getSpeed():Number{
			return speed;
		}
		
		public function getOriginalSpeed():Number{
			return originalSpeed;
		}
		
		public function despawn(e:TimerEvent = null){
			counter.removeEventListener(TimerEvent.TIMER, update);
		}
	}
}