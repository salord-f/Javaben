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

		long start, time = 0;
		long t0 = System.nanoTime();
		int iterations = 0;
		while (System.nanoTime() -t0 < seconds * 1000000000) {
			iterations++;
			callable = setupStructure(callable, method, list);
			start = System.nanoTime();
			runMethod(callable, method, list);
			time += System.nanoTime() - start;
		}
		return new Result(time, iterations);
	}

	private static Callable setupStructure(Callable callable, String method, List<Integer> source) {
		switch (callable.getClass().getSimpleName()) {
			case "MutableArray":
				return setupMutableArray(method, source);
			case "MutableAVL":
				return setupMutableAVL(method, source);
			case "MutableHeap":
				return setupMutableHeap(method, source);
			case "MutableQueue":
				return setupMutableQueue(method, source);
			case "MutableStack":
				return setupMutableStack(method, source);
			case "ImmutableArray":
				return setupImmutableArray(method, source);
			case "ImmutableAVL":
				return setupImmutableAVL(method, source);
			case "ImmutableHeap":
				return setupImmutableHeap(method, source);
			case "ImmutableQueue":
				return setupImmutableQueue(method, source);
			case "ImmutableStack":
				return setupImmutableStack(method, source);
			case "NativeArray":
				return setupNativeArray(method, source);
			case "NativeStack":
				return setupNativeStack(method, source);
			default:
				throw new UnsupportedOperationException();
		}
	}

	private static Callable setupMutableArray(String method, List<Integer> source) {
		MutableArray callable = new MutableArray(source.size());
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

	private static Callable setupImmutableArray(String method, List<Integer> source) {
		ImmutableArray callable = new ImmutableArray(source.size());
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

	private static Callable setupNativeArray(String method, List<Integer> source) {
		NativeArray callable = new NativeArray(source.size());
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


	private static Callable setupMutableAVL(String method, List<Integer> source) {
		MutableAVL callable = new MutableAVL();
		if ("delete".equals(method) || "search".equals(method)) {
			for (Integer integer : source) {
				callable.insert(integer);
			}
		}
		return callable;
	}

	private static Callable setupImmutableAVL(String method, List<Integer> source) {
		ImmutableAVL callable = new ImmutableAVL();
		if ("delete".equals(method) || "search".equals(method)) {
			for (Integer integer : source) {
				callable = callable.insert(integer);
			}
		}
		return callable;
	}

	private static Callable setupMutableHeap(String method, List<Integer> source) {
		MutableHeap callable = new MutableHeap();
		if ("pop".equals(method)) {
			for (Integer integer : source) {
				callable.add(integer);
			}
		}
		return callable;
	}

	private static Callable setupImmutableHeap(String method, List<Integer> source) {
		ImmutableHeap callable = new ImmutableHeap();
		if ("pop".equals(method)) {
			for (Integer integer : source) {
				callable = callable.add(integer);
			}
		}
		return callable;
	}

	private static Callable setupMutableQueue(String method, List<Integer> source) {
		MutableQueue callable = new MutableQueue();
		if ("dequeue".equals(method)) {
			for (Integer integer : source) {
				callable.enQueue(integer);
			}
		}
		return callable;
	}

	private static Callable setupImmutableQueue(String method, List<Integer> source) {
		ImmutableQueue callable = new ImmutableQueue();
		if ("dequeue".equals(method)) {
			for (Integer integer : source) {
				callable = callable.enQueue(integer);
			}
		}
		return callable;
	}

	private static Callable setupMutableStack(String method, List<Integer> source) {
		MutableStack callable = new MutableStack();
		if ("pop".equals(method)) {
			for (Integer integer : source) {
				callable.push(integer);
			}
		}
		return callable;
	}

	private static Callable setupImmutableStack(String method, List<Integer> source) {
		ImmutableStack callable = new ImmutableStack();
		if ("pop".equals(method)) {
			for (Integer integer : source) {
				callable = callable.push(integer);
			}
		}
		return callable;
	}

	private static Callable setupNativeStack(String method, List<Integer> source) {
		NativeStack callable = new NativeStack();
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
				break;
			case "NativeStack":
				runNativeStack((NativeStack) callable, method, source);
				break;
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
			case "push":
				for (Integer item : source) {
					callable.push(item);
				}
				break;
			case "pop":
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
			case "push":
				for (Integer item : source) {
					callable.push(item);
				}
				break;
			case "pop":
				for (Integer value : source) {
					callable.pop();
				}
				break;
		}
	}
}
