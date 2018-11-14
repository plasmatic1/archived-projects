package{
	import flash.display.Sprite;

	public class Block extends Sprite{
		private var colour:uint;
		private var border:uint;
		private var hasBorder:Boolean;
		private var graphic:Sprite = new Sprite();
		private var xCoord:int;
		private var yCoord:int;
		private var sizeX:int;
		private var sizeY:int;
		private var removed:int = 0;
		private var information:String;
		private var modified:Array;
		
		public function Block(colour:uint, border:uint, hasBorder:Boolean, xCoord:int, yCoord:int, sizeX:int, sizeY:int){
			this.colour = colour;
			this.border = border;
			this.hasBorder = hasBorder
			this.xCoord = xCoord;
			this.yCoord = yCoord;
			this.sizeX = sizeX;
			this.sizeY = sizeY;
			
			initalize();
		}
		
		public function initalize(){
			graphic.graphics.lineStyle(3, border);
			graphic.graphics.beginFill(colour);
			graphic.graphics.drawRect(0, 0, sizeX, sizeY);
			graphic.graphics.endFill();
			
			graphic.x = xCoord;
			graphic.y = yCoord;
			
			addChild(graphic);
			
			modVar();
			
			information = modified[0].toString() + " " + modified[1].toString() + " | " + modified[2].toString() + " " + modified[3].toString() + " | " + colour.toString() + " " + border.toString() + " " + hasBorder.toString();
		}
		
		public function remove(){
			graphic.visible = false;
			removed = 1;
		}
		
		public function getFillColour():uint{
			return colour;
		}
		
		public function getBorderColour():uint{
			return border;
		}
		
		public function getHasBorder():Boolean{
			return hasBorder;
		}
		
		public function getXPos():int{
			return xCoord;
		}
		
		public function getYPos():int{
			return yCoord;
		}
		
		public function getsizeX():int{
			return sizeX;
		}
		
		public function getsizeY():int{
			return sizeY;
		}
		
		public function getInfo():String{
			return information;
		}
		
		public function modVar():void{
			var xCoordS:String = String(xCoord);
			
			if(int(xCoordS) < 10){
				xCoordS = "00" + xCoordS;
			}
			else if(int(xCoordS) < 100){
				xCoordS = "0" + xCoordS;
			}
			
			
			var yCoordS:String = String(yCoord);
			
			if(int(yCoordS) < 10){
				yCoordS = "00" + yCoordS;
			}
			else if(int(xCoordS) < 100){
				yCoordS = "0" + yCoordS;
			}
			
			
			var sizeXS:String = String(sizeX);
			
			if(int(sizeXS) < 10){
				sizeXS = "00" + sizeXS;
			}
			else if(int(xCoordS) < 100){
				sizeXS = "0" + sizeXS;
			}
			
			
			var sizeYS:String = String(sizeY);
			
			if(int(sizeXS) < 10){
				sizeYS = "00" + sizeYS;
			}
			else if(int(xCoordS) < 100){
				sizeYS = "0" + sizeYS;
			}
			
			modified.push(xCoordS, yCoordS, sizeXS, sizeYS);
		}
	}
}