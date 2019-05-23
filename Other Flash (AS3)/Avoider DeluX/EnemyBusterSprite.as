package{
	import flash.display.MovieClip;
	import flash.utils.Timer;
	import flash.events.TimerEvent;
	
	public class EnemyBusterSprite extends MovieClip{
		private var counter:Timer = new Timer(10);
		private var deathTime:Timer = new Timer(1000, 3);
		
		public function EnemyBusterSprite(xPos:int, yPos:int){
			x = xPos;
			y = yPos;
			initalize();
			
			counter.start();
			counter.addEventListener(TimerEvent.TIMER, update);
			
			deathTime.start();
			deathTime.addEventListener(TimerEvent.TIMER_COMPLETE, despawn);
		}
		
		protected function initalize(){
			
		}
		
		protected function update(e:TimerEvent){
			
		}
		
		public function despawn(e:TimerEvent = null){
			counter.removeEventListener(TimerEvent.TIMER, update);
		}
		
		public function setPos(xVal:int, yVal:int){
			x = xVal;
			y = yVal;
		}
		
		public function movePos(xVal:int, yVal:int){
			x += xVal;
			y += yVal;
		}
	}
}