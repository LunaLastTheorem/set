import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Board {
    final int RANDOM = 0;
    final int SOLVER = 1;
    final int DEBUG = 2;
    List<Card> cardBoard = new LinkedList<>();
    List<Integer> allCards = new ArrayList<>();
    Stack<Integer> cardStack = new Stack<>();

    Board(int mode, Scanner scan){
        Card.setUp();
        if(mode == RANDOM){
            randomMode();
            //findSets();
        }
        else if(mode == SOLVER){
            System.out.println("Please input cards by row left to right in the mode \"0000\":");
            System.out.println("[0]: red    solid    oval");
            System.out.println("[1]: green    open    diamond");
            System.out.println("[2]: purple    striped    squiggly");
            for(int i = 0; i < 12; i++){
                String nextCard = scan.nextLine();
                while(!nextCard.matches("[012]+") || nextCard.length() != 4){
                    System.out.println("invalid card");
                    nextCard = scan.nextLine();
                }
                cardBoard.add(new Card(nextCard));
            }
            printBoard();
            findSets();
        }else if(mode == DEBUG){
            debugMode();
            while(!cardStack.isEmpty()){
                int[] cardsToRemove = new int[3];
                printBoard();
                System.out.println(cardStack);
                for(int i = 0; i < 3; i++){
                    int cardsSubmit = scan.nextInt();
                    while(cardsSubmit > 11){
                        System.out.println("not valid");
                        cardsSubmit = scan.nextInt();
                    }
                    cardsToRemove[i] = cardsSubmit;
                }
                debugSubmit(cardsToRemove);
            }
        }
    }

    void debugMode(){
        for(int i = 0 ; i < 10; i++){ //TODO swithc back to 81
            allCards.add(i);
        }
        Collections.shuffle(allCards);
        cardStack.addAll(allCards);
        for(int j = 0; j < 3; j++){ //TODO switch back to 12
            cardBoard.add(new Card(cardStack.pop()));
        }
    }

    void randomMode(){
        for(int i = 0 ; i < 81; i++){ //TODO swithc back to 81
            allCards.add(i);
        }
        Collections.shuffle(allCards);
        cardStack.addAll(allCards);
        for(int j = 0; j < 81; j++){ //TODO switch back to 12
            cardBoard.add(new Card(cardStack.pop()));
        }
    }

    void debugSubmit(int[] cards){
        for(int i = 0; i < 3; i++){
            cardBoard.remove(cards[i]);
            if(!cardStack.isEmpty()){
                cardBoard.add(new Card(cardStack.pop()));
            }else{
                cardBoard.add(cards[i], null);
            }
        }
    }

    void sumbitSet(int[] cards){
        if(verifySet(Card.intToCard(cards))){ 
            System.out.println("Set Found!");
            for(int i = 0; i < 3; i++){
                cardBoard.remove(cards[i]);
                if(!cardStack.isEmpty()){
                    cardBoard.add(new Card(cardStack.pop()));
                }
            }
        }
        else{
            System.out.println("not a set");
        }
    }

    void findSets(){
        for(int first = 0; first < cardBoard.size() - 2; first++){
            for(int second = first + 1; second < cardBoard.size() - 1; second++){
                for(int third = second + 1; third < cardBoard.size(); third++){
                    if(verifySet(cardBoard.get(first), cardBoard.get(second), cardBoard.get(third))){

                            System.out.println(cardBoard.get(first) + ", " + cardBoard.get(second) + ", " + cardBoard.get(third) + " is a set!");
                            
                    }
                }
            }
        }
    }

    boolean verifySet(Card card1, Card card2, Card card3){
        Card[] set = {card1, card2, card3};
        return verifySet(set);
    }

    boolean verifySet(Card[] set){
        int trueCount = 0;
        for(int i = 0; i < 4; i++){
            if(validAttributeSet(set, i)){
                trueCount++;
            }
        }
        return trueCount == 4;
    }

    boolean validAttributeSet(Card[] set, int i){
        int trueCount = 0;
        String[] cardStrings = Card.CardsToString(set);

        for(int first = 0; first < set.length - 1; first++){
            for(int second = first + 1; second < set.length; second++){
                if(cardStrings[first].charAt(i) == cardStrings[second].charAt(i)){
                    trueCount++;
                }
            }
        }

        if(trueCount == 0 || trueCount == 3) return true;
        return false;
    }

    void printBoard(){
        for(int i = 0; i < cardBoard.size(); i++){
            Card next = cardBoard.get(i);
            if(next != null){
                System.out.println("[" + i + "] "+ next);
            }
        }
    }

    public List<Card> getCardBoard() {
        return cardBoard;
    }

    public List<Integer> getAllCards() {
        return allCards;
    }

    public Stack<Integer> getCardStack() {
        return cardStack;
    }
}
