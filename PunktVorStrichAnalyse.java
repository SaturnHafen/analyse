
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

    public static void analysieren(String s) {
        string = s;
        index = 0;
        rekursivPruefen();
        System.out.println(" >>> " + string);
    }

    private static void rekursivPruefen() {
        int lastOperatorIndex = index;
        boolean operatorFound = false;
        while(index < string.length() && string.charAt(index) != Main.closing) {
            if(string.charAt(index) == Main.opening) {
                index++;
                System.out.println(" <neue Rekursion> ");
                rekursivPruefen();
            } else if(string.charAt(index) == Main.add || string.charAt(index) == Main.subtract) {
                if(operatorFound) {
                    System.out.println(string.substring(lastOperatorIndex, index));
                    //TODO -> COMPILE
                    klammernEinfuegen(lastOperatorIndex, index);
                    operatorFound = false;
                } else
                    lastOperatorIndex = index;
            }
            else if(string.charAt(index) == Main.multiply || string.charAt(index) == Main.divide) {
                operatorFound = true;
            }
            index++;
        }
        if(operatorFound) {
            System.out.println(" > " + string.substring(lastOperatorIndex, index));
            //TODO -> COMPILE
            klammernEinfuegen(lastOperatorIndex, index);
        }
    }
    
    private static void klammernEinfuegen(int start, int stop) { // start inklusive, stop exklusive
        if(start > stop || stop > string.length() || start > string.length())
            return;
            
        String anfang = string.substring(0, start);
        String mitte = string.substring(start, stop);
        String ende = string.substring(stop);
        
        string = anfang + Main.opening + mitte + Main.closing + ende;
    }
}