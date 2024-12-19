import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class arraylist {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Double> zahlenList = new ArrayList<>();
        ArrayList<Character> operatorenList = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();

            Pattern numRegex = Pattern.compile("-?\\d+");
            Matcher numMatcher = numRegex.matcher(input);
            boolean numFound = numMatcher.find();

            Pattern opRegex = Pattern.compile("[-+*/=]");
            Matcher opMatcher = opRegex.matcher(input);
            boolean opFound = opMatcher.find();

            StringBuilder berechnungsString = new StringBuilder();

            if (numFound) {
                Double numAdd = Double.parseDouble(input);
                zahlenList.add(numAdd);
            } else if (opFound) {
                if (input.equalsIgnoreCase("=")) {
                    Character opAdd = input.charAt(0);
                    operatorenList.add(opAdd);
                    for (int i = 0; i < operatorenList.size(); i++) {
                        Character op = operatorenList.get(i);
                        Double num = zahlenList.get(i);

                        berechnungsString.append((num)).append(op);

                        String fish = berechnungsString.toString();

                        System.out.println(fish);
                    }
                } else {
                        Character opAdd = input.charAt(0);
                        operatorenList.add(opAdd);
                    }
            } else if (input.equalsIgnoreCase("A")) {
                System.out.println(zahlenList);
                System.out.println(operatorenList);
            } else if (input.equalsIgnoreCase("P")) {

            } else if (input.equalsIgnoreCase("=")) {
                for (int i = 0; i < operatorenList.size(); i++) {
                    Character op = operatorenList.get(i);
                    Double num = zahlenList.get(i);

                    berechnungsString.append((num)).append(op);

                    String fish = berechnungsString.toString();

                    System.out.println(fish);
                }
            } else {
                System.out.println("Dead Error");
            }

        }
    }
}
