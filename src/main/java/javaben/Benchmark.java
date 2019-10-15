package javaben;

import javaben.structure.immutable.ImmutableAVL;
import javaben.structure.immutable.ImmutableArray;
import javaben.structure.immutable.ImmutableHeap;
import javaben.structure.immutable.ImmutableQueue;
import javaben.structure.mutable.*;

import java.util.List;

public class Benchmark {

	public static Result bench(Callable callable, String method, long seconds, int size, long seed, Generator.Type type) {
		List<Integer> list = callable.init(size, seed, type);
		callable.setup(method, list);
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
				callable = new MutableStack();
				break;
			case "ImmutableArray":
				callable = new ImmutableArray(0);
				break;
			case "ImmutableAVL":
				return setupImmutableAVL((ImmutableAVL) callable, method, source);
			case "ImmutableHeap":
				return setupImmutableHeap((ImmutableHeap) callable, method, source);
			case "ImmutableQueue":
				return setupImmutableQueue((ImmutableQueue) callable, method, source);
			case "ImmutableStack":
				return setupImmutableArray((ImmutableArray) callable, method, source);
			default:
				throw new UnsupportedOperationException();
		}
		return callable;
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

	private static Callable setupMutableAVL(MutableAVL callable, String method, List<Integer> source) {
		if ("get-random".equals(method)) {
			for (Integer integer : source) {
				callable.insert(integer);
			}
		}
		return callable;
	}

	private static Callable setupImmutableAVL(ImmutableAVL callable, String method, List<Integer> source) {
		if ("get-random".equals(method)) {
			for (Integer integer : source) {
				callable = callable.insert(integer);
			}
		}
		return callable;
	}

	private static Callable setupMutableHeap(MutableHeap callable, String method, List<Integer> source) {
		switch (method) {
			case "get-random":
				for (Integer integer : source) {
					callable.add(integer);
				}
				break;
			case "get-sequential":
				for (int i = 0; i < source.size(); i++) {
					callable.add(i);
				}
				break;
		}
		return callable;
	}

	private static Callable setupImmutableHeap(ImmutableHeap callable, String method, List<Integer> source) {
		switch (method) {
			case "get-random":
				for (Integer integer : source) {
					callable = callable.add(integer);
				}
				break;
			case "get-sequential":
				for (int i = 0; i < source.size(); i++) {
					callable = callable.add(i);
				}
				break;
		}
		return callable;
	}

	private static Callable setupMutableQueue(MutableQueue callable, String method, List<Integer> source) {
		switch (method) {
			case "get-random":
				for (Integer integer : source) {
					callable.enQueue(integer);
				}
				break;
			case "get-sequential":
				for (int i = 0; i < source.size(); i++) {
					callable.enQueue(i);
				}
				break;
		}
		return callable;
	}

	private static Callable setupImmutableQueue(ImmutableQueue callable, String method, List<Integer> source) {
		switch (method) {
			case "get-random":
				for (Integer integer : source) {
					callable = callable.enQueue(integer);
				}
				break;
			case "get-sequential":
				for (int i = 0; i < source.size(); i++) {
					callable = callable.enQueue(i);
				}
				break;
		}
		return callable;
	}

	private static void runMethod(Callable callable, String method, List<Integer> source) {
		switch (callable.getClass().getSimpleName()) {
			case "MutableArray":
				runMutableArray((MutableArray) callable, method, source);
			case "MutableAVL":
				//return setupMutableAVL((MutableAVL) callable, method, source);
			case "MutableHeap":
				callable = new MutableHeap();
				break;
			case "MutableQueue":
				callable = new MutableQueue();
				break;
			case "MutableStack":
				callable = new MutableStack();
				break;
			case "ImmutableArray":
				callable = new ImmutableArray(0);
				break;
			case "ImmutableAVL":
				//return setupImmutableAVL((ImmutableAVL) callable, method, source);
			case "ImmutableHeap":
				callable = new ImmutableHeap();
				break;
			case "ImmutableQueue":
				callable = new ImmutableQueue();
				break;
			case "ImmutableStack":
				//return setupImmutableArray((ImmutableArray) callable, method, source);
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

}
