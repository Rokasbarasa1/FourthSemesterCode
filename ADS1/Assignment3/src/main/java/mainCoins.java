import java.util.HashMap;

public class mainCoins {

    static HashMap<Integer,Integer> pastValues;
    static HashMap<Integer,Integer> basicValues;
    public static void main(String[] args) {
        pastValues = new HashMap<Integer, Integer>();
        basicValues = new HashMap<Integer, Integer>();

        //Coin values and how much each amounts to minimum
        pastValues.put(1,1);
        pastValues.put(7,1);
        pastValues.put(10,1);
        pastValues.put(22,1);

        //Coin values and calculated values as well. For quick finding
        basicValues.put(1,1);
        basicValues.put(7,1);
        basicValues.put(10,1);
        basicValues.put(22,1);

        for (int i = 0; i < 11110000; i++) {
            int minumum = getMinimumChange(i);

            System.out.println("The minimum number of coins you can get with " + i + " is: " + minumum);
        }
    }

    static int getMinimumChange(int money){
        if(money == 0)
            return 0;
        else if(pastValues.containsKey(money))
            return pastValues.get(money);
        int minimum = 0;
        int tempMinimum = 0;
        for (int key : basicValues.keySet()) {
            if((money-key) >= 1){
                tempMinimum = 1 + getMinimumChange(money-key);
                if(tempMinimum < minimum || minimum == 0){
                    minimum = tempMinimum;
                }
            }
        }
        pastValues.put(money,minimum);
        return minimum;
    }
}
