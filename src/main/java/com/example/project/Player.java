package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

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
        return "Nothing";
    }

    public void sortAllCards(){
        
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
        return new ArrayList<Integer>(Arrays.asList(frequency));
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
        return new ArrayList<Integer>(Arrays.asList(frequency));
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
