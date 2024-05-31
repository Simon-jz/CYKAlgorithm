import java.util.*;

public class CYKAlgorithm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lese die Anzahl der Nichtterminale, Terminale, Regeln und Wörter ein
        int numNonTerminals = Integer.parseInt(scanner.nextLine().trim());
        int numTerminals = Integer.parseInt(scanner.nextLine().trim());
        int numRules = Integer.parseInt(scanner.nextLine().trim());
        int numWords = Integer.parseInt(scanner.nextLine().trim());

        // Lese die Nichtterminale ein
        String[] nonTerminals = scanner.nextLine().split(" ");

        // Lese die Terminale ein
        String[] terminals = scanner.nextLine().split(" ");

        // Lese das Startsymbol ein
        String startSymbol = scanner.nextLine().trim();

        // Lese die Regeln ein
        Map<String, List<String>> ruleDict = new HashMap<>();
        for (int i = 0; i < numRules; i++) {
            String[] rule = scanner.nextLine().split("->");
            String lhs = rule[0].trim();
            String rhs = rule[1].trim();
            ruleDict.computeIfAbsent(lhs, k -> new ArrayList<>()).add(rhs);
        }

        // Lese die Eingabewörter ein
        String[] words = new String[numWords];
        for (int i = 0; i < numWords; i++) {
            words[i] = scanner.nextLine().trim();
        }

        // Führe den CYK-Algorithmus für jedes Wort aus und gib das Ergebnis aus
        for (String word : words) {
            if (cykAlgorithm(nonTerminals, terminals, startSymbol, ruleDict, word)) {
                System.out.println("Ja");
            } else {
                System.out.println("Nein");
            }
        }

        scanner.close();
    }

    private static boolean cykAlgorithm(String[] nonTerminals, String[] terminals, String startSymbol, 
                                        Map<String, List<String>> ruleDict, String word) {
        int n = word.length();
        @SuppressWarnings("unchecked")
        Set<String>[][] table = new HashSet[n][n];

        // Initialisiere die Tabelle
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = new HashSet<>();
            }
        }

        // Bearbeite Teilstrings der Länge 1
        for (int i = 0; i < n; i++) {
            String terminal = String.valueOf(word.charAt(i));
            for (String lhs : ruleDict.keySet()) {
                if (ruleDict.get(lhs).contains(terminal)) {
                    table[i][i].add(lhs);
                }
            }
        }

        // Bearbeite Teilstrings mit einer Länge > 1
        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                for (int k = i; k < j; k++) {
                    for (String lhs : ruleDict.keySet()) {
                        for (String rhs : ruleDict.get(lhs)) {
                            if (rhs.length() == 2) {
                                String B = String.valueOf(rhs.charAt(0));
                                String C = String.valueOf(rhs.charAt(1));
                                if (table[i][k].contains(B) && table[k + 1][j].contains(C)) {
                                    table[i][j].add(lhs);
                                }
                            }
                        }
                    }
                }
            }
        }

        return table[0][n - 1].contains(startSymbol);
    }
}

