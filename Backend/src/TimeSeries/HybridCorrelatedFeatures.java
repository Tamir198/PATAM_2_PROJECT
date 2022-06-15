package TimeSeries;

public class HybridCorrelatedFeatures {
    public final String feature1, feature2;
    public final float correlation;



    public HybridCorrelatedFeatures(String feature1, String feature2, float correlation) {
        this.feature1 = feature1;
        this.feature2 = feature2;
        this.correlation = correlation;
    }
}
