class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int value) {
        stack.push(value);
        if (!minStack.isEmpty()) {
            if (minStack.peek() < value) {
                minStack.push(minStack.peek());
            } else {
                minStack.push(value);
            }
        } else {
            minStack.push(value);
        }
    }
    
    public void pop() {
        minStack.pop();
        System.out.print(stack.pop());
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */