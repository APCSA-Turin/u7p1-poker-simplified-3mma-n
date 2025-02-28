package com.example.project;

import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2, String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        if (Utility.getHandRanking(p1Hand) > Utility.getHandRanking(p2Hand)) {
            return "Player 1 Wins!";
        } else if (Utility.getHandRanking(p2Hand) > Utility.getHandRanking(p1Hand)) {
            return "Player 2 Wins!";
        }
        int p1Max = 0;
        for (Card card : p1.getAllCards()) {
            if (Utility.getRankValue(card.getRank()) > p1Max) {
                p1Max = Utility.getRankValue(card.getRank());
            }
        }
        int p2Max = 0;
        for (Card card : p2.getAllCards()) {
            if (Utility.getRankValue(card.getRank()) > p1Max) {
                p2Max = Utility.getRankValue(card.getRank());
            }
        }
        if (p1Max > p2Max) {
            return "Player 1 Wins!";
        }
        if (p2Max > p1Max) {
            return "Player 2 Wins!";
        }
        return "Tie!";
    }

    public static void play(){ //simulate card playing  
    }

    public static void main(String[] args) {
        Player player = new Player();
        player.addCard(new Card("9", "♠"));
        player.addCard(new Card("9", "♦"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("9", "♣"));
        communityCards.add(new Card("9", "♥"));
        communityCards.add(new Card("A", "♦"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        System.out.println(handResult);
    }
        
        

}