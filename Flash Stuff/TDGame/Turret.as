package{
	import flash.display.Sprite;
	import flash.display.Graphics;
	import flash.utils.Timer;
	import flash.events.TimerEvent;
	
	public class Turret extends Sprite{
		private var graphic:Sprite = new Sprite();
		private var counter:Timer = new Timer(1);
		
		private var reload:int;
		private var speed:Number;
		private var damage:int;
		private var fillMain:uint;
		private var borderMain:uint;
		private var fillBullet:uint;
		private var borderBullet:uint;
		private var ability:String;
		private var visibility:Number;
		
		private var target:Enemy;
		private var tempReload:int;
		
		public function Turret(xPos:int, yPos:int, reload:int, speed:Number, damage:int, fillMain:uint, borderMain:uint, fillBullet:uint, borderBullet:uint, ability:String, visibility:Number){
			this.reload = reload;
			this.speed = speed;
			this.damage = damage;
			this.fillMain = fillMain;
			this.borderMain = borderMain;
			this.fillBullet = fillBullet;
			this.borderBullet = borderBullet;
			this.ability = ability;
			this.visibility = visibility;
			
			target = new Enemy(0, 0, 0.0, 0x000000, 0x000000, 1, 1, 1, 1);
			tempReload = 0;
			
			//started bullet generation
			
			
			initGraphics();
			addChild(graphic);
			graphic.x = xPos;
			graphic.y = yPos;
			
			counter.start();
			
			counter.addEventListener(TimerEvent.TIMER, update);
		}
		
		public function initGraphics():void{
			trace("generationg bullet");
			
			graphic.graphics.beginFill(fillMain, visibility);
			graphic.graphics.lineStyle(3, borderMain);
			graphic.graphics.drawCircle(0, 0, 20);
			graphic.graphics.endFill();
			graphic.graphics.beginFill(fillBullet, visibility);
			graphic.graphics.lineStyle(3, borderBullet);
			graphic.graphics.drawRect(-5, -35, 10, 40);
			graphic.graphics.endFill();
		}
		
		public function rotate(rotAmt:Number){
			graphic.rotation += rotAmt;
		}
		
		public function shoot(enemy:Enemy){
			var bullet:Bullet = new Bullet(1, 10, fillBullet, borderBullet, ability, visibility, graphic.rotation);
		}
		
		public function update(){
			graphic.rotation = Math.atan2(target.getY() - this.y, target.getX() - this.x) / Math.PI * 180;
			
			tempReload++;
			
			if(tempReload >= reload){
				shoot(target);
			}
		}
		
		public function pickTarget(inp:Enemy){
			target = inp;
		}
		
		public function die(){
			graphic = null;
			
		}
	}
}