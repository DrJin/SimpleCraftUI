
public interface Stone{
	int stone_durability=50;
	int stone_effectiveness=8;
	boolean canBurn=false;
}


class StoneAx extends Ax implements Stone{//10.µ¹µµ³¢
	public StoneAx(){
		super.durability=stone_durability;
		super.effectiveness=stone_effectiveness;
		super.ingredient = STONE;
		super.canBurn=Stone.canBurn;
		super.itemCode=10;
		super.itemNum=0;
		super.itemName="µ¹µµ³¢";
	}

}
class StonePickax extends Pickax implements Stone{//11.µ¹°î±ªÀÌ
	public StonePickax(){
		super.durability=stone_durability;
		super.effectiveness=stone_effectiveness;
		super.ingredient = STONE;
		super.canBurn=Stone.canBurn;
		super.itemCode=11;
		super.itemNum=0;
		super.itemName="µ¹°î±ªÀÌ";
	}

}