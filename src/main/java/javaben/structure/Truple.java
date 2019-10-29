package javaben.structure;

import lombok.Getter;

@Getter
public class Truple<K, V> extends Tuple<K, V> {

	private boolean bool;

	public Truple(K k, V v, boolean bool) {
		super(k, v);
		this.bool = bool;
	}
}
