package javaben.structure.natif;

import javaben.structure.Structure;

import java.util.Stack;

public class NativeStack extends Structure {
    private Stack<Integer> stack = new Stack<>();

    public void push(int value) {
        stack.push(value);
    }

    public Integer pop() {
        return stack.pop();
    }
}
