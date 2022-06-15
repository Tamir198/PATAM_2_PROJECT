package TimeSeries;

import java.util.*;

public class Hybrid implements TimeSeriesAnomalyDetector {

    private Map<String,Circle> hybridMap =new HashMap<>();
    private Map<String,SimpleAnomalyDetector> linearMap = new HashMap<>();
    private Circle circle;
    private Map<String,Zscore> zScoreDetect;
    private SimpleAnomalyDetector linear;
    private TimeSeries ts;
    private Random random = new Random();

    //setters&getters


    public void setHybridMap(Map<String, Circle> hybridMap) {
        this.hybridMap = hybridMap;
    }

    public Map<String, Circle> getHybridMap() {
        return hybridMap;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Map<String, Zscore> getzScoreDetect() {
        return zScoreDetect;
    }

    public void setzScoreDetect(Map<String, Zscore> zScoreDetect) {
        this.zScoreDetect = zScoreDetect;
    }

    public SimpleAnomalyDetector getLinear() {
        return linear;
    }

    public void setLinear(SimpleAnomalyDetector linear) {
        this.linear = linear;
    }

    @Override
    public void learnNormal(TimeSeries ts) {
        List<Point> points = new ArrayList<>();
        linear.learnNormal(ts);
        List<CorrelatedFeatures> correlated=linear.getNormalModel();
        for(CorrelatedFeatures correlatedFeatures:correlated){
            TimeSeries tempTS=new TimeSeries(correlatedFeatures.feature1, correlatedFeatures.feature2,ts);
            String name = correlatedFeatures.feature1 + "-" + correlatedFeatures.feature2;
            if(Math.abs(correlatedFeatures.correlation)>=0.95){
                linear.learnNormal(tempTS);
                linearMap.put(name,linear);
            }
           else if(Math.abs(correlatedFeatures.correlation)<0.5){
                Zscore zscore=new Zscore(tempTS);
                zscore.learnNormal(tempTS);
                zScoreDetect.put(name,zscore);
            }
            else{
                Map<String,ArrayList<Float>> map = tempTS.getHashTimeSeries();
                for(int i=0;i<map.get(correlatedFeatures.feature1).size();i++){
                    points.add(new Point(map.get(correlatedFeatures.feature1).get(i),
                            map.get(correlatedFeatures.feature2).get(i)));
                }

                hybridMap.put(name,new Circle(points));
            }
        }


    }

    @Override
    public List<AnomalyReport> detect(TimeSeries ts) {
        List<AnomalyReport> anomalyReports = new ArrayList<>();
        List<CorrelatedFeatures> correlated=linear.getNormalModel();
        for(CorrelatedFeatures correlatedFeatures:correlated){
            String description =correlatedFeatures.feature1 + "-" + correlatedFeatures.feature2;
            List<AnomalyReport> tempReports = new ArrayList<>();
            List<Float> featuresList1 = ts.getHashTimeSeries().get(correlatedFeatures.feature1);
            List<Float> featuresList2 = ts.getHashTimeSeries().get(correlatedFeatures.feature2);
            List<Point> points = new ArrayList<>();
            if(hybridMap.containsKey(description)){
                for(int i=0;i<featuresList1.size();i++){
                    points.add(new Point(featuresList1.get(i),featuresList2.get(i)));
                    tempReports.add(new AnomalyReport(description,i + 1));
                }
                if(hybridMap.get(description).contains(points)){
                    anomalyReports.addAll(tempReports);
                }

            } else if(linearMap.containsKey(description)){
                String[] tokens = description.split("-");
                tempReports = linear.detect(new TimeSeries(tokens[0],tokens[1],ts));
                if(tempReports.size() != 0) {
                    anomalyReports.addAll(tempReports);
                }
                /*for(int i=0;i<featuresList1.size();i++){
                    points.add(new Point(featuresList1.get(i),featuresList2.get(i)));
                    tempReports.add(new AnomalyReport(description,i + 1));
                }

                Line line = StatLib.linear_reg(toPointArray(points));
                if(line )*/

            } else if(zScoreDetect.containsKey(description)){
                String[] tokens = description.split("-");
                tempReports = zScoreDetect.get(description).detect(new TimeSeries(tokens[0],tokens[1],ts));
                if(tempReports.size() != 0) {
                    anomalyReports.addAll(tempReports);
                }
            }
        }
        return anomalyReports;
    }
    private static Point[] toPointArray(List<Point> points){
        Point[] pointsArray=new Point[points.size()];
        for(int i=0;i<points.size();i++){
            pointsArray[i]=points.get(i);
        }
        return pointsArray;
    }
    // Creates a points array from two float arrays
    private static Point[] FloatArraytoPointArray(float[] x, float[] y) {
        Point[] points = new Point[x.length];

        for (int nIndex = 0; nIndex < points.length; nIndex++) {
            points[nIndex] = new Point(x[nIndex], y[nIndex]);
        }

        return points;
    }

}

