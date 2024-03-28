import java.util.HashMap;
import java.util.Map;

public class Card{
    private static Map<Integer, String> symbolMap = new HashMap<>();
    private static Map<Integer, String> colorMap = new HashMap<>();
    private static Map<Integer, String> patternMap = new HashMap<>();

    int value;
    int shape;
    int color;
    int pattern;
    int seed;
    
    static void setUp(){
        colorMap.put(0, "red");
        colorMap.put(1, "green");
        colorMap.put(2, "purple");

        symbolMap.put(0, "oval");
        symbolMap.put(1, "diamond");
        symbolMap.put(2, "squiggle");

        patternMap.put(0, "solid");
        patternMap.put(1, "open");
        patternMap.put(2, "striped");
    }

    public static Card[] intToCard(int[] cards){
        Card[] set = new Card[cards.length];
        for(int i = 0; i < cards.length; i++){
            set[i] = new Card(cards[i]);
        }
        return set;
    }

    public static Card[] stringToCard(String[] cards){
        Card[] set = new Card[cards.length];
        for(int i = 0; i < cards.length; i++){
            set[i] = new Card(cards[i]);
        }
        return set;
    }

    public static String[] CardsToString(Card[] cards){
        String[] set = new String[cards.length];
        for(int i = 0; i < cards.length; i++){
            set[i] = cards[i].valuesToString();
        }
        return set;
    }

    public Card(String s){
        if(s.length() != 4) System.exit(2);
        this.value = s.charAt(0) - '0';
        this.color = s.charAt(1) - '0';
        this.pattern = s.charAt(2) - '0';
        this.shape = s.charAt(3) - '0';
    }

    public Card(int count, int color, int symbol, int pattern) {
        this.value = count;
        this.color = color;
        this.shape = symbol;
        this.pattern = pattern;
    }

    public Card(int seed){
        int[] values = cardNumToCard(seed);
        this.value = values[0] + 1;
        this.shape = values[1];
        this.color = values[2];
        this.pattern = values[3];
        this.seed = seed;
    }

    // MUST ALWAYS BE 0-80
    public static int[] cardNumToCard(int num){
        int[] seed = new int[4];
        for(int i = 0 ; i < 4; i++){
            seed[i] = num / (int) Math.pow(3, i) % 3;
        }
        return seed;
    }
    
    @Override
    public String toString() {
        return value + " " + colorMap.get(color) + " " + patternMap.get(pattern) + " " + symbolMap.get(shape);
    }

    public String valuesToString(){
        return this.value + "" + this.color + "" + this.shape + "" + this.pattern;
    }

    public boolean sameShape(Card card){
        return this.shape == card.shape;
    }

    public boolean sameColor(Card card){
        return this.color == card.color;
    }

    public boolean samePattern(Card card){
        return this.pattern == card.pattern;
    }

    public boolean sameNumber(Card card){
        return this.value == card.value;
    }
}