import java.util.HashMap;

public class Main {
    private static HashMap<Integer, Integer> lengthPriceAdded;


    public static void main(String[] args) {
        lengthPriceAdded = new HashMap<Integer,Integer>();
        lengthPriceAdded.put(1,1);
        lengthPriceAdded.put(2,5);
        lengthPriceAdded.put(3,8);
        lengthPriceAdded.put(4,9);
        lengthPriceAdded.put(5,10);
        lengthPriceAdded.put(6,17);
        //lengthPriceAdded.put(7,17);
        //lengthPriceAdded.put(8,20);
        //lengthPriceAdded.put(9,24);
        //lengthPriceAdded.put(10,30);

        for (int i = 0; i < 30; i++) {
            int cost = costOfSplit(i);
            System.out.println("Cost of length "+ i + " is "+ cost);
            // write your code here
        }
    }

    private static int costOfSplit(int length) {
        if(length == 0)
            return 0;
        else if(lengthPriceAdded.containsKey(length))
            return lengthPriceAdded.get(length);

        int maximum = 0;
        for (int key : lengthPriceAdded.keySet()) {
            if(length>key){
                int tempMax;
                if(lengthPriceAdded.get(length-key) != null)
                    tempMax = lengthPriceAdded.get(key) + lengthPriceAdded.get(length-key);
                else
                    tempMax = lengthPriceAdded.get(key) + costOfSplit(length-key);
                if(tempMax > maximum)
                    maximum = tempMax;
            }
        }
        lengthPriceAdded.put(length,maximum);
        return maximum;
    }
}
