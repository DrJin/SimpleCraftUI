
public abstract class Block extends Item {//블럭
	protected int ingredient;//재료
	protected boolean isCarved;//제련되었는지 안되었는지
	protected int hardness;//굳기
}

class WoodPiece extends Block implements Tree{//1.나무조각
	public WoodPiece(){
		super.ingredient=TREE;
		super.isCarved=false;
		super.hardness=10;
		super.canBurn=Tree.canBurn;
		super.itemCode=1;
		super.itemNum=0;
		super.itemName="나무조각";
	}
}
class StonePiece extends Block implements Stone{//2.돌조각
	public StonePiece(){
		super.ingredient=STONE;
		super.isCarved=false;
		super.hardness=20;
		super.canBurn=Stone.canBurn;
		super.itemCode=2;
		super.itemNum=0;
		super.itemName="돌조각";
	}
}
class IronPiece extends Block implements Iron{//3.철조각
	public IronPiece(){
		super.ingredient=IRON;
		super.isCarved=false;
		super.hardness=30;
		super.canBurn=Iron.canBurn;
		super.itemCode=3;
		super.itemNum=0;
		super.itemName="철조각";
	}
}
class WoodStick extends Block implements Tree{//4.나무막대기
	public WoodStick(){
		super.ingredient=TREE;
		super.isCarved=true;
		super.hardness=0;
		super.canBurn=Tree.canBurn;
		super.itemCode=4;
		super.itemNum=0;
		super.itemName="나무막대기";
	}
}
class WoodLump extends Block implements Tree{//5.나무덩이
	public WoodLump(){
		super.ingredient=TREE;
		super.isCarved=true;
		super.hardness=0;
		super.canBurn=Tree.canBurn;
		super.itemCode=5;
		super.itemNum=0;
		super.itemName="나무덩이";
	}
}
class StoneLump extends Block implements Stone{//6.돌덩이
	public StoneLump(){
		super.ingredient=STONE;
		super.isCarved=true;
		super.hardness=0;
		super.canBurn=Stone.canBurn;
		super.itemCode=6;
		super.itemNum=0;
		super.itemName="돌덩이";
	}
}
class IronLump extends Block implements Iron{//7.철괴
	public IronLump(){
		super.ingredient=IRON;
		super.isCarved=true;
		super.hardness=0;
		super.canBurn=Iron.canBurn;
		super.itemCode=7;
		super.itemNum=0;
		super.itemName="철괴";
	}
}
class Flint extends Block{//14.부싯돌
	public Flint(){
		super.canBurn=false;
		super.itemCode=14;
		super.itemNum=0;
		super.itemName="부싯돌";
	}
}