`package{
	import flash.display.MovieClip;
	import flash.utils.Timer;
	import flash.events.TimerEvent;

	public class Enemy extends MovieClip{
		public var speed:Number;
		public var counter:Timer = new Timer(1);
		
		public function Enemy(startX:int, startY:int, speedRange:Array){
			x = startX;
			y = startY;
			speed = speedRange[0] + (Math.random()*(speedRange[1]-speedRange[0]));
			trace("Speed: " + speed.toString());
			counter.addEventListener(TimerEvent.TIMER, update);
			counter.start();
		}
		
		public function update(e:TimerEvent){
			y += speed;
		}
	}
}