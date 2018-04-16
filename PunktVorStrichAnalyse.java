/**
 * Beschreiben Sie hier die Klasse PunktVorStrichAnalyse.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class PunktVorStrichAnalyse
{
    private static String string;
    private static int index;

    private PunktVorStrichAnalyse() { }

    public static String analysieren(String s) {
        string = s;
        index = 0;
        rekursivPruefen();
        System.out.println(" >>> " + string);
        return string;
    }

    private static void rekursivPruefen() {
        int lastOperatorIndex = index;
        boolean operatorFound = false;
        while(index < string.length() && string.charAt(index) != Main.closing) {
            if(string.charAt(index) == Main.opening) {
                index++;
                System.out.println(" <neue Rekursion> ");
                rekursivPruefen();
                System.out.println(" <Rekursion beendet> ");
            } else if(string.charAt(index) == Main.add || string.charAt(index) == Main.subtract) {
                if(operatorFound) {
                    if(lastOperatorIndex == 0)
                        lastOperatorIndex--;
                    System.out.println(" <Klammern um> " + string.substring(lastOperatorIndex +1, index));
                    klammernEinfuegen(lastOperatorIndex +1, index);
                    operatorFound = false;
                } else {
                    lastOperatorIndex = index;
                }
            }
            else if(string.charAt(index) == Main.multiply || string.charAt(index) == Main.divide) {
                operatorFound = true;
            }
            index++;
        }
        if(operatorFound) {
            if(lastOperatorIndex == 0)
                lastOperatorIndex--;
            System.out.println(" <Klammern um> " + string.substring(lastOperatorIndex +1, index));
            klammernEinfuegen(lastOperatorIndex +1, index);
        }
    }

    private static void klammernEinfuegen(int start, int stop) { // start inklusive, stop exklusive
        if(start > stop || stop > string.length() || start > string.length())
            return;

        String anfang = string.substring(0, start);
        String mitte = string.substring(start, stop);
        String ende = string.substring(stop);

        System.out.println(anfang + " | " + mitte + " | " + ende);

        string = anfang + Main.opening + mitte + Main.closing + ende;
    }
}