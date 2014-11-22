package com.xtremedesign.blackjack;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {
	
	Thread th = new Thread(this);
	static List<Card> cards;
	boolean addcard = false;
	String cardtype;
	public List<String> possiblecards = new ArrayList<String>();
	public HashMap<String, Integer> values = new HashMap<String, Integer>();
	int x = 100;
	int y = 100;
	int value = 0;
	public Screen() {
		addCard();
		addCard();
		th.start();
	}
	
	
	public void registerHashmap() {
		values.put("c2", 2);
		values.put("c3", 3);
		values.put("c4", 4);
		values.put("c5", 5);
		values.put("c6", 6);
		values.put("c7", 7);
		values.put("c8", 8);
		values.put("c9", 9);
		values.put("c10", 10);
		values.put("cj", 10);
		values.put("cq", 10);
		values.put("ck", 10);
		values.put("h2", 2);
		values.put("h3", 3);
		values.put("h4", 4);
		values.put("h5", 5);
		values.put("h6", 6);
		values.put("h7", 7);
		values.put("h8", 8);
		values.put("h9", 9);
		values.put("h10", 10);
		values.put("hj", 10);
		values.put("hq", 10);
		values.put("hk", 10);
		values.put("d2", 2);
		values.put("d3", 3);
		values.put("d4", 4);
		values.put("d5", 5);
		values.put("d6", 6);
		values.put("d7", 7);
		values.put("d8", 8);
		values.put("d9", 9);
		values.put("d10", 10);
		values.put("dj", 10);
		values.put("dq", 10);
		values.put("dk", 10);
		values.put("s2", 2);
		values.put("s3", 3);
		values.put("s4", 4);
		values.put("s5", 5);
		values.put("s6", 6);
		values.put("s7", 7);
		values.put("s8", 8);
		values.put("s9", 9);
		values.put("s10", 10);
		values.put("sj", 10);
		values.put("sq", 10);
		values.put("sk", 10);
	}
	public void updateCards() {
		possiblecards.clear();
		possiblecards.add("c1");
		possiblecards.add("c2");
		possiblecards.add("c3");
		possiblecards.add("c4");
		possiblecards.add("c5");
		possiblecards.add("c6");
		possiblecards.add("c7");
		possiblecards.add("c8");
		possiblecards.add("c9");
		possiblecards.add("c10");
		possiblecards.add("cj");
		possiblecards.add("cq");
		possiblecards.add("ck");
		possiblecards.add("s1");
		possiblecards.add("s2");
		possiblecards.add("s3");
		possiblecards.add("s4");
		possiblecards.add("s5");
		possiblecards.add("s6");
		possiblecards.add("s7");
		possiblecards.add("s8");
		possiblecards.add("s9");
		possiblecards.add("s10");
		possiblecards.add("sj");
		possiblecards.add("sq");
		possiblecards.add("sk");
		possiblecards.add("h1");
		possiblecards.add("h2");
		possiblecards.add("h3");
		possiblecards.add("h4");
		possiblecards.add("h5");
		possiblecards.add("h6");
		possiblecards.add("h7");
		possiblecards.add("h8");
		possiblecards.add("h9");
		possiblecards.add("h10");
		possiblecards.add("hj");
		possiblecards.add("hq");
		possiblecards.add("hk");
		possiblecards.add("d1");
		possiblecards.add("d2");
		possiblecards.add("d3");
		possiblecards.add("d4");
		possiblecards.add("d5");
		possiblecards.add("d6");
		possiblecards.add("d7");
		possiblecards.add("d8");
		possiblecards.add("d9");
		possiblecards.add("d10");
		possiblecards.add("dj");
		possiblecards.add("dq");
		possiblecards.add("dk");
	}
	
	public synchronized void addCard() {
		Random random = new Random();
		String s = possiblecards.get(random.nextInt(possiblecards.size()));
		cardtype = s;
		int value = values.get(cardtype);
		Card card = new Card(cardtype, x, y, value, true);
		cards.add(card);
		cardtype = null;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, 500, 500);
		repaint();
	}
	
	public void run() {
		while(true) {
			
		}
	}

}
