package TimeSeries;

public class Line {
	public final float a;
	public final float b;

	public Line(float a, float b) {
		this.a = a;
		this.b = b;
	}

	public float f(float x) {
		return this.a * x + this.b;
	}
}

