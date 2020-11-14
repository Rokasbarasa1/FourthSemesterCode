import java.util.HashMap;

public class Main {

    private static HashMap<Integer, Integer> lengthPrice;

    public static void main(String[] args) {
        lengthPrice = new HashMap<Integer,Integer>();
        lengthPrice.put(1,1);
        lengthPrice.put(2,5);
        lengthPrice.put(3,8);
        lengthPrice.put(4,9);
        lengthPrice.put(5,10);
        lengthPrice.put(6,17);
        lengthPrice.put(7,17);
        lengthPrice.put(8,20);
        lengthPrice.put(9,24);
        lengthPrice.put(10,30);
        for (int i = 0; i < 200; i++) {
            int cost = costOfSplit(i);
            System.out.println("Cost of length "+ i + " is "+ cost);
            // write your code here
        }
    }

    private static int costOfSplit(int length) {
        if(length == 0){
            return 0;
        }else if(lengthPrice.containsKey(length)){
            return lengthPrice.get(length);
        }else if((length % 2) == 0){
            for (int i = length; i >= 0; i--) {
                if ((i % 2) == 0 && lengthPrice.containsKey(i)) {
                    if((length%i) == 0){
                        int divisionResult = length/i;
                        int result = divisionResult * lengthPrice.get(i);
                        lengthPrice.put(length, result);
                        return lengthPrice.get(length);
                    }
                }
            }
            return 0;
        }else {
            return 0;//Check if prime
        }
    }


    private static HashMap<Integer, Integer> sortHashMapByKey(){
        return null;
    }

}
