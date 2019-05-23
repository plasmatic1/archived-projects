package{
	import flash.display.MovieClip;
	import flash.utils.Timer;
	import flash.events.TimerEvent;
	
	public class Shockwave extends MovieClip{
		private var speeds:Array = new Array();
		private var counter:Timer = new Timer(10);
		private var canShoot:int = 2;
		private var deathTime:Timer = new Timer(1000, 4);
		private var speed:Number;
		
		public function Shockwave(xPos:int, yPos:int, speeds:Array){
			x = xPos;
			y = yPos;
			this.speeds = speeds;
			initalize();
			
			counter.start();
			counter.addEventListener(TimerEvent.TIMER, update);
			
			deathTime.addEventListener(TimerEvent.TIMER_COMPLETE, despawn);
		}
		
		protected function initalize(){
			speed = speeds[0] + (Math.random()*(speeds[1] - speeds[0]));
		}
		
		protected function update(e:TimerEvent){
			y += speed * 1.25;
		}
		
		public function despawn(e:TimerEvent = null){
			canShoot = 0;
		}
		
		public function getCanShoot():int{
			return canShoot;
		}
		
		public function setCanShoot(inp:int){
			canShoot = inp;
		}
		
		public function setPos(xVal:int, yVal:int){
			x = xVal;
			y = yVal;
		}
		
		public function movePos(xVal:int, yVal:int){
			x += xVal;
			y += yVal;
		}

		public function activateCounter(){
			deathTime.start();
			canShoot = 1;
			counter.removeEventListener(TimerEvent.TIMER, update);
			x = 480;
			y = 35;
		}
	}
}