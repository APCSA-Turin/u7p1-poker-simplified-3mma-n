package com.example.project;
import java.util.ArrayList;

public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){
        for (Card card : hand) {
            allCards.add(card);
        }   
        for (Card card : communityCards) {
            allCards.add(card);
        }
        sortAllCards();
        ArrayList<Integer> rankFreq = findRankingFrequency();
        ArrayList<Integer> suitFreq = findSuitFrequency();
        boolean isFlush = false;
        for (int freq : suitFreq) {
            if (freq == 5) {
                isFlush = true;
                break;
            }
        }
        
        boolean isStraight = true;
        for (int i = 1; i < allCards.size(); i++) {
            if (Utility.getHandRanking(allCards.get(i - 1).getRank()) != Utility.getHandRanking(allCards.get(i).getRank()) - 1) {
                isStraight = false;
                break;
            }
        }

        int pairs = 0;
        int triples = 0;
        boolean quad;
        for 
        
        return "Nothing";
    }

    public void sortAllCards(){
        for (int i = 1; i < allCards.size(); i++) {
            int val = Utility.getRankValue(allCards.get(i).getRank());
            int j = i;
            while(val < Utility.getRankValue(allCards.get(j - 1).getRank())) {
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
                if (card.getRank().equals(suits[i])) {
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
