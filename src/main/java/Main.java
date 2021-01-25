import java.util.List;

public class Main {

        public static void main(String[] args){
//            Wczytanie danych punktów z pliku .csv

            SetOfPoints setOfPoints = new SetOfPoints("points.csv");
            Line line = new Line(setOfPoints.getPointsWithId(3), true);
            SetOfLines setOfLines = new SetOfLines("lines.csv", setOfPoints);
//
//            Utworzenie obiektów klasy Line:

            Line[] lineObjects = setOfLines.arrayOfLines;
//            System.out.println("\nWszystkie linie:\n");
//            setOfLines.show();
//            System.out.println("\nLinie o fladze true:\n");
//            for (Line trueLine:setOfLines.getTrueLines()) {
//                trueLine.show();
//            }

//            Zapisanie Obiektów Line z flagą true do pliku binarnergo binary.bin

            setOfLines.BinaryNIO("binary.bin", 'w');

//            Wczytanie obiektów Line z pliku binarnego oraz umieszczenie w kolekcji List<Line>

            List<Line> finalList = setOfLines.BinaryNIO("binary.bin", 'r');
//            for (Line finalLine: finalList) {
//                finalLine.show();
//            }
        }
}
