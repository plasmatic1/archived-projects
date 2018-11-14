
package{
	import flash.display.Shape;
	
	public class Player extends Shape{
		private var facing:int;
		private var playerNum;
		private var player:Shape;
		
		public function Player(playerClip:Shape, playerNum:int){
			facing = 0;
			player = playerClip;
			initPlayer();
			this.playerNum = playerNum;
			drawShape(playerNum);
		}
		
		public function getFacingValue():int{
			return facing;
		}
		
		public function drawShape(playerN:int){
			if(playerN == 1){
				player.graphics.lineStyle(3, 0x000000, 1);
				player.graphics.beginFill(0x00FF00,1); 
				player.graphics.drawRect(0, 0, 30, 30);
				player.graphics.endFill();
				player.graphics.lineStyle(0, 0x000000, 1);
				player.graphics.beginFill(0x000000, 1);
				player.graphics.drawRect(-15, 15, 30, 2);
				player.graphics.endFill();
			}
			else if(playerN == 2){
				player.graphics.lineStyle(3, 0x000000, 1);
				player.graphics.beginFill(0xFF0000,1); 
				player.graphics.drawRect(0, 0, 30, 30);
				player.graphics.endFill();
				player.graphics.lineStyle(0, 0x000000, 1);
				player.graphics.beginFill(0x000000, 1);
				player.graphics.drawRect(-15, 15, 30, 2);
				player.graphics.endFill();
			}
		}
		
		public function initPlayer():void{
			player.x = 100;
			player.y = 100;
		}
		
		public function setFacingValue(facing:int):void{
			this.facing = facing;
		}
		
		public function setPos(xPos:int, yPos:int){
			player.x = xPos;
			player.y = yPos;
		}
		
		public function modifyPos(xMove:int, yMove:int){
			player.x += xMove;
			player.y += yMove;
		}
		
		public function getPos():String{
			return player.x.toString() + "," + player.y.toString();
		}
		
		public function getRotation():int{
			return player.rotation;
		}
		
		public function setRotation(rot:int):void{
			player.rotation = rot;
		}
	}
}

