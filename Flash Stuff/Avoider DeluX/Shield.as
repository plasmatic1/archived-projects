package{
	import flash.display.MovieClip;
	import flash.utils.Timer;
	import flash.events.TimerEvent;
	
	public class Shield extends MovieClip{
		private var speeds:Array = new Array();
		private var counter:Timer = new Timer(10);
		private var speed:Number;
		
		public function Shield(xPos:int, yPos:int, speeds:Array){
			x = xPos;
			y = yPos;
			this.speeds = speeds;
			initalize();
			
			counter.start();
			counter.addEventListener(TimerEvent.TIMER, update);
		}
		
		protected function initalize(){
			speed = speeds[0] + (Math.random()*(speeds[1] - speeds[0]));
		}
		
		protected function update(e:TimerEvent){
			y += speed/2;
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
		
		
	}
}