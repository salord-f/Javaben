package javaben.mapping;

public class Size {
	private final int minX;
	private final int minY;
	private final int maxX;
	private final int maxY;
	private final int sizeScore;

	public Size(int minX, int minY, int maxX, int maxY) {
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;

		this.sizeScore = (int) Math.pow(Math.max(Math.abs(minX - maxX), Math.abs(minY - maxY)), 2);
	}

	public int getMinX() {
		return minX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getSizeScore() {
		return sizeScore;
	}
}
