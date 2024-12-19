import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Double> zahlenList = new ArrayList<>();
        ArrayList<Character> operatorenList = new ArrayList<>();
        ArrayList<Double> ergebnisList = new ArrayList<>();

        System.out.println("Willkommen zum Taschenrechner.");
        //System.out.println("F = Funktionalitäten Menü / R = Rechnungsbeginn");
        System.out.println("Geben Sie ihre Zahl ein.");

        while (true) {

            String input1 = scanner.nextLine();

            String input = input1.replace(',', '.');

            Pattern numRegex = Pattern.compile("-?\\d+");
            Matcher numMatcher = numRegex.matcher(input);
            boolean numFound = numMatcher.find();

            Pattern opRegex1 = Pattern.compile("[+*/]");
            Matcher opMatcher1 = opRegex1.matcher(input);
            boolean opFound1 = opMatcher1.find();

            boolean noOpAndNum = opFound1 && numFound;

            Pattern opRegex2 = Pattern.compile("-");
            Matcher opMatcher2 = opRegex2.matcher(input);
            boolean opFound2 = opMatcher2.find();

            // klammern als ausnahme regex nutzen //
            Pattern klammernRegex = Pattern.compile("[()]");
            Matcher klammernMatcher = klammernRegex.matcher(input);
            boolean klammernFound = klammernMatcher.find();
            try {
                if (input.equalsIgnoreCase("F")) {
                    System.out.println("C = Leeren des Taschenrechners\n E = Beenden des Taschenrechners");

                }
                if (noOpAndNum) {
                    System.out.println("Keine Operatoren und Zahlen zusammen");
                } else if (opFound1 || opFound2 || numFound || input.equals("=") || input.equals(",")) {
                    if (numFound) {
                        boolean opListVergleich = zahlenList.size() > operatorenList.size();
                        boolean numListSmallerThan2 = zahlenList.isEmpty();

                        if (numListSmallerThan2) {
                            Double numAdd = Double.parseDouble(input);
                            zahlenList.add(numAdd);
                            System.out.println("Geben sie nun ihren Operator ein.(nutzen sie dafür +-*/)");
                        } else if (opListVergleich) {
                            System.out.println("geben sie erst einen Operator ein.");
                        } else {
                            Double numAdd = Double.parseDouble(input);
                            zahlenList.add(numAdd);
                            System.out.println("Geben sie nun das '=' ein um ein Ergebnis zu Erhalten oder weitere Operator.");
                        }
                    } else if (opFound1 || opFound2) {
                        if (input.length() <= 1) {

                            boolean zahlenListVergleich = operatorenList.size() == zahlenList.size();

                            if (zahlenListVergleich) {
                                System.out.println("Geben sie erst eine weitere Zahl ein.");
                            } else {
                                Character opAdd = input.charAt(0);
                                operatorenList.add(opAdd);
                                System.out.println("Geben sie nun ihre nächste Zahl ein.");
                            }
                        } else {
                            System.out.println("Bitte geben sie nur 1 Operator ein. (z.B. + - * /)");
                        }
                    } else if (klammernFound) {
                        if (input.length() == 3) {

                        }
                    } else if (input.equals("=")) {
                        Double ergebnis = zahlenList.getFirst();
                        for (int i = 0; i < operatorenList.size(); i++) {
                            Character operator = operatorenList.get(i);
                            Double zahl = zahlenList.get(i + 1);

                            switch (operator) {
                                case '+':
                                    ergebnis += zahl;
                                    System.out.println(ergebnis);
                                    break;
                                case '-':
                                    ergebnis -= zahl;
                                    System.out.println(ergebnis);
                                    break;
                                case '*':
                                    ergebnis *= zahl;
                                    System.out.println(ergebnis);
                                    break;
                                case '/':
                                    if (zahl != 0) {
                                        ergebnis /= zahl;
                                        System.out.println(ergebnis);
                                    } else {
                                        System.out.println("Keine Division durch null!");
                                    }
                                    break;

                                default:
                                    System.out.println("#Fatal Error# Bitte geben sie C ein und starten von vorne");
                                    break;
                            }
                        }
                    }
                } else if (input.equalsIgnoreCase("A")) {
                    System.out.println("Zahlenlist: " + zahlenList);
                    System.out.println("Operatorenlist: " + operatorenList);
                    System.out.println("Ergebnislist: " + ergebnisList);
                } else if (input.equalsIgnoreCase("C")) {
                    zahlenList.clear();
                    operatorenList.clear();
                    ergebnisList.clear();
                    System.out.println("Taschenrechner wurde Geleert.");
                } else if (input.equalsIgnoreCase("E")) {
                    System.out.println("Programm wird geschlossen!");
                    break;
                } else {
                    System.out.println("Fatal Error");
                }
            } catch (Exception e) {
                System.out.println("Fehlerhafte Eingabe kein minus hinter oder zwischen den Zahlen.");
            }
        }
        scanner.close();
    }
}
// Double.parseDouble(input); in eine If variante schreiben
// Peter Menschensohn des Horst und der Erika aus dem Hause Fitz