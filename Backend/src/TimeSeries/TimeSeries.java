package TimeSeries;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TimeSeries {

    private HashMap<String, ArrayList<Float>> arrTimeSeries;
    private String[] strFirstLine;

    public TimeSeries(String feature1,String feature2,TimeSeries timeSeries){
        strFirstLine=new String[2];
        strFirstLine[0]=feature1;
        strFirstLine[1]=feature2;
        arrTimeSeries.put(feature1,timeSeries.getHashTimeSeries().get(feature1));
        arrTimeSeries.put(feature2,timeSeries.getHashTimeSeries().get(feature2));

    }
    // The function saves a csv file to a Hashmap
    public TimeSeries(String csvFileName) {
        try {
            Scanner scanner = new Scanner(new File(csvFileName));
            strFirstLine = scanner.nextLine().split(",");
            String[] strNextLine;
            arrTimeSeries = new HashMap<>();
            int nIndex;

            // Saves the first line as keys for the Hashmap and creates a corresponding arraylist for each one
            for (nIndex = 0; nIndex < strFirstLine.length; nIndex++) {
                arrTimeSeries.put(strFirstLine[nIndex], new ArrayList<>());
            }

            // Continues traversing the file from the second line onwards
            while (scanner.hasNextLine()) {
                strNextLine = scanner.nextLine().split(",");
                for (int nCol = 0; nCol < strNextLine.length; nCol++)
                    arrTimeSeries.get(strFirstLine[nCol]).add(Float.parseFloat(strNextLine[nCol]));
            }

            // Closes the file reader
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found !!!");
        }
    }

    public HashMap<String, ArrayList<Float>> getHashTimeSeries() {
        return arrTimeSeries;
    }

    public String[] getHashKeys() {
        return strFirstLine;
    }
}



