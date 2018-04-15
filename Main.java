/**
 * Beschreiben Sie hier die Klasse Main.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Main
{
    public static final char opening    = '(';
    public static final char closing    = ')';
    public static final char add        = '+';
    public static final char subtract   = '-';
    public static final char multiply   = '*';
    public static final char divide     = '/';

    private Main() { }

    public static double analysierenUndBerechnen(String s) {
        System.out.println(" >>> " + s);
        if(Klammeranalyse.analysiere(s)) {
            PunktVorStrichAnalyse.analysieren(s);
            return Berechnung.berechnen(s);
        } else {
            System.out.println(" >>> Ungültige Klammeranzahl");
            return 0;
        }
    }

    public static void main(String[] args) {
        if(args.length == 0)
            return;

        for(int i = 1; i < args.length; i++)
            System.out.println(args[i]);

        System.out.println(analysierenUndBerechnen(args[0]));
     }
}