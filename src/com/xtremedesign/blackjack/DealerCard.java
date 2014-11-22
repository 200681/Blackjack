package com.xtremedesign.blackjack;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class DealerCard {
	String cardtype;
	int x;
	int y;
	int value;
	boolean visible;
	BufferedImage img;
	
	public DealerCard(String cardtype, int x, int y, int value, boolean visible) {
		this.cardtype = cardtype;
		this.x = x;
		this.y = y;
		this.value = value;
		this.visible = visible;
		if(!visible) {
			try {
				img = ImageIO.read(getClass().getResource("/card_back.png"));
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				img = ImageIO.read(getClass().getResource("/" + cardtype + ".png"));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getX() {
		return this.x;
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	public void setVisible(boolean b) {
		this.visible = b;
	}
	public int getY() {
		return this.y;
	}
	public int getValue() {
		return this.value;
	}
	public String getCardType() {
		return this.cardtype;
	}
	public BufferedImage getImage() {
	
		if(!visible) {
			try {
				img = ImageIO.read(getClass().getResource("/card_back.png"));
				return img;
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				img = ImageIO.read(getClass().getResource("/" + cardtype + ".png"));
				return img;
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	return null;
	
	}
	}

