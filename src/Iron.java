


public interface Iron {	
	int iron_durability=70;
	int iron_effectiveness=10;
	boolean canBurn=false;
}





class IronAx extends Ax implements Iron{//12.ö����
	public IronAx(){
		super.durability=iron_durability;
		super.effectiveness=iron_effectiveness;
		super.ingredient = IRON;
		super.canBurn=Iron.canBurn;
		super.itemCode=12;
		super.itemNum=0;
		super.itemName="ö����";
	}
	

}
class IronPickax extends Pickax implements Iron{//13.ö���
	public IronPickax(){
		super.durability=iron_durability;
		super.effectiveness=iron_effectiveness;
		super.ingredient = IRON;
		super.canBurn=Iron.canBurn;
		super.itemCode=13;
		super.itemNum=0;
		super.itemName="�ְ��";
	}

}