import java.util.List;

public class Main {

        public static void main(String[] args){
            SetOfPoints setOfPoints = new SetOfPoints("points.csv");
            Line line = new Line(setOfPoints.getPointsWithId(3), true);
            SetOfLines setOfLines = new SetOfLines("lines.csv", setOfPoints);
            System.out.println("\nWszystkie linie:\n");
            setOfLines.show();

            System.out.println("\nLinie o fladze true:\n");
            for (Line trueLine:setOfLines.getTrueLines()) {
                trueLine.show();
            }
            setOfLines.BinaryNIO("binary.bin", 'w');
            List<Line> finalList = setOfLines.BinaryNIO("binary.bin", 'r');

            for (Line finalLine: finalList) {
                finalLine.show();

            }


        }
}
