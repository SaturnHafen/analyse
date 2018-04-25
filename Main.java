/**
 * @author Luke
 * @version 18.04.2018
 */
public class Main
{
    /** Das Zeichen, dass einen neuen Block beginnt */
    public static final char opening    = '(';
    /** Das Zeichen, dass einen Block schließt */
    public static final char closing    = ')';
    /** Das Addierzeichen */
    public static final char add        = '+';
    /** Das Subtrahierzeichen */
    public static final char subtract   = '-';
    /** Das Multiplizierzeichen */
    public static final char multiply   = '*';
    /** Das Dividierzeichen */
    public static final char divide     = '/';
    /** Erlaubte Zeichen [RUNDE KLAMMERN '(' ')' GEHÖREN DAZU] */
    public static final char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '*', '/'};

    private Main() { }

    /**
     * Analysiert den Eingabestring _s_, ob die Anzahl der öffnenden und schließenden Klammern gleich sind, </br>
     * setzt neue Klammern zur gewährleistung der Punkt- vor Strichrechnung und </br>
     * berechnet schlussendlich das Ergebnis 
     *  [!!]Es wird nicht auf ungültige Zeichen geprüft, dies führt möglicherweise zu Fehlern im Rechenprozess[!!]
     * @param s Der zu analysierende String
     * @return Das Ergebnis [Punkt- vor Strichrechnung wird berücksichtigt]
     */
    @Deprecated
    public static double analysierenUndBerechnen(String s) {
        double result;
        boolean ok = false;
        long timeStamp = System.currentTimeMillis();
        if(ok = Klammeranalyse.analysieren(s)) {
            s = PunktVorStrichAnalyse.analysieren(s);
            result = Berechnung.berechnen(s);
        } else
            result = 0;
        System.out.println();
        if(ok)
            System.out.println(" > Berechnung : " + s + " = " + result);
        else
            System.out.println(" > Berechnung : " + s + " = <[ERROR]>" );
        System.out.println(" > verstrichene Zeit : " + (System.currentTimeMillis() - timeStamp) + " ms");
        return result;
    }

    /**
     * Analysiert den Eingabestring _s_, ob die Anzahl der öffnenden und schließenden Klammern gleich sind, </br>
     * setzt neue Klammern zur gewährleistung der Punkt- vor Strichrechnung und </br>
     * berechnet schlussendlich das Ergebnis
     * @param s Der zu analysierende String
     * @param forceCheck Soll der eingegebene String auf ungültige Zeichen untersucht werden
     * @return Das Ergebnis [Punkt- vor Strichrechnung wird berücksichtigt]
     */
    public static double analysierenUndBerechnen(String s, boolean forceCheck) {
        double result;
        boolean ok;
        long timeStamp = System.currentTimeMillis();
        if(ok = Klammeranalyse.analysieren(s, forceCheck)) {
            s = PunktVorStrichAnalyse.analysieren(s);
            result = Berechnung.berechnen(s);
        } else
            result = 0;
        System.out.println();
        if(ok)
            System.out.println(" > Berechnung : " + s + " = " + result);
            else
            System.out.println(" > Berechnung : " + s + " = <[ERROR]>");
        System.out.println(" > verstrichene Zeit : " + (System.currentTimeMillis() - timeStamp) + " ms");
        return result;
    }

    /**
     * Benutzung:
     * @param args <ul>
     *              <li> <[String] Rechenausdruck> </li>
     *              <li> <[String] Rechenausdruck> <[Boolean] forceCheck> </li>
     *             </ul>
     */
    public static void main(String[] args) {
        if(args.length == 1)
            System.out.println(analysierenUndBerechnen(args[0], false));
        else if(args.length == 2)
            System.out.println(analysierenUndBerechnen(args[0], Boolean.parseBoolean(args[1])));
        else {
            System.out.println(" Benutzung: ");
            System.out.println("    <[String] Rechenausdruck>");
            System.out.println("    <[String] Rechenausdruck> <[Boolean] forceCheck>");
            System.out.println();
            System.out.println(" Rechenausdruck : Der zu berechnende Ausdruck");
            System.out.println(" <forceCheck : Soll der Rechenausdruck auf Fehler geprüft werden>");
        }
    }
}