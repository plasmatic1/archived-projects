package{
	import flash.display.MovieClip;
	import flash.utils.Timer;
	import flash.events.TimerEvent;
	
	public class ShockwaveSprite extends MovieClip{
		private var counter:Timer = new Timer(10);
		private var speed:Number;
		private var enemiesKilled:int = 0;
		
		public function ShockwaveSprite(xPos:int, yPos:int, speed:Number){
			x = xPos;
			y = yPos;
			this.speed = speed;
			initalize();
			
			counter.start();
			counter.addEventListener(TimerEvent.TIMER, update);
		}
		
		protected function initalize(){
			
		}
		
		protected function update(e:TimerEvent){
			y -= speed;
		}
		
		public function setPos(xVal:int, yVal:int){
			x = xVal;
			y = yVal;
		}
		
		public function movePos(xVal:int, yVal:int){
			x += xVal;
			y += yVal;
		}
		
		public function despawn(){
			counter.removeEventListener(TimerEvent.TIMER, update);
		}
		
		public function addKill(){
			enemiesKilled++;
		}
		
		public function getKills():int{
			return enemiesKilled;
		}
	}
}