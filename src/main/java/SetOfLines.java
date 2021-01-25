import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;
import java.util.*;

import java.nio.file.*;

public class SetOfLines {
    Hashtable<Integer, Boolean> dictionary;
    Line[] arrayOfLines;

    public SetOfLines(String pathToTheDictionary, SetOfPoints setOfPoints) {
        this.dictionary = readCSVDictionary(pathToTheDictionary);
        this.arrayOfLines = setArrayOfLines(setOfPoints);
    }


    public Hashtable<Integer, Boolean> readCSVDictionary(String path) {
        Hashtable<Integer, Boolean> result = new Hashtable<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(path))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                String[] split = values[0].split(";");         //split the string line

                if (split[1].startsWith("true")) {
                    result.put(Integer.parseInt(split[0]), true);
                } else {
                    result.put(Integer.parseInt(split[0]), false);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return result;
    }
    public Line[] setArrayOfLines(SetOfPoints setOfPoints) {
        Set<Integer> keys = dictionary.keySet();
        Line[] result = new Line[keys.size()];
        int i = 0;
        for (int key: keys) {
            result[i] = new Line(setOfPoints.getPointsWithId(key), dictionary.get(key));
        i++;
        }
        return result;
    }
    public Line[] getTrueLines(){
        List<Line> records = new ArrayList<>();
        for (Line line:arrayOfLines) {
            if (line.someFlag) records.add(line);
        }
        return records.toArray(new Line[0]);
    }

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }
    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }

    public  List<Line> BinaryNIO(String path, char mode){
        List<Line> result = new ArrayList<>();
        try {
            long start = System.currentTimeMillis();
            if(mode == 'w' || mode == 'W'){
                System.out.println("No");
                int[] a = {1, 2, 3, 4, 5};
              byte[] allBytes = SerializationUtils.serialize(getTrueLines());
//                byte[] allBytes = SerializationUtils.serialize(a);
                System.out.println(allBytes);
                Files.write(Paths.get(path), allBytes);
            } else if(mode == 'r' || mode == 'R'){
                byte[] allBytes = Files.readAllBytes(Paths.get(path));
                System.out.println(allBytes);
                Line[] arrayOfLines = SerializationUtils.deserialize(allBytes);
                result = Arrays.asList(arrayOfLines);
            }
            long end = System.currentTimeMillis();
            System.out.println("End of IO operation in " + (end - start) + " ms");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    public void show(){
        for (Line line: arrayOfLines){
            line.show();
        }
    }
}
