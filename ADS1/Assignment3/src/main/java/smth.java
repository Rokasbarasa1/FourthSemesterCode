public class smth {
    public static void main(String[] args) {
        int max = 100;
        int[] table = new int[max+1];
        for (int i = 0; i <= max; i++) {
            int s = 0;
            for (int j = 1; j < i; j++) {
                if(i%j == 0){
                    s=s+i;
                }
            }
            table[i]=s;
        }
        for (int i = 1; i <= max; i++) {
            if(table[i] <= max && i != table[i] && i == table[table[i]])
                System.out.println(i + " and "+ table[i]+"are");
        }
    }
}
