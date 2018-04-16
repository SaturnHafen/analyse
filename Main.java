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
        System.out.println();
        System.out.println(" > " + s);
        if(Klammeranalyse.analysieren(s)) {
            System.out.println();
            System.out.println(" > " + s);
            s = PunktVorStrichAnalyse.analysieren(s);

            System.out.println();
            System.out.println(" > " + s);
            result = Berechnung.berechnen(s);
        } else
            result = 0;
        System.out.println();
        System.out.println(" > verstrichene Zeit : " + (System.currentTimeMillis() - timeStamp) + " ms");
        return result;
    }

    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("Formel angeben!");
            return;
        }

        for(int i = 1; i < args.length; i++)
            System.out.println(args[i]);

        System.out.println(analysierenUndBerechnen(args[0]));
    }
}