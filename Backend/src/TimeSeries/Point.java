package TimeSeries;

public class Point {
	public final float x;
	public final float y;

	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float distanceSquaredTo(Point p) {
		float DX = this.x - p.x;
		float DY = this.y - p.y;
		return DX * DX + DY * DY;
	}

	public float distanceTo(Point p) {
		return (float)Math.sqrt((double)this.distanceSquaredTo(p));
	}

	public static boolean areColinear(Point p1, Point p2, Point p3) {
		return (double)Math.abs(p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y)) == 0.0D;
	}
	public Point subtract(Point p) {
		return new Point(x - p.x, y - p.y);
	}

	public double cross(Point p) {
		return x * p.y - y * p.x;
	}
}
