package TimeSeries;

import java.util.ArrayList;
import java.util.List;

public class Zscore implements TimeSeriesAnomalyDetector {

    ArrayList<Float> maxZscore = new ArrayList();
    TimeSeries timeSeries;

    public Zscore(TimeSeries timeSeries) {
        this.timeSeries = timeSeries;
    }

    @Override
    public void learnNormal(TimeSeries ts) {
        float maximum;
        for (int nCol = 0; nCol < ts.getHashKeys().length - 1; nCol++) {
            ArrayList<Float> features = ts.getHashTimeSeries().get(ts.getHashKeys()[nCol]);
            maximum = -1.0f;

            for (int nNextCol = nCol + 1; nNextCol < ts.getHashKeys().length; nNextCol++) {
                float std = (float) Math.sqrt(StatLib.var(ArrayListToArray(features)));
                float var_avg = StatLib.avg(ArrayListToArray(features));

                for (int j = 0; j < nNextCol; ++j) {
                    float zScore;
                    if (std != 0.0f) {
                        zScore = Math.abs(features.get(j) - var_avg) / std;
                    } else {
                        zScore = 0.0f;
                    }

                    if (zScore > maximum) {
                        maximum = zScore;
                    }
                }
            }

            this.maxZscore.add(maximum);
        }
    }
    private float[] ArrayListToArray(ArrayList<Float> list) {
        float[] fArray = new float[list.size()];
        for (int nIndex = 0; nIndex < list.size(); nIndex++) {
            fArray[nIndex] = list.get(nIndex);
        }
        return fArray;
    }
    @Override
    public List<AnomalyReport> detect(TimeSeries ts) {
        ArrayList<AnomalyReport> reportList = new ArrayList();
        for (int i = 0; i < ts.getHashKeys().length; i++) {
            ArrayList<Float> features = ts.getHashTimeSeries().get(ts.getHashKeys()[i]);
            for (int j = 2; j < ts.getHashKeys().length; ++j) {
                float x_avg = StatLib.avg(ArrayListToArray(features));
                float std = (float) Math.sqrt(StatLib.var(ArrayListToArray(features)));
                if (std != 0.0f) {
                    for (int s = 0; s < j; ++s) {
                        float Zscore = Math.abs(features.get(s) - x_avg) / std;
                        if (Zscore > maxZscore.get(i)) {
                            AnomalyReport anomalyReport = new AnomalyReport(ts.getHashKeys()[s], (s + 1));
                            if (!StatLib.isContain(reportList, anomalyReport)) {
                                reportList.add(anomalyReport);
                            }
                        }

                    }
                }
            }
        }
        return reportList;
    }
}
