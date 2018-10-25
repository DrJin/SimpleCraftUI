
public abstract class Item {
	public static final int TREE = 0;
	public static final int STONE = 1;
	public static final int IRON = 2;
	public static final int HAND = 3;
	protected int itemCode;//�������ڵ�
	protected int itemNum;//����
	protected String itemName;//�̸�
	protected boolean canBurn;//Ż �� �ִ���
	public String toString(){return " : " + itemName + "(" + itemNum + "��)";} 
}

/*
 * ������ �ڵ�
 * 0 - ����(��)
 * 1 - ��������
 * 2 - ������
 * 3 - ö����
 * 4 - ���� �����
 * 5 - ���� ����
 * 6 - ������
 * 7 - ö��
 * 8 - ��������
 * 9 - �������
 * 10 - ������
 * 11 - �����
 * 12 - ö����
 * 13 - ö���
 * 14 - �ν˵�
 * 15 - ������(�ν˵� + ö��)
 * 16 - ���
 *  
 */
