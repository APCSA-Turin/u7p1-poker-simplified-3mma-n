package com.example.project;

import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2, String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        // first checks if one hand has a higher rank than the other
        if (Utility.getHandRanking(p1Hand) > Utility.getHandRanking(p2Hand)) {
            return "Player 1 wins!";
        } else if (Utility.getHandRanking(p2Hand) > Utility.getHandRanking(p1Hand)) {
            return "Player 2 wins!";
        }

        // finds the highest card in player one's hand
        int p1Max = 0;
        for (Card card : p1.getHand()) {
            if (Utility.getRankValue(card.getRank()) > p1Max) {
                p1Max = Utility.getRankValue(card.getRank());
            }
        }

        // finds the highest card in player two's hand
        int p2Max = 0;
        for (Card card : p2.getHand()) {
            if (Utility.getRankValue(card.getRank()) > p2Max) {
                p2Max = Utility.getRankValue(card.getRank());
            }
        }

        // checks which player has the higher card
        if (p1Max > p2Max) {
            return "Player 1 wins!";
        }
        if (p2Max > p1Max) {
            return "Player 2 wins!";
        }
        return "Tie!";
    }

    public static void play(){ //simulate card playing  
    }

    public static void main(String[] args) {

    }
}