package{
	import flash.display.Sprite;
	import flash.display.Graphics;
	import flash.utils.Timer;
	import flash.events.TimerEvent;
	import flash.text.TextField;
	
	public class Enemy extends Sprite{
		private var graphic:Sprite = new Sprite();
		private var counter:Timer = new Timer(1);
		private var health:int;
		private var speed:Number;
		private var damageAmt:int;
		private var fillMain:uint;
		private var borderMain:uint;
		private var ability:String;
		private var visibility:Number;
		
		public function Enemy(xPos:int, yPos:int, rot:Number, fillMain:uint, borderMain:uint, visibility:Number, health:int, damage:int, speed:Number){
			this.speed = speed;
			this.damageAmt = damage;
			this.fillMain = fillMain;
			this.borderMain = borderMain;
			this.visibility = visibility;
			this.rotation = rot;
			this.health = health;
			
			initGraphics();
			
			x = xPos;
			y = yPos;			
			
			counter.start();
			
			counter.addEventListener(TimerEvent.TIMER, update);
		}
		
		public function initGraphics(){
			graphic.graphics.beginFill(fillMain, visibility);
			graphic.graphics.lineStyle(3, borderMain, visibility);
			graphic.graphics.drawCircle(0, 0, 1);
			graphic.graphics.endFill();
			addChild(graphic);
 
		}
		
		public function update(e:TimerEvent){
			graphic.x += Math.cos(graphic.rotation * Math.PI / 180) * speed;
			graphic.y += Math.sin(graphic.rotation * Math.PI / 180) * speed;
		}
		
		public function setSpeed(amt:Number){
			speed = amt;
		}
		
		public function damage(amt:int){
			health -= amt;
		}
		
		public function die(){
			graphic = null;
		}
		
		public function getY():Number{
			return graphic.y;
		}
		
		public function getX():Number{
			return graphic.x;
		}
		
		public function move(xPos:Number, yPos:Number){
			graphic.x += xPos;
			graphic.y += yPos;
		}
		
		public function set(xPos:Number, yPos:Number){
			graphic.x = xPos;
			graphic.y = yPos;
		}
	}
}