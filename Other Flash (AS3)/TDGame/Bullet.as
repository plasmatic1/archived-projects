package{
	import flash.display.Sprite;
	import flash.display.Graphics;
	import flash.utils.Timer;
	import flash.events.TimerEvent;
	
	public class Bullet extends Sprite{
		private var graphic:Sprite;
		private var counter:Timer = new Timer(1);
		
		private var speed:Number;
		private var damage:int;
		private var fillMain:uint;
		private var borderMain:uint;
		private var ability:String;
		private var visibility:Number
		
		public function Bullet(speed:Number, damage:int, fillMain:uint, borderMain:uint, ability:String, visibility:Number, rot:Number){
			this.speed = speed;
			this.damage = damage;
			this.fillMain = fillMain;
			this.borderMain = borderMain;
			this.ability = ability;
			this.visibility = visibility;
			this.rotation = rot;
			
			initGraphics();
			
			counter.start();
			
			counter.addEventListener(TimerEvent.TIMER, update);
		}
		
		public function initGraphics(){
			graphic.graphics.beginFill(fillMain, visibility);
			graphic.graphics.lineStyle(3, borderMain, visibility);
			graphic.graphics.drawCircle(0, 0, 5);
			graphic.graphics.endFill();
			addChild(graphic);
		}
		
		public function update(e:TimerEvent){
			graphic.x += Math.cos(graphic.rotation * Math.PI / 180) * speed;
			graphic.y += Math.sin(graphic.rotation * Math.PI / 180) * speed;
		}
		
		public function getValues():Array{
			var values:Array = new Array(damage, ability);
			return values;
		}
	}
}