package com.example.project;
import java.util.ArrayList;

public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<Card>();
        allCards = new ArrayList<Card>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){
        allCards = new ArrayList<Card>();
        for (Card card : hand) {
            allCards.add(card);
        }   
        for (Card card : communityCards) {
            allCards.add(card);
        }
        sortAllCards();
        ArrayList<Integer> rankFreq = findRankingFrequency();
        ArrayList<Integer> suitFreq = findSuitFrequency();
        System.out.println(rankFreq);
        System.out.println(suitFreq);
        boolean isFlush = false;
        for (int freq : suitFreq) {
            if (freq == 5) {
                isFlush = true;
                break;
            }
        }
        
        boolean isStraight = false;
        int inRow = 0;
        for (int freq : rankFreq) {
            if (freq == 1) {
                inRow++;
                if (inRow == 5) {
                    isStraight = true;
                    break;
                }
            } else if (inRow > 0) {
                break;
            }
        }

        if (isFlush && isStraight) {
            if (allCards.get(4).getRank().equals("A")) {
                return "Royal Flush";
            } else {
                return "Straight Flush";
            }
        }

        int pairs = 0;
        boolean triple = false;
        for (int i = 0; i < rankFreq.size(); i++) {
            if (rankFreq.get(i) == 4) {
                return "Four of a Kind";
            }
            if (rankFreq.get(i) == 3) {
                triple = true;
            }
            if (rankFreq.get(i) == 2) {
                pairs++;
            }
        }
        if (triple && pairs > 0) {
            return "Full House";
        }
        if (isFlush) {
            return "Flush";
        }
        if (isStraight) {
            return "Straight";
        }
        if (triple) {
            return "Three of a Kind";
        }
        if (pairs == 2) {
            return "Two Pair";
        }
        if (pairs == 1) {
            return "A Pair";
        }
        if (hand.get(0) == allCards.get(4) || hand.get(1) == allCards.get(4)) {
            return "High Card";
        }
        return "Nothing";
    }

    public void sortAllCards(){
        for (int i = 1; i < allCards.size(); i++) {
            int val = Utility.getRankValue(allCards.get(i).getRank());
            int j = i;
            while(j > 0 && val < Utility.getRankValue(allCards.get(j - 1).getRank())) {
                allCards.set(j, allCards.set(j - 1, allCards.get(j)));
                j--;
            }
        }
    } 

    public ArrayList<Integer> findRankingFrequency(){
        int[] frequency = new int[ranks.length];
        for (Card card : allCards) {
            for (int i = 0; i < ranks.length; i++) {
                if (card.getRank().equals(ranks[i])) {
                    frequency[i]++;
                    break;
                }
            }
        }
        ArrayList<Integer> output = new ArrayList<Integer>();
        for (int num : frequency) {
            output.add(num);
        }
        return output;
    }

    public ArrayList<Integer> findSuitFrequency(){
        int[] frequency = new int[suits.length];
        for (Card card : allCards) {
            for (int i = 0; i < suits.length; i++) {
                if (card.getSuit().equals(suits[i])) {
                    frequency[i]++;
                    break;
                }
            }
        }
        ArrayList<Integer> output = new ArrayList<Integer>();
        for (int num : frequency) {
            output.add(num);
        }
        return output;
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
