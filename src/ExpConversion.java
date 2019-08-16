import java.util.Stack;

// exp conversion class
class ExpConversion {
    // precedence of operators
    static int precedence(char c) {
        if (c == '+' || c == '-') {
            return 1;
        }
        if (c == '*' || c == '/') {
            return 2;
        }
        if (c == '^') {
            return 3;
        }
        return -1;
    }

    // Convert intfix to Postfix expression
    // The link below is the source of infix to postfix algorithm
    // https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/
    public String infixToPostfix(String exp) {
        String postfix = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);
            // if operand, grab it
            if (Character.isLetterOrDigit(c))
                postfix += c;
            // if (
            else if (c == '(')
                stack.push(c);
            // If the scanned character is an ')', pop and output from the stack until an
            // '(' is encountered.
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    postfix += stack.pop();

                if (!stack.isEmpty() && stack.peek() != '(')
                    return "Invalid Expression";
                else
                    stack.pop();
            } else // an operator is encountered
            {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    if (stack.peek() == '(')
                        return "Invalid Expression";
                    postfix += stack.pop();
                }
                stack.push(c);
            }
        }
        // get all the operators from the stack
        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                return "Invalid Expression";
            postfix += stack.pop();
        }
        return postfix;
    }

    // check operator
    static boolean isOperator(char x) {
        String ops = "+-*/^";
        return ops.indexOf(x) != -1;
    }

    // Convert prefix to Postfix expression
    // The link below is the source of infix to postfix algorithm
    // https://www.geeksforgeeks.org/prefix-postfix-conversion/
    public String prefixToPostfix(String exp) {
        Stack<String> stack = new Stack<String>();
        for (int i = exp.length() - 1; i >= 0; i--) {
            if (isOperator(exp.charAt(i))) {
                // get two operands from stack
                if (stack.isEmpty()) {
                    return "Invalid Expression";
                }
                String firstOperator = stack.peek();
                stack.pop();

                if (stack.isEmpty()) {
                    return "Invalid Expression";
                }
                String secondOperator = stack.peek();
                stack.pop();

                stack.push(firstOperator + secondOperator + exp.charAt(i));
            } else {
                // push the operand to the stack
                stack.push(exp.charAt(i) + "");
            }
        }
        // stack contains postfix expression only
        return stack.peek();
    }
}