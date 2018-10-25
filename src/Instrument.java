
import javax.sound.sampled.*;
import javax.swing.*;

import java.io.File;

public abstract class Instrument extends Item {
	protected int durability=0;//������
	protected int ingredient=0;//���
	protected int effectiveness=0;//ȿ�� (���� �ߵ�ٰų�...)
	protected abstract boolean function(Block b);
	protected abstract boolean function(Item I);// ���߿� ���⿡ ��� ���
	public static void playSound(String audioFile) throws Exception {
       
        Clip clip;
        File soundFile = new File(audioFile);

        Line.Info linfo = new Line.Info (Clip.class);
        Line line = AudioSystem.getLine (linfo);
        clip = (Clip) line;
        AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
        clip.open (ais);
        clip.start();
       
        Thread.sleep(100);
    }

}

abstract class Ax extends Instrument{//����
	public boolean function(Block b){
		
		while(b.hardness>0){//������ �Ⱥμ���������
			if(JOptionPane.showConfirmDialog(null, "������ �����մϴ�.\t("+ b.hardness+")��ŭ ����","������ �����մϴ�" , JOptionPane.PLAIN_MESSAGE)==JOptionPane.OK_OPTION){
				try {
					playSound("./tree.wav");
				} catch (Exception e) {}
				b.hardness-=this.effectiveness;//�νŴ�
			}
			else{
				break;
			}
			
			if(b.hardness<0)//������ �μ����� break;
				break;
			if(this.itemCode!=0){//���� �ƴ϶��
				this.durability-=Tree.tree_effectiveness;//�ı��Ǹ鼭
				if(this.durability<=0){//�ı��Ǹ�
					JOptionPane.showMessageDialog(null, this.itemName+"��(��) �μ������ϴ�.","������ �ļ�",JOptionPane.WARNING_MESSAGE);
					System.out.println();
					this.itemCode=0;
				}
			}
		}
		if(b.hardness<=0){//������ 0���� ���ų� ������
			JOptionPane.showMessageDialog(null, "�������� ����","�������� ����",JOptionPane.INFORMATION_MESSAGE);
			return true;

		}
		else{//0���� ���� ������
			JOptionPane.showMessageDialog(null, "�������� ����","�������� ����",JOptionPane.WARNING_MESSAGE);
			return false;
		}

	}
	protected boolean function(Item I){
		return false;
	}
}
abstract class Pickax extends Instrument{//���
	public boolean function(Block b){
		while(b.hardness>0){//���� �Ⱥμ���������
			if(JOptionPane.showConfirmDialog(null, b.itemName.charAt(0)+"�� ĸ�ϴ�.\t("+ b.hardness+")��ŭ ����","ä���� �����մϴ�" , JOptionPane.PLAIN_MESSAGE)==JOptionPane.OK_OPTION){
				try {
					playSound("./stone.wav");
				} catch (Exception e) {}
				b.hardness-=this.effectiveness;//�νŴ�
			}
			else{
				break;
			}
			if(this.itemCode!=0){//���� �ƴ϶��
				if(b instanceof Stone)
					this.durability-=Stone.stone_effectiveness;//�ı��Ǹ鼭
				else if(b instanceof Iron)
					this.durability-=Iron.iron_effectiveness;//�ı��Ǹ鼭
				if(this.durability<=0){//�ı��Ǹ�
					JOptionPane.showMessageDialog(null, this.itemName+"��(��) �μ������ϴ�.","������ �ļ�",JOptionPane.WARNING_MESSAGE);
					Main.item_list.remove(this);
					this.itemCode=0;
				}
			}
		}
		if(b.hardness<=0){//������ 0���� ���ų� ������
			JOptionPane.showMessageDialog(null, "ä�� ����","ä�� ����",JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		else{//0���� ���� ������
			JOptionPane.showMessageDialog(null, "ä�� ����","ä�� ����",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		}
	protected boolean function(Item I){
		return false;
	}
}

class HandAx extends Ax{
	HandAx(){
		super.durability=20;
		super.effectiveness=3;
		super.ingredient = HAND;
		super.canBurn=false;
		super.itemCode=0;
		super.itemNum=0;
		super.itemName="��";
	}
}
class HandPickax extends Pickax{
	HandPickax(){
		super.durability=20;
		super.effectiveness=3;
		super.ingredient = HAND;
		super.canBurn=false;
		super.itemCode=0;
		super.itemNum=0;
		super.itemName="��";
	}
}


class Lighter extends Instrument{//15.������
	public Lighter(){
		super.canBurn=false;
		super.itemCode=15;
		super.itemNum=0;
		super.itemName="������";
	}
	public boolean function(Item I){

		if(I.canBurn){
			JOptionPane.showMessageDialog(null,I.itemName+"�� �¿����ϴ�.","�¿�� ����!",JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null,I.itemName+"�� �¿�� �����ϴ�!","�¿�� ����!",JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	@Override
	protected boolean function(Block b) {
		return false;
	}

}