/**
 * @author Luke
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
                    error(s, i, "Expected '(' before ')' ", System.err);
                    return false;
                }
            else {
                boolean allowed = false;
                for(int a = 0; a < Main.chars.length; a++)
                    if(s.charAt(i) == Main.chars[a]) {
                        allowed = true;
                        break;
                    }
                if(!allowed) {
                    error(s, i, "Unexpected Character : " + s.charAt(i), System.err);
                    return false;
                }
            }
        }
        if(anzahl == 0)
            return true;
        error(s, s.length(), "Expected ')' before '' ", System.err);
        return false;
    }

    private static void error(String s, int index, String errorMsg, java.io.PrintStream ps) {
        ps.println();
        ps.println(" > " + s);
        ps.print("   ");
        for(int i = 0; i < index; i++)
            ps.print(' ');
        ps.println('^');
        ps.println("   " + errorMsg);
    }
}