package javaben;

import javaben.structure.immutable.*;
import javaben.structure.mutable.*;
import javaben.structure.natif.NativeArray;
import javaben.structure.natif.NativeStack;

import java.util.List;

public class Benchmark {

	public static Result bench(Callable callable, String method, long seconds, int size, long seed, Generator.Type type) {
		callable.init(size, seed, type);
		return callable.compute(method, seconds);
	}

	public static Result benchStructure(Callable callable, String method, long seconds, int size, long seed, Generator.Type type) {

		List<Integer> list = callable.init(size, seed, type);
		callable = setupStructure(callable, method, list);

		long current = System.nanoTime();
		int iterations = 0;
		while (System.nanoTime() - current < seconds * 1000000000) {
			iterations++;
			runMethod(callable, method, list);
		}
		return new Result(System.nanoTime() - current, iterations);
	}

	private static Callable setupStructure(Callable callable, String method, List<Integer> source) {
		switch (callable.getClass().getSimpleName()) {
			case "MutableArray":
				return setupMutableArray((MutableArray) callable, method, source);
			case "MutableAVL":
				return setupMutableAVL((MutableAVL) callable, method, source);
			case "MutableHeap":
				return setupMutableHeap((MutableHeap) callable, method, source);
			case "MutableQueue":
				return setupMutableQueue((MutableQueue) callable, method, source);
			case "MutableStack":
				return setupMutableStack((MutableStack) callable, method, source);
			case "ImmutableArray":
				return setupImmutableArray((ImmutableArray) callable, method, source);
			case "ImmutableAVL":
				return setupImmutableAVL((ImmutableAVL) callable, method, source);
			case "ImmutableHeap":
				return setupImmutableHeap((ImmutableHeap) callable, method, source);
			case "ImmutableQueue":
				return setupImmutableQueue((ImmutableQueue) callable, method, source);
			case "ImmutableStack":
				return setupImmutableStack((ImmutableStack) callable, method, source);
			case "NativeArray":
				return setupNativeArray((NativeArray) callable, method, source);
			case "NativeStack":
				return setupNativeStack((NativeStack) callable, method, source);
			default:
				throw new UnsupportedOperationException();
		}
	}

	private static Callable setupMutableArray(MutableArray callable, String method, List<Integer> source) {
		switch (method) {
			case "get-random":
				for (int i = 0; i < source.size(); i++) {
					callable.set(source.get(i), i);
				}
				break;
			case "get-sequential":
				for (int i = 0; i < source.size(); i++) {
					callable.set(i, source.get(i));
				}
				break;
		}
		return callable;
	}

	private static Callable setupImmutableArray(ImmutableArray callable, String method, List<Integer> source) {
		switch (method) {
			case "get-random":
				for (int i = 0; i < source.size(); i++) {
					callable = callable.set(source.get(i), i);
				}
				break;
			case "get-sequential":
				for (int i = 0; i < source.size(); i++) {
					callable = callable.set(i, source.get(i));
				}
				break;
		}
		return callable;
	}

	private static Callable setupNativeArray(NativeArray callable, String method, List<Integer> source) {
		switch (method) {
			case "get-random":
				for (int i = 0; i < source.size(); i++) {
					callable.set(source.get(i), i);
				}
				break;
			case "get-sequential":
				for (int i = 0; i < source.size(); i++) {
					callable.set(i, source.get(i));
				}
				break;
		}
		return callable;
	}


	private static Callable setupMutableAVL(MutableAVL callable, String method, List<Integer> source) {
		if ("delete".equals(method) || "search".equals(method)) {
			for (Integer integer : source) {
				callable.insert(integer);
			}
		}
		return callable;
	}

	private static Callable setupImmutableAVL(ImmutableAVL callable, String method, List<Integer> source) {
		if ("delete".equals(method) || "search".equals(method)) {
			for (Integer integer : source) {
				callable = callable.insert(integer);
			}
		}
		return callable;
	}

	private static Callable setupMutableHeap(MutableHeap callable, String method, List<Integer> source) {
		if ("pop".equals(method)) {
			for (Integer integer : source) {
				callable.add(integer);
			}
		}
		return callable;
	}

	private static Callable setupImmutableHeap(ImmutableHeap callable, String method, List<Integer> source) {
		if ("pop".equals(method)) {
			for (Integer integer : source) {
				callable = callable.add(integer);
			}
		}
		return callable;
	}

	private static Callable setupMutableQueue(MutableQueue callable, String method, List<Integer> source) {
		if ("dequeue".equals(method)) {
			for (Integer integer : source) {
				callable.enQueue(integer);
			}
		}
		return callable;
	}

	private static Callable setupImmutableQueue(ImmutableQueue callable, String method, List<Integer> source) {
		if ("dequeue".equals(method)) {
			for (Integer integer : source) {
				callable = callable.enQueue(integer);
			}
		}
		return callable;
	}

	private static Callable setupMutableStack(MutableStack callable, String method, List<Integer> source) {
		if ("pop".equals(method)) {
			for (Integer integer : source) {
				callable.push(integer);
			}
		}
		return callable;
	}

	private static Callable setupImmutableStack(ImmutableStack callable, String method, List<Integer> source) {
		if ("pop".equals(method)) {
			for (Integer integer : source) {
				callable = callable.push(integer);
			}
		}
		return callable;
	}

	private static Callable setupNativeStack(NativeStack callable, String method, List<Integer> source) {
		if ("pop".equals(method)) {
			for (Integer integer : source) {
				callable.push(integer);
			}
		}
		return callable;
	}

	private static void runMethod(Callable callable, String method, List<Integer> source) {
		switch (callable.getClass().getSimpleName()) {
			case "MutableArray":
				runMutableArray((MutableArray) callable, method, source);
				break;
			case "MutableAVL":
				runMutableAVL((MutableAVL) callable, method, source);
				break;
			case "MutableHeap":
				runMutableHeap((MutableHeap) callable, method, source);
				break;
			case "MutableQueue":
				runMutableQueue((MutableQueue) callable, method, source);
				break;
			case "MutableStack":
				runMutableStack((MutableStack) callable, method, source);
				break;
			case "ImmutableArray":
				runImmutableArray((ImmutableArray) callable, method, source);
				break;
			case "ImmutableAVL":
				runImmutableAVL((ImmutableAVL) callable, method, source);
				break;
			case "ImmutableHeap":
				runImmutableHeap((ImmutableHeap) callable, method, source);
				break;
			case "ImmutableQueue":
				runImmutableQueue((ImmutableQueue) callable, method, source);
				break;
			case "ImmutableStack":
				runImmutableStack((ImmutableStack) callable, method, source);
				break;
			case "NativeArray":
				runNativeArray((NativeArray) callable, method, source);
			case "NativeStack":
				runNativeStack((NativeStack) callable, method, source);
			default:
				throw new UnsupportedOperationException();
		}
	}

	private static void runMutableArray(MutableArray callable, String method, List<Integer> source) {
		switch (method) {
			case "set-random":
				for (int i = 0; i < source.size(); i++) {
					callable.set(source.get(i), i);
				}
				break;
			case "set-sequential":
				for (int i = 0; i < source.size(); i++) {
					callable.set(i, source.get(i));
				}
				break;
			case "get-random":
				for (Integer integer : source) {
					callable.get(integer);
				}
				break;
			case "get-sequential":
				for (int i = 0; i < source.size(); i++) {
					callable.get(i);
				}
				break;
		}
	}

	private static void runImmutableArray(ImmutableArray callable, String method, List<Integer> source) {
		switch (method) {
			case "set-random":
				for (int i = 0; i < source.size(); i++) {
					callable = callable.set(source.get(i), i);
				}
				break;
			case "set-sequential":
				for (int i = 0; i < source.size(); i++) {
					callable = callable.set(i, source.get(i));
				}
				break;
			case "get-random":
				for (Integer integer : source) {
					callable.get(integer);
				}
				break;
			case "get-sequential":
				for (int i = 0; i < source.size(); i++) {
					callable.get(i);
				}
				break;
		}
	}

	private static void runNativeArray(NativeArray callable, String method, List<Integer> source) {
		switch (method) {
			case "set-random":
				for (int i = 0; i < source.size(); i++) {
					callable.set(source.get(i), i);
				}
				break;
			case "set-sequential":
				for (int i = 0; i < source.size(); i++) {
					callable.set(i, source.get(i));
				}
				break;
			case "get-random":
				for (Integer integer : source) {
					callable.get(integer);
				}
				break;
			case "get-sequential":
				for (int i = 0; i < source.size(); i++) {
					callable.get(i);
				}
				break;
		}
	}

	private static void runMutableAVL(MutableAVL callable, String method, List<Integer> source) {
		switch (method) {
			case "insert":
				for (Integer item : source) {
					callable.insert(item);
				}
				break;
			case "search":
				for (Integer value : source) {
					callable.search(value);
				}
				break;
			case "delete":
				for (Integer integer : source) {
					callable.delete(integer);
				}
				break;
		}
	}

	private static void runImmutableAVL(ImmutableAVL callable, String method, List<Integer> source) {
		switch (method) {
			case "insert":
				for (Integer item : source) {
					callable = callable.insert(item);
				}
				break;
			case "search":
				for (Integer value : source) {
					callable.search(value);
				}
				break;

			case "delete":
				for (Integer integer : source) {
					callable = callable.delete(integer);
				}
				break;
		}
	}

	private static void runMutableHeap(MutableHeap callable, String method, List<Integer> source) {
		switch (method) {
			case "add":
				for (Integer item : source) {
					callable.add(item);
				}
				break;
			case "pop":
				for (Integer value : source) {
					callable.pop();
				}
				break;
		}
	}

	private static void runImmutableHeap(ImmutableHeap callable, String method, List<Integer> source) {
		switch (method) {
			case "add":
				for (Integer item : source) {
					callable = callable.add(item);
				}
				break;
			case "pop":
				for (Integer value : source) {
					callable = callable.pop().left;
				}
				break;
		}
	}

	private static void runMutableQueue(MutableQueue callable, String method, List<Integer> source) {
		switch (method) {
			case "enqueue":
				for (Integer item : source) {
					callable.enQueue(item);
				}
				break;
			case "dequeue":
				for (Integer value : source) {
					callable.deQueue();
				}
				break;
		}
	}

	private static void runImmutableQueue(ImmutableQueue callable, String method, List<Integer> source) {
		switch (method) {
			case "enqueue":
				for (Integer item : source) {
					callable = callable.enQueue(item);
				}
				break;
			case "dequeue":
				for (Integer value : source) {
					callable = callable.deQueue().left;
				}
				break;
		}
	}

	private static void runMutableStack(MutableStack callable, String method, List<Integer> source) {
		switch (method) {
			case "enqueue":
				for (Integer item : source) {
					callable.push(item);
				}
				break;
			case "dequeue":
				for (Integer value : source) {
					callable.pop();
				}
				break;
		}
	}

	private static void runImmutableStack(ImmutableStack callable, String method, List<Integer> source) {
		switch (method) {
			case "push":
				for (Integer item : source) {
					callable = callable.push(item);
				}
				break;
			case "pop":
				for (Integer value : source) {
					callable = callable.pop().left;
				}
				break;
		}
	}

	private static void runNativeStack(NativeStack callable, String method, List<Integer> source) {
		switch (method) {
			case "enqueue":
				for (Integer item : source) {
					callable.push(item);
				}
				break;
			case "dequeue":
				for (Integer value : source) {
					callable.pop();
				}
				break;
		}
	}
}
