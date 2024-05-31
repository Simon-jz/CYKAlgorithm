# CYKAlgorithm
1. Eingabe lesen:
   a. Verwendet 'Scanner', um die EIngabe von der Standard-Eingabe zu lesen.
   b. Liest die Anzahl der Nichtterminale, Terminale, regeln und Wörter ein.
   c. 'scanner.nextLine()' wird verwendet, um die verbleibende Zeile nach dem Lesen 
      der Zahlen zu konsumieren.
   d. Liest die Nichtterminale, Terminale und das Startsymbol ein。
      Liest die Produktionsregeln ein und speichert sie in einem Wörterbuch 
      ruleDict, wobei der Schlüssel die linke Seite der Regel ist und der Wert eine 
      Liste der rechten Seiten ist。
   e. Liest die Eingabewörter ein。
2. CYK-Algorithmus implementieren:
   a.Erstellt ein zweidimensionales Array 'table', wobei jedes Element eine Menge 
     ist, die die Nichtterminale enthält, die den entsprechenden Teilstring 
     erzeugen können。
   b.Füllt die Tabelle für Teilstrings der Länge 1 (einzelne Zeichen)。
   c.Verwendet dynamische Programmierung, um die Tabelle für längere Teilstrings zu 
     füllen, indem kürzere Teilstrings kombiniert werden。
   d.Überprüft schließlich, ob das Startsymbol in 'table[0][n-1]' enthalten ist。
