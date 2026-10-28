/* Description: Uses a stack to check whether (), [] and {} delimiters are
   correctly matched and nested, and prints a step-by-step trace of every
   push, peek and pop operation together with the current stack contents.
   Programmed by: <Alea Rawan Barrida> <BSIT-2> <48079> <CC104 Data Structures and Algorithms>
   Last Modified: 2026-10-28
   Version: 2.0
   Acknowledgements: Claude (Anthropic) 
*/
import java.util.Scanner;

public class DelimiterCheckerTrace {

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

        // Bottom to top
        String contents() {
            if (size == 0) return "(empty)";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                if (i > 0) sb.append(' ');
                sb.append(data[i]);
            }
            return sb.toString();
        }
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

    static boolean check(String input) {
        CharStack stack = new CharStack();
        System.out.println("Input: " + (input.isEmpty() ? "(empty)" : input));
        System.out.println();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (isOpening(c)) {
                System.out.println("Read: " + c);
                stack.push(c);
                System.out.println("Operation: PUSH " + c);
                System.out.println("Stack: " + stack.contents());
                System.out.println();
            } else if (isClosing(c)) {
                System.out.println("Read: " + c);
                if (stack.isEmpty()) {
                    System.out.println("Operation: isEmpty -> true");
                    System.out.println("Result: INVALID - closing delimiter '" + c
                            + "' has no matching opening delimiter");
                    return false;
                }
                char top = stack.peek();
                System.out.println("Operation: PEEK -> " + top);    
                if (top == opener(c)) {
                    stack.pop();
                    System.out.println("Operation: POP " + top + " (matches '" + c + "')");
                    System.out.println("Stack: " + stack.contents());
                    System.out.println();
                } else {
                    System.out.println("Stack: " + stack.contents());
                    System.out.println("Result: INVALID - mismatched delimiters: '" + c
                            + "' does not match '" + top + "' on top of the stack");
                    return false;
                }
            }
            // other characters are ignored
        }

        System.out.println("End of input. Stack: " + stack.contents());
        if (!stack.isEmpty()) {
            System.out.println("Result: INVALID - unmatched opening delimiter remains ('"
                    + stack.peek() + "')");
            return false;
        }
        System.out.println("Result: VALID - stack is empty, all delimiters matched");
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a sequence: ");
        String input = in.hasNextLine() ? in.nextLine() : "";
        check(input);
    }
}
