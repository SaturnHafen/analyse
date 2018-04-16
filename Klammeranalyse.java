/**
 * @author SaturnHafen
 * @version 16.04.2018
 */
public class Klammeranalyse {

    private Klammeranalyse() { }

    /**
     * Prüft, ob der Eingabestring _s_ genausoviele öffnende wie schließende Klammern beinhaltet
     * @param s Der zu analysierende String
     * @return Gleiche Anzahl
     */
    public static boolean analysieren(String s) {
        boolean result;
        System.out.println();
        System.out.println(" ---------------Klammeranalyse---------------");
        result = klammerAnalyse(s);
        if(result)
            System.out.println(" > Erfolgreich");
        else
            System.out.println(" > Fehler");
        System.out.println(" ------------Klammeranalyse fertig-----------");
        return result;
    }

    private static boolean klammerAnalyse(String s) {
        int anzahl = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == Main.opening)
                anzahl++;
            else if(s.charAt(i) == Main.closing)
                if(anzahl > 0)
                    anzahl--;
                else {
                    error(s, i, "Expected '(' before ')' ");
                    return false;
                }
        }
        if(anzahl == 0)
            return true;
        error(s, s.length(), "Expected ')' before '' ");
        return false;
    }

    private static void error(String s, int index, String errorMsg) {
        System.err.println(" > " + s);
        System.err.print("   ");
        for(int i = 0; i < index; i++)
            System.err.print(' ');
        System.err.println('^');
        System.err.println("   " + errorMsg);
    }
}