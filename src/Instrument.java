
import javax.sound.sampled.*;
import javax.swing.*;

import java.io.File;

public abstract class Instrument extends Item {
	protected int durability=0;//내구도
	protected int ingredient=0;//재료
	protected int effectiveness=0;//효과 (날이 잘든다거나...)
	protected abstract boolean function(Block b);
	protected abstract boolean function(Item I);// 나중에 여기에 찍는 대상
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

abstract class Ax extends Instrument{//도끼
	public boolean function(Block b){
		
		while(b.hardness>0){//나무가 안부서질떄까지
			if(JOptionPane.showConfirmDialog(null, "나무를 벌목합니다.\t("+ b.hardness+")만큼 남음","벌목을 시작합니다" , JOptionPane.PLAIN_MESSAGE)==JOptionPane.OK_OPTION){
				try {
					playSound("./tree.wav");
				} catch (Exception e) {}
				b.hardness-=this.effectiveness;//부신다
			}
			else{
				break;
			}
			
			if(b.hardness<0)//나무가 부서지면 break;
				break;
			if(this.itemCode!=0){//손이 아니라면
				this.durability-=Tree.tree_effectiveness;//파괴되면서
				if(this.durability<=0){//파괴되면
					JOptionPane.showMessageDialog(null, this.itemName+"이(가) 부서졌습니다.","아이템 파손",JOptionPane.WARNING_MESSAGE);
					System.out.println();
					this.itemCode=0;
				}
			}
		}
		if(b.hardness<=0){//나오고서 0보다 적거나 같으면
			JOptionPane.showMessageDialog(null, "나무베기 성공","나무베기 성공",JOptionPane.INFORMATION_MESSAGE);
			return true;

		}
		else{//0보다 적지 않으면
			JOptionPane.showMessageDialog(null, "나무베기 실패","나무베기 실패",JOptionPane.WARNING_MESSAGE);
			return false;
		}

	}
	protected boolean function(Item I){
		return false;
	}
}
abstract class Pickax extends Instrument{//곡괭이
	public boolean function(Block b){
		while(b.hardness>0){//돌이 안부서질떄까지
			if(JOptionPane.showConfirmDialog(null, b.itemName.charAt(0)+"을 캡니다.\t("+ b.hardness+")만큼 남음","채광을 시작합니다" , JOptionPane.PLAIN_MESSAGE)==JOptionPane.OK_OPTION){
				try {
					playSound("./stone.wav");
				} catch (Exception e) {}
				b.hardness-=this.effectiveness;//부신다
			}
			else{
				break;
			}
			if(this.itemCode!=0){//손이 아니라면
				if(b instanceof Stone)
					this.durability-=Stone.stone_effectiveness;//파괴되면서
				else if(b instanceof Iron)
					this.durability-=Iron.iron_effectiveness;//파괴되면서
				if(this.durability<=0){//파괴되면
					JOptionPane.showMessageDialog(null, this.itemName+"이(가) 부서졌습니다.","아이템 파손",JOptionPane.WARNING_MESSAGE);
					Main.item_list.remove(this);
					this.itemCode=0;
				}
			}
		}
		if(b.hardness<=0){//나오고서 0보다 적거나 같으면
			JOptionPane.showMessageDialog(null, "채광 성공","채광 성공",JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		else{//0보다 적지 않으면
			JOptionPane.showMessageDialog(null, "채광 실패","채광 실패",JOptionPane.WARNING_MESSAGE);
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
		super.itemName="손";
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
		super.itemName="손";
	}
}


class Lighter extends Instrument{//15.라이터
	public Lighter(){
		super.canBurn=false;
		super.itemCode=15;
		super.itemNum=0;
		super.itemName="라이터";
	}
	public boolean function(Item I){

		if(I.canBurn){
			JOptionPane.showMessageDialog(null,I.itemName+"을 태웠습니다.","태우기 성공!",JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null,I.itemName+"은 태울수 없습니다!","태우기 실패!",JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	@Override
	protected boolean function(Block b) {
		return false;
	}

}