package javaben.structure.common;

import javaben.structure.Structure;

import java.util.List;

public abstract class CommonStack extends Structure {


	public abstract void push(int value);

	public abstract Integer pop();

	@Override
	public void setup(String method, List<Integer> source) {
		if ("pop".equals(method)) {
			for (Integer integer : source) {
				this.push(integer);
			}
		}
	}

	@Override
	public void method(String method, List<Integer> source) {
		switch (method) {
			case "push":
				for (Integer integer : source) {
					this.push(integer);
				}
				break;
			case "pop":
				for (int i = 0; i < source.size(); i++) {
					this.pop();
				}
				break;
		}
	}
}
