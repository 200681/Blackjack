package com.xtremedesign.blackjack;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Screen extends JPanel implements Runnable {
	
	List<Card> cards = new ArrayList<Card>();
	List<DealerCard> dealercards = new ArrayList<DealerCard>();
	String cardtype;
	int totalvalue = 0;
	int dealervalue = 0;
	int bet;
	boolean boughtinsurance = false;
	int money = 500;
	boolean restart;
	public List<String> possiblecards = new ArrayList<String>();
	public HashMap<String, Integer> values = new HashMap<String, Integer>();
	int x = 100;
	int y = 300;
	Thread th = new Thread(this);
	int dealerx = 100;
	int dealery = 100;
	int value = 0;
	public Screen() {
		try {
			System.out.println("" + this.checkFile());
			money = checkFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		updateCards();
		registerHashmap();
		betThread.start();
		th.start();
	}
	
	Thread hitstayThread = new Thread(new Runnable() {
        @SuppressWarnings("deprecation")
		public void run() {

            Scanner scan = new Scanner(System.in);
            String input = "";
            while (true) {
                System.out.println("Hit or stay?!?");
                input = scan.nextLine();
                if(input.equalsIgnoreCase("hit")) {
                	try {
						hit();
					} catch (IOException e) {
						e.printStackTrace();
					}
                } else if(input.equalsIgnoreCase("stay")) {
                	try {
						stay();
					} catch (IOException e) {
						e.printStackTrace();
					}
                	hitstayThread.stop();
                }
            }
        }
    });
	Thread insuranceThread = new Thread(new Runnable() {
        @SuppressWarnings("deprecation")
		public void run() {

            Scanner scan = new Scanner(System.in);
            String input = "";
            while (true) {
                input = scan.nextLine();
                if(input.equalsIgnoreCase("yes")) {
               boughtinsurance = true;
               System.out.println("You have bought insurance!");
               hitstayThread.start();
               insuranceThread.stop();
               money-=bet/2;
                } else if(input.equalsIgnoreCase("no")) {
                	hitstayThread.start();
                	insuranceThread.stop();
                }
            }
        }
    });
	
	
	
	
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
	
	public void cleanup() throws IOException {

		 writeMoneyToFile();
	}
	
	public void writeMoneyToFile() throws IOException {
		String content = "" + money;
		 
		File file = new File("/Users/" + System.getProperty("user.name") + "/Desktop/Blackjack/money.txt");
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();

		System.out.println("Done");
	}
	
	public int checkFile() throws IOException {
		 BufferedReader br = new BufferedReader(new FileReader("/Users/" + System.getProperty("user.name") + "/Desktop/Blackjack/money.txt"));
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		            line = br.readLine();
		        }
		        String everything = sb.toString();
		        int x = Integer.parseInt(everything);
		        return x;
		    } finally {
		        br.close();
		    }
	}
	
	public void restart() {
		restart = true;
	}
	
	
	public synchronized void addDealerCard(boolean visible) {
		Random random = new Random();
		String s = possiblecards.get(random.nextInt(possiblecards.size()));
		cardtype = s;
		int value = 0;
		if(cardtype.equalsIgnoreCase("s1") || cardtype.equalsIgnoreCase("h1") || cardtype.equalsIgnoreCase("d1") || cardtype.equalsIgnoreCase("c1")) {
			if(dealervalue+11==21) {
				value = 11;
			} else {
				value = 1;
			}
		} else {
		 value = values.get(cardtype);
		}
		DealerCard card = new DealerCard(cardtype, dealerx, dealery, value, visible);
	   dealercards.add(card);
		possiblecards.remove(cardtype);
		dealerx+=40;
		dealervalue+=value;
		cardtype = null;
	}

	
	public void start() throws IOException {	
		addCard();
		addCard();
		addDealerCard(false);
		addDealerCard(true);
		if(totalvalue==21) {
			System.out.println("You Win!");
			int x = bet*2;
			money+=x;
			cleanup();
		} else {
		for(DealerCard card : dealercards) {
			if(card.getVisible()) {
				if(card.getCardType().equalsIgnoreCase("s1") || card.getCardType().equalsIgnoreCase("h1") || card.getCardType().equalsIgnoreCase("d1") || card.getCardType().equalsIgnoreCase("c1")) {
					System.out.println("The Dealer has an Ace! Would you like to buy insurance? Type Yes or No!");
					insuranceThread.start();
				} else {
					hitstayThread.start();
				}
			}
		}
		System.out.println("You: " + totalvalue);
		
		}
	
	}
	@SuppressWarnings("deprecation")
	public void hit() throws IOException {
		addCard();
		System.out.println("You: " + totalvalue);
		if(totalvalue==21) {
			System.out.println("You Win!");
			int x = bet*2;
			money+=x;
			cleanup();
			hitstayThread.stop();
		} else if(totalvalue>21) {
		System.out.println("You have bust!");
		cleanup();
		hitstayThread.stop();
		}
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
	@SuppressWarnings({ "static-access" })
	public void run() {
		while(true) {

			if(restart) {

			}
			try {
				th.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	
	public void stay() throws IOException {
		for(DealerCard card : dealercards)
			card.setVisible(true);
		if(dealervalue==21) {
			System.out.println("You Lose! The dealer has a blackjack!");
			if(boughtinsurance) {
				money+=bet;
				money+=bet/2;
				cleanup();
			}
		} else if(dealervalue>16 && dealervalue<21) {
			if(dealervalue>totalvalue) {
				System.out.println("You Lose!");
				cleanup();
			}
			if(dealervalue==totalvalue) {
				System.out.println("It was a tie!");
				money+=bet;
				cleanup();
			}
			if(dealervalue<totalvalue) {
				System.out.println("You Win!");
				int x = bet*2;
				money+=x;
				cleanup();
			}
		} else if(dealervalue<17) {
			addDealerCard(true);
			stay();
		} else if(dealervalue>21) {
			System.out.println("Dealer has bust!");
			int x = bet*2;
			money+=x;
			cleanup();
		}
		
	}
	public synchronized void addCard() {
		Random random = new Random();
		String s = possiblecards.get(random.nextInt(possiblecards.size()));
		cardtype = s;
		int value = 0;
		if(cardtype.equalsIgnoreCase("s1") || cardtype.equalsIgnoreCase("h1") || cardtype.equalsIgnoreCase("d1") || cardtype.equalsIgnoreCase("c1")) {
		if(totalvalue+11==21) {
			value = 11;
		} else {
			value = 1;
		}
		} else {
		 value = values.get(cardtype);
		}
		Card card = new Card(cardtype, x, y, value, true);
	   cards.add(card);
		possiblecards.remove(cardtype);
		x+=40;
		cardtype = null;
		totalvalue+=value;
	
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, 500, 500);
		g.setColor(Color.BLACK);
		g.drawString("Money: " + money , 400, 20);
		g.drawString("BlackJack v1.0 Pre-Alpha Release.", 5, 20);
		g.drawString("Developed by Viraj Prakash", 5, 35);

		for(int x = 0; x<cards.size(); x++) {
			Card card = (Card) cards.get(x);
			if(card.getVisible() && !cards.isEmpty()) {
				g.drawImage(card.getImage(), card.getX(), card.getY(), null);
			}
		}
		
		for(int x = 0; x<dealercards.size(); x++) {
			DealerCard card = (DealerCard) dealercards.get(x);
			g.drawImage(card.getImage(), card.getX(), card.getY(), null);
		}
		repaint();
	}
	Thread betThread = new Thread(new Runnable() {
        @SuppressWarnings("deprecation")
		public void run() {

            Scanner scan = new Scanner(System.in);
            int input = 0;
            while (true) {
                System.out.println("Place Your Bet!: ");
                input = scan.nextInt();
                if(!(money<input)) {
                	if(input>9) {
                	try {
						start();
					} catch (IOException e) {
						e.printStackTrace();
					}
                	money-=input;
                	bet = input;
                    betThread.stop();
                	} else {
                		System.out.println("Your bet needs to be at least 10!");
                	}
                } else {
                	System.out.println("Insufficient Funds!");
                }
            }
        }
    });
	

}
