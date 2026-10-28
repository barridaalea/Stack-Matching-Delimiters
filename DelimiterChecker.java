/* Description: Uses a stack to check whether (), [] and {} delimiters in a
   user-entered sequence are correctly matched and properly nested.
   Programmed by: <Alea Rawan Barrida> <BSIT-2> <48079> <CC104 Data Structures and Algorithms>
   Last Modified: 2026-10-28
   Version: 1.0
   Acknowledgements: Claude (Anthropic) 
*/
import java.util.Scanner;

public class DelimiterChecker {

    // Simple array-based stack of chars (written from scratch)
    static class CharStack {
        private char[] data = new char[10];
        private int size = 0;

        void push(char c) {
            if (size == data.length) {
                data = java.util.Arrays.copyOf(data, size * 2);
            }
            data[size++] = c;
        }

        char pop() { return data[--size]; }

        char peek() { return data[size - 1]; }

        boolean isEmpty() { return size == 0; }
    }

    static boolean isOpening(char c) { return c == '(' || c == '[' || c == '{'; }

    static boolean isClosing(char c) { return c == ')' || c == ']' || c == '}'; }

    static char opener(char closing) {
        switch (closing) {
            case ')': return '(';
            case ']': return '[';
            default:  return '{';
        }
    }

    // Returns "Valid" or "Invalid"
    static String check(String input) {
        CharStack stack = new CharStack();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (isOpening(c)) {
                stack.push(c);
            } else if (isClosing(c)) {
                if (stack.isEmpty()) {
                    return "Invalid";
                }
                char top = stack.peek();
                if (top == opener(c)) {
                    stack.pop();
                } else {
                    return "Invalid";
                }
            }
            // any other character is ignored
        }

        if (!stack.isEmpty()) {
            return "Invalid";
        }
        return "Valid";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a sequence: ");
        String input = in.hasNextLine() ? in.nextLine() : "";
        System.out.println(check(input));
    }
}
