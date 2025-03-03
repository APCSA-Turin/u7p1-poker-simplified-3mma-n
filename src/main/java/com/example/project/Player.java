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

    // returns the type of hand the player has
    public String playHand(ArrayList<Card> communityCards){
        allCards = new ArrayList<Card>();
        // fills allCards with cards from the player's hand and the community cards
        for (Card card : hand) {
            allCards.add(card);
        }   
        for (Card card : communityCards) {
            allCards.add(card);
        }

        // sorts the cards in order of ascending value
        sortAllCards();
        // stores the number of times that each rank and suit appear in allCards
        ArrayList<Integer> rankFreq = findRankingFrequency();
        ArrayList<Integer> suitFreq = findSuitFrequency();

        // iterates through the suit frequency list to check if one suit appears 5 times
        boolean isFlush = false;
        for (int freq : suitFreq) {
            if (freq == 5) {
                isFlush = true;
                break;
            }
        }

        // iterates through the rank frequency list to see if 5 ranks in a row appear 1 time
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

        // checks if the player has at least a straight flush
        if (isFlush && isStraight) {
            // checks if the last card in the sorted list is an Ace
            if (allCards.get(4).getRank().equals("A")) {
                return "Royal Flush";
            } else {
                return "Straight Flush";
            }
        }

        // checks for pairs, triples, and quads by iterating through the rank frequency list
        int pairs = 0;
        boolean triple = false;
        for (int i = 0; i < rankFreq.size(); i++) {
            // returns early if a quad appears
            if (rankFreq.get(i) == 4) {
                return "Four of a Kind";
            }
            // stores when any triple appears
            if (rankFreq.get(i) == 3) {
                triple = true;
            }
            // increments the int variable pairs if a pair appears
            if (rankFreq.get(i) == 2) {
                pairs++;
            }
        }

        // uses stored info to return hand types depending on the values of each varuable
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

        // checks if the player has the highest card (compared to the community cards)
        if (hand.get(0) == allCards.get(4) || hand.get(1) == allCards.get(4)) {
            return "High Card";
        }
        return "Nothing";
    }

    // uses insertion sort to sort the list of cards
    public void sortAllCards(){
        for (int i = 1; i < allCards.size(); i++) {
            int val = Utility.getRankValue(allCards.get(i).getRank());
            int j = i;
            // moves the current item down the card list until its rank is not lower than the card before it
            while(j > 0 && val < Utility.getRankValue(allCards.get(j - 1).getRank())) {
                allCards.set(j, allCards.set(j - 1, allCards.get(j)));
                j--;
            }
        }
    } 

    // iterates through the list of cards and returns a list of how many times each rank appears
    public ArrayList<Integer> findRankingFrequency(){
        // creates a new ArrayList and starts the count of each rank at 0
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        for (int i = 0; i < ranks.length; i++) {
            frequency.add(0);
        }
        // checks each card in allCards against the list of ranks
        for (Card card : allCards) {
            for (int i = 0; i < ranks.length; i++) {
                // checks if the current card matches the current rank
                if (card.getRank().equals(ranks[i])) {
                    // increments an item in the frequency list corresponding to the matching rank
                    frequency.set(i, frequency.get(i) + 1);
                    break;
                }
            }
        }
        return frequency;
    }

    // iterates through the list of cards and returns a list of how many times each suit appears
    public ArrayList<Integer> findSuitFrequency() {
        // creates a new ArrayList and starts the count of each suit at 0
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        for (int i = 0; i < suits.length; i++) {
            frequency.add(0);
        }
        // checks each card in allCards against the list of suits
        for (Card card : allCards) {
            for (int i = 0; i < suits.length; i++) {
                // checks if the current card matches the current suit
                if (card.getSuit().equals(suits[i])) {
                    // increments an item in the frequency list corresponding to the matching suit
                    frequency.set(i, frequency.get(i) + 1);
                    break;
                }
            }
        }
        return frequency;
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
