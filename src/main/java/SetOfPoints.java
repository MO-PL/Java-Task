import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SetOfPoints {
    Point[] arrayOfPoints;

    public SetOfPoints(String pathToPointsSet) {
        this.arrayOfPoints = readCSVPoints(pathToPointsSet);     //read set from CSV file
    }

    public Point[] readCSVPoints(String path){
        List<Point> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(path))){
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                String[] split = values[0].split(";");         //split the string line
                Point point = new Point(
                        Integer.parseInt(split[0]),
                        Integer.parseInt(split[1]),
                        Integer.parseInt(split[2]));
//                System.out.println("Show point's coords and id:");
//                point.show();
                records.add(point);
            }
//            System.out.println();
//            for (Point record : records) {
//                record.show();
//            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return records.toArray(new Point[0]);
        }


    public Point[] getPointsWithId(int line_id) {
        List<Point> records = new ArrayList<>();

        for (Point point : arrayOfPoints) if (point.id == line_id) records.add(point);

        return records.toArray(new Point[0]);
    }
}
