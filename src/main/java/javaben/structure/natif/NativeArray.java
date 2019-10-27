package javaben.structure.natif;


import javaben.structure.Structure;

public class NativeArray extends Structure {
    private Integer[] array;

    public NativeArray(int size) {
        array = new Integer[size];
    }

    public void set(int index, int value) {
        array[index] = value;
    }

    public Integer get(int index) {
        return array[index];
    }
}