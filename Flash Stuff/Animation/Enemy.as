package{
	import flash.display.MovieClip;
	import flash.utils.Timer;
	import flash.events.TimerEvent;
	
	public class Enemy extends MovieClip{
		private var counter:Timer = new Timer(10);
		private var speed:int = 0;
		
		public function Enemy(startX:int, startY:int, speeds:Array){
			x = startX;
			y = startY;
			counter.start();
			counter.addEventListener(TimerEvent.TIMER, update);
			speed = 1 + Math.floor(Math.random() * (speeds[1]-speeds[0])) + speeds[0];
		}
		
		public function update(e:TimerEvent){
			y += speed;
		}
	}
}