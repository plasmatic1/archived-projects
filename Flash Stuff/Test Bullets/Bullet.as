package{
	import flash.display.MovieClip;
	import flash.utils.Timer;
	import flash.events.TimerEvent;

	public class Bullet extends MovieClip{
		private var offset:Number = 4000;
		
		private var xVel:Number;
		private var yVel:Number;
		private var counter:Timer = new Timer(1);
		private var speed:Number = 1;
		
		public function Bullet(startX:int, startY:int, rot:Number){
			x = startX;
			y = startY;
			rotation = rot + (((Math.random() * offset) - (offset / 2)) * Math.PI / 180);
			trace(rotation);
			
			xVel = Math.cos(rotation * Math.PI / 180) * speed;
			yVel = Math.sin(rotation * Math.PI / 180) * speed;
			
			counter.addEventListener(TimerEvent.TIMER, updateBullet);
			counter.start();
		}
		
		public function updateBullet(e:TimerEvent):void{
			x += xVel;
			y += yVel;
		}
	}
}