import java.util.ArrayList;
import java.util.Stack;

public class palindromeStack {

    public static void main(String[] args) {
        String[] words = {"radar", "boom", "kayak", "i saw i was i", "level madam level", "kaka", "able was i ere i saw elba"};

        for (int i = 0; i < words.length; i++) {
            System.out.println("Is word \"" + words[i] + "\" palindrome ? " + checkIfPalindrome(words[i]));
        }
    }

    private static boolean checkIfPalindrome(String word) {
        Stack<Character> letters = new Stack<Character>();
        for (int i = 0; i < word.length(); i++) {
            letters.push(word.charAt(i));
        }
        StringBuilder string = new StringBuilder();
        int palindrome = 1;
        for (int i = 0; i < word.length(); i++) {
            char letter = letters.pop();
            if(string.toString().length() != 1 && string.toString().length() != 0 && string.toString().charAt(i-palindrome) == letter)
                palindrome++;
            else
                palindrome = 1;
            string.append(letter);
        }
        if(palindrome > 1)
            return true;
        else
            return false;
    }
}
