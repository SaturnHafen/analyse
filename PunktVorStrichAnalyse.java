/**
 * Gewährleistet die Punkt- vor Strichrechnung, indem Klammern gesetzt werden
 * @author Luke
 * @version 18.04.2018
 */
public class PunktVorStrichAnalyse
{
    private String string;
    private int index;
    private int rekursionsTiefe;

    public PunktVorStrichAnalyse() { }

    /**
     * Prüft den Eingabestring _s_ auf priorisierte Rechenzeichen ['*','/'] und 
     * gewährleistet durch Klammern die richtige Ausführungsreihenfolge
     * @param s Der zu analysierende String
     * @return Der neue String mit Klammern
     */
    public String analysieren(String s) {
        string = s;
        index = 0;
        rekursionsTiefe = 0;
        System.out.println();
        System.out.println(" ----------Punkt- vor Strichanalyse----------");
        rekursivPruefen();
        System.out.println(" ------Punkt- vor Strichanalyse fertig-------");
        return string;
    }

    private void rekursivPruefen() {
        rekursionsTiefe++;
        int lastOperatorIndex = index -1;
        boolean operatorFound = false;
        while(index < string.length() && string.charAt(index) != Main.closing) {
            if(string.charAt(index) == Main.opening) {
                index++;
                printFormattedMessage(" <neue Rekursion> ");
                rekursivPruefen();
                printFormattedMessage(" <Rekursion beendet> ");
            } else if(string.charAt(index) == Main.add || string.charAt(index) == Main.subtract)
                if(operatorFound) {
                    if(string.charAt(index) == Main.subtract && 
                        (string.charAt(index -1) == Main.multiply || string.charAt(index -1) == Main.divide)) {
                        index++;
                        continue;
                    }
                    if(lastOperatorIndex == 0)
                        lastOperatorIndex--;
                    printFormattedMessage(" <" + string.substring(lastOperatorIndex +1, index) + "> ");
                    klammernEinfuegen(lastOperatorIndex +1, index);
                    operatorFound = false;
                    index += 2;
                } else
                    lastOperatorIndex = index;
            else if(string.charAt(index) == Main.multiply || string.charAt(index) == Main.divide)
                operatorFound = true;
            index++;
        }
        if(operatorFound) {
            if(lastOperatorIndex == 0)
                lastOperatorIndex--;
            printFormattedMessage(" <" + string.substring(lastOperatorIndex +1, index) + "> ");
            klammernEinfuegen(lastOperatorIndex +1, index);
            index += 2;
        }
        rekursionsTiefe--;
    }

    private void klammernEinfuegen(int start, int stop) { // start inklusive, stop exklusive
        if(start > stop || stop > string.length() || start > string.length())
            return;

        String anfang = string.substring(0, start);
        String mitte = string.substring(start, stop);
        String ende = string.substring(stop);

        string = anfang + Main.opening + mitte + Main.closing + ende;
    }

    private void printFormattedMessage(String msg) {
        System.out.print(" > ");
        for(int i = 1; i < rekursionsTiefe; i++)
            System.out.print(" | ");
        System.out.println(msg);
    }
}