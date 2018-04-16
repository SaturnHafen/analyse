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
        double result;
        long timeStamp = System.currentTimeMillis();
        if(Klammeranalyse.analysieren(s)) {
            s = PunktVorStrichAnalyse.analysieren(s);
            result = Berechnung.berechnen(s);
        } else
            result = 0;
        System.out.println();
        System.out.println(" > verstrichene Zeit : " + (System.currentTimeMillis() - timeStamp) + " ms");
        return result;
    }

    public static void main(String[] args) {
        if(args.length == 0)
            return;

        for(int i = 1; i < args.length; i++)
            System.out.println(args[i]);

        System.out.println(analysierenUndBerechnen(args[0]));
    }
}