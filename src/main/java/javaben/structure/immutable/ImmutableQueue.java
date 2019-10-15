package javaben.structure.immutable;

import javaben.structure.Structure;
import javaben.structure.exception.EmptyQueueException;
import javaben.structure.Tuple;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ImmutableQueue extends Structure {
    private ImmutableStack stack_in = new ImmutableStack();
    private ImmutableStack stack_out = new ImmutableStack();

    public ImmutableQueue enQueue(int x) {
        return new ImmutableQueue(stack_in.push(x), stack_out.copy());
    }

    public Tuple<ImmutableQueue, Integer> deQueue() {
        ImmutableStack stack_in = this.stack_in;
        ImmutableStack stack_out = this.stack_out;
        if (isEmpty()) {
            throw new EmptyQueueException();
        } else {
            if (stack_out.isEmpty()) {
                while (!stack_in.isEmpty()) {
                    Tuple<ImmutableStack, Integer> tuple = stack_in.pop();
                    stack_in = tuple.left;
                    stack_out = stack_out.push(tuple.right);
                }
            }
        }

        Tuple<ImmutableStack, Integer> tuple = stack_out.pop();
        return new Tuple<>(new ImmutableQueue(stack_in, tuple.left), tuple.right);
    }

    public boolean isEmpty() {
        return stack_in.isEmpty() && stack_out.isEmpty();
    }

    @Override
    public void setup(String method, List<Integer> source) {
        if ("dequeue".equals(method)) {
            for (Integer integer : source) {
                this.enQueue(integer);
            }
        }
    }

    @Override
    public void method(String method, List<Integer> source) {
        switch (method) {
            case "enqueue":
                for (Integer integer : source) {
                    this.enQueue(integer);
                }
                break;
            case "dequeue":
                for (int i = 0; i < source.size(); i++) {
                    this.deQueue();
                }
                break;
        }
    }
}
