
public abstract class Block extends Item {//��
	protected int ingredient;//���
	protected boolean isCarved;//���õǾ����� �ȵǾ�����
	protected int hardness;//����
}

class WoodPiece extends Block implements Tree{//1.��������
	public WoodPiece(){
		super.ingredient=TREE;
		super.isCarved=false;
		super.hardness=10;
		super.canBurn=Tree.canBurn;
		super.itemCode=1;
		super.itemNum=0;
		super.itemName="��������";
	}
}
class StonePiece extends Block implements Stone{//2.������
	public StonePiece(){
		super.ingredient=STONE;
		super.isCarved=false;
		super.hardness=20;
		super.canBurn=Stone.canBurn;
		super.itemCode=2;
		super.itemNum=0;
		super.itemName="������";
	}
}
class IronPiece extends Block implements Iron{//3.ö����
	public IronPiece(){
		super.ingredient=IRON;
		super.isCarved=false;
		super.hardness=30;
		super.canBurn=Iron.canBurn;
		super.itemCode=3;
		super.itemNum=0;
		super.itemName="ö����";
	}
}
class WoodStick extends Block implements Tree{//4.���������
	public WoodStick(){
		super.ingredient=TREE;
		super.isCarved=true;
		super.hardness=0;
		super.canBurn=Tree.canBurn;
		super.itemCode=4;
		super.itemNum=0;
		super.itemName="���������";
	}
}
class WoodLump extends Block implements Tree{//5.��������
	public WoodLump(){
		super.ingredient=TREE;
		super.isCarved=true;
		super.hardness=0;
		super.canBurn=Tree.canBurn;
		super.itemCode=5;
		super.itemNum=0;
		super.itemName="��������";
	}
}
class StoneLump extends Block implements Stone{//6.������
	public StoneLump(){
		super.ingredient=STONE;
		super.isCarved=true;
		super.hardness=0;
		super.canBurn=Stone.canBurn;
		super.itemCode=6;
		super.itemNum=0;
		super.itemName="������";
	}
}
class IronLump extends Block implements Iron{//7.ö��
	public IronLump(){
		super.ingredient=IRON;
		super.isCarved=true;
		super.hardness=0;
		super.canBurn=Iron.canBurn;
		super.itemCode=7;
		super.itemNum=0;
		super.itemName="ö��";
	}
}
class Flint extends Block{//14.�ν˵�
	public Flint(){
		super.canBurn=false;
		super.itemCode=14;
		super.itemNum=0;
		super.itemName="�ν˵�";
	}
}