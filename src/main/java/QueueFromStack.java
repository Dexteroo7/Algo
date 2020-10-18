import java.util.Stack;

//https://leetcode.com/problems/implement-queue-using-stacks/
class QueueFromStack {

    Stack<Integer> buffer = new Stack<>();
    Stack<Integer> reversed = new Stack<>();

    /** Initialize your data structure here. */
    public QueueFromStack() {

    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!reversed.isEmpty())
            buffer.push(reversed.pop());
        buffer.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {

        while (!buffer.isEmpty())
            reversed.push(buffer.pop());
        return reversed.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (!reversed.isEmpty())
            return reversed.peek();
        while (!buffer.isEmpty())
            reversed.push(buffer.pop());
        return reversed.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return reversed.isEmpty() && buffer.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
