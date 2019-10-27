package javaben.structure.mutable;

import javaben.structure.Node;
import javaben.structure.Structure;

import java.util.EmptyStackException;

public class MutableStack extends Structure {
    private static final int ORDER = 1;
    private Node head;

    public void push(int value) {
        if (head == null) {
            head = new Node(value, 0);
        } else {
            Node node = new Node(value, ORDER);
            node.getNodes()[0] = head;
            head = node;
        }
    }

    public Integer pop() {
        int value;
        try {
            value = head.getValue();
        } catch (NullPointerException e) {
            throw new EmptyStackException();
        }
        try {
            head = head.getNodes()[0];
        } catch (IndexOutOfBoundsException e) {
            head = null;
        }
        return value;
    }

    public int peek() {
        try {
            return head.getValue();
        } catch (NullPointerException e) {
            throw new EmptyStackException();
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}
