package javaben.structure.common;

import javaben.structure.Structure;

import java.util.List;

public abstract class Array extends Structure {

	public abstract void set(int index, int value);

	public abstract Integer get(int index);

	@Override
	public void method(String method, List<Integer> source) {
		switch (method) {
			case "set-random":
				for (int i = 0; i < source.size(); i++) {
					this.set(source.get(i), i);
				}
				break;
			case "set-sequential":
				for (int i = 0; i < source.size(); i++) {
					this.set(i, source.get(i));
				}
				break;
			case "get-random":
				for (int i = 0; i < source.size(); i++) {
					this.get(source.get(i));
				}
				break;
			case "get-sequential":
				for (int i = 0; i < source.size(); i++) {
					this.get(i);
				}
				break;
		}
	}

	@Override
	public void setup(String method, List<Integer> source) {
		switch (method) {
			case "get-random":
				for (int i = 0; i < source.size(); i++) {
					this.set(source.get(i), i);
				}
				break;
			case "get-sequential":
				for (int i = 0; i < source.size(); i++) {
					this.set(i, source.get(i));
				}
				break;
		}
	}

}
