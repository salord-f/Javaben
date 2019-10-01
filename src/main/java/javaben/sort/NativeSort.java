package javaben.sort;

import java.util.Collections;
import java.util.List;

public class NativeSort extends Sort {

	@Override
	public List<Integer> sort(List<Integer> source) {
		Collections.sort(source);
		return source;
	}
}
