package javaben.structure.mutable;

import javaben.structure.EmptyQueueException;

import java.util.EmptyStackException;

public class MutableQueue {
    private MutableStack s1 = new MutableStack();
    private MutableStack s2 = new MutableStack();

    public void enQueue(int x)
    {
        while (!s1.isEmpty())
        {
            s2.push(s1.pop());
        }

        s1.push(x);

        while (!s2.isEmpty())
        {
            s1.push(s2.pop());
        }
    }

    public int deQueue()
    {
        try {
            return s1.pop();
        } catch (EmptyStackException e) {
            throw new EmptyQueueException();
        }
    }

    public boolean isEmpty() {
        return s1.isEmpty();
    }

}
