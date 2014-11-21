package com.xtremedesign.blackjack;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Card {
	int value;
	int y;
	int x;
	String cardtype;
	BufferedImage img;
	boolean visible;
	
	public Card(String cardtype, int x, int y, int value, boolean visible) {
		
		this.cardtype = cardtype;
		this.x = x;
		this.y = y;
		this.value = value;
		this.visible = visible;
		
		try {
			img = ImageIO.read(getClass().getResource("/" + cardtype + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getValue() {
		return this.value;
	}
	public int getY() {
		return this.y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public BufferedImage getImage() {
		return this.img;
	}
	
	public boolean getVisible() {
		return this.visible;
	}

}
