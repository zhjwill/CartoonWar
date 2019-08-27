package com.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import com.action.ActionAble;
import com.client.GameClient;
import com.constant.Constant;
import com.util.GetImageUtil;
import com.util.singlePlay;

public class Bullet extends Gameobj implements ActionAble{
	//�������β������ֵĶ���
	singlePlay singleplay = new singlePlay();
	private int speed;
	public GameClient gc;
	//�ӵ�����
	public boolean IsGood;
    public Bullet() {
    	
    }
    public Bullet(int x,int y,String imgName,GameClient gc,boolean IsGood) {
    	this.x = x;
    	this.y = y;
    	this.img = GetImageUtil.getImg(imgName);
    	this.gc = gc;
    	this.speed = 50;
    	this.IsGood = IsGood;
    }
	@Override
	public void move() {
		if(IsGood) {
		x +=speed;
		}else {
			x -= speed;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
		outOfBound();
	}
    //�ӵ�Խ������
	public void outOfBound() {
		if(x>Constant.GAMEWIDTH||x<0) {
			gc.bullets.remove(this);
		}
	}
	Random random = new Random();
	//�ӵ�һ�������
	public boolean hitMonster(Mouse mouse) {
		//�ж����������Ƿ��ཻ
		Boom boom = new Boom(mouse.x,mouse.y,gc);
		if(this.getRec().intersects(mouse.getRec())&&this.IsGood!=mouse.IsGood) {
			if(mouse.IsGood) {
				mouse.blood -= 10;
				if(mouse.blood==0) {
					//��������
					gc.mouses.remove(mouse);
				}
				//�Ƴ��ӵ�
				gc.bullets.remove(this);
			}
			else{
				singleplay.play("com/sound/SOUND_ANNA_SPECIAL_ENEMYDEATH_01.mp3");
				if(mouse instanceof Boos) {
					mouse.blood -= 5;
					if(mouse.blood <= 0) {
					    gc.booss.remove(mouse);	  
					}
					//�Ƴ��ӵ�
					gc.bullets.remove(this);
				}
				else {
					//�Ƴ����еĹ���
					gc.enemys.remove(mouse);
					//�Ƴ��ӵ�
					gc.bullets.remove(this);
					//����һ�����������
					if(random.nextInt(500)>400){
						if(mouse instanceof EnemyMouse) {
						EnemyMouse enemymouse = (EnemyMouse)mouse;
						Prop prop = new Prop(mouse.x,mouse.y,"com/img/prop/prop.png");
						gc.props.add(prop);
						}
				}
				}
			}
			//��ӱ�ըЧ��
			gc.booms.add(boom);
			return true;
		}
		return false;
	}
	//�ӵ���������
	public boolean hitMonsters(List<Mouse> enemys) {
		for(int i = 0;i<enemys.size();i++) {
			if(hitMonster(enemys.get(i))) {
				return true;
			}
		}
		return false;
	}
	//��ȡ���ӵ��ľ���
	public Rectangle getRec() {
		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
	}
}
