package javaben.structure.natif;


import javaben.structure.common.Array;

public class NativeArray extends Array {
	private Integer[] array;

	public NativeArray(int size) {
		array = new Integer[size];
	}

	@Override
	public void set(int index, int value) {
		array[index] = value;
	}

	@Override
	public Integer get(int index) {
		return array[index];
	}
}