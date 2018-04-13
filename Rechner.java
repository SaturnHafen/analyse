/**
 * @author Luke Ortlam
 * @version 12.04.2018
 * @version 1.2
 */
public class Rechner
{
    private String lastResult;
    private String msg;

    /** Das Addierzeichen */
    public static final char add       = '+';
    /** Das Subtrahierzeichen */
    public static final char subtract  = '-';
    /** Das Multiplizierzeichen */
    public static final char multiply  = '*';
    /** Das Dividierzeichen */
    public static final char divide    = '/';

    /** Das Zeichen, dass einen neuen Block beginnt. */
    public final char openingChar;
    /** Das Zeichen, dass einen Block beendet. */
    public final char closingChar;

    /** 
     * Standardkonstruktor  </br>
     * openingChar => '('   </br>
     * closingChar => ')'
     */
    public Rechner() { 
        openingChar = '(';
        closingChar = ')';
    }

    /** 
     * @param opening Das Zeichen, dass einen Block beginnt
     * @param closing Das Zeichen, dass einen Block beendet
     */
    public Rechner(char opening, char closing) {
        openingChar = opening;
        closingChar = closing;
    }

    private boolean pruefen(String msg) {
        boolean ok = true;
        int klammern = 0;

        for(int i = 0; i < msg.length(); i++) {
            if(msg.charAt(i) == openingChar)
                klammern++;
            else if(msg.charAt(i) == closingChar)
                if(klammern != 0)
                    if(klammern > 0) {
                        ok = true;
                        klammern--;
                    } else
                        return false;
                else
                    return false;
            if(klammern == 0)
                ok = true;
            else
                ok = false;
        }
        return ok;
    }

    private int pruefenRekursiv(int index) {
        if(index > msg.length())
            return -1;
        String tempMessage = "";
        while(index < msg.length() && msg.charAt(index) != closingChar) {
            if(msg.charAt(index) == openingChar) {
                index =  pruefenRekursiv(index +1);
                tempMessage += lastResult;
            } else
                tempMessage += msg.charAt(index);
            index += 1;
        }
        if(tempMessage.length() != 0)
            lastResult = "" + parseString(tempMessage);
        return index;
    }

    /**
     * Berechnet das Ergebnis aus dem eingegebenen String. Punkt- vor Strichrechnung wird <u>beachtet</u>!
     * @param msg Die Zeichenfolge, die analysiert werden soll.
     * @return Das Ergebnis.
     */
    public double berechnen(String s) {
        lastResult = "";
        msg = s;
        if(pruefen(msg)) {
            System.out.println(msg);
            findeMalGeteilt(0);
            System.out.println(msg);
            pruefenRekursiv(0);
            System.out.println(" >>> Ergebnis : " + lastResult);
            return Double.parseDouble(lastResult);
        } else 
            System.out.println(" >>> Ungültige Klammeranzahl");
        return 0;
    }

    /**
     * Berechnet das Ergebnis aus dem eingegebenen String.
     * @param msg Die Zeichenfolge, die analysiert werden soll.
     * @param punktVorStrich Soll die Punkt- vor Strichrechnung beachtet werden? </br>
     * Falls nein : <ul>
     *                  <li> primäre Leserichtung : innen => außen    </li> 
     *                  <li> sekundäre Leserichtung : rechts => links </li>
     *              </ul>
     * @return Das Ergebnis.
     */
    public double berechnen(String s, boolean punktVorStrich) {
        lastResult = "";
        msg = s;
        if(pruefen(msg)) {
            System.out.println(msg);
            if(punktVorStrich) {
                findeMalGeteilt(0);
                System.out.println(msg);
            }
            pruefenRekursiv(0);
            System.out.println(" >>> Ergebnis : " + lastResult);
            return Double.parseDouble(lastResult);
        } else 
            System.out.println(" >>> Ungültige Klammeranzahl");
        return 0;
    }

    private int findeMalGeteilt(int index) {
        int lastOperatorIndex = index -1;
        int nextOperatorIndex = 0;
        boolean found = false;
        while(index < msg.length() && msg.charAt(index) != closingChar) {
            if(msg.charAt(index) == openingChar)
                index = findeMalGeteilt(index +1);
            if(msg.charAt(index) == add || msg.charAt(index) == subtract)
                if(!found)
                    lastOperatorIndex = index;
                else {
                    nextOperatorIndex = index;
                    String front = msg.substring(0, lastOperatorIndex +1); 
                    String back = msg.substring(nextOperatorIndex);
                    String middle = msg.substring(lastOperatorIndex +1, nextOperatorIndex);

                    //System.out.println(front + "<=>" + middle + "<=>" + back);

                    msg = front + openingChar + middle + closingChar + back;
                    found = false;
                    index++;
                    nextOperatorIndex = 0;
                }
            if(msg.charAt(index) == multiply || msg.charAt(index) == divide)
                found = true;
            index++;
        }
        if(found) {
            String front = msg.substring(0, lastOperatorIndex +1);
            String back = msg.substring(msg.length());
            String middle = msg.substring(lastOperatorIndex +1, msg.length());
            //System.out.println(front + "<=>" + middle + "<=>" + back);
            msg = front + openingChar + middle + closingChar + back;
        }
        return index;
    }

    private static double berechnen(double a, double b, char zeichen) {
        switch(zeichen) {
            case add:       return a + b;
            case subtract:  return a - b;
            case multiply:  return a * b;
            case divide:    return a / b;
            default:        return 0;
        }
    }

    private static String parseString(String s) {
        char zeichen = ' ';
        boolean zeichenGefunden = false;
        int zeichenIndex = -1;
        s = s.replace(" ", "");
        for(int i = s.length() -1; i >= 0; i--)
            if(!zeichenGefunden)
                if(s.charAt(i) == add) {
                    zeichen = add;
                    zeichenIndex = i;
                    zeichenGefunden = true;
                } else if(s.charAt(i) == subtract) {
                    zeichen = subtract;
                    zeichenIndex = i;
                    zeichenGefunden = true;
                } else if(s.charAt(i) == multiply) {
                    zeichen = multiply;
                    zeichenIndex = i;
                    zeichenGefunden = true;
                } else if(s.charAt(i) == divide) {
                    zeichen = divide;
                    zeichenIndex = i;
                    zeichenGefunden = true;
                } else
                    ;
            else {
                if(s.charAt(i) == add || s.charAt(i) == subtract || s.charAt(i) == multiply || s.charAt(i) == divide) {
                    System.out.println(" <Neue Rekursion>");
                    String temp = parseString(s.substring(0 ,zeichenIndex));
                    s = temp + s.substring(zeichenIndex);
                } else 
                    ;
            }
        double zahlA = 0;
        double zahlB = 0;

        try {
            s.charAt(zeichenIndex);
        } catch(java.lang.StringIndexOutOfBoundsException e) {
            System.out.println(" >>> " + s + " = " + s);
            return s;
        }

        try {
            zahlA = Double.parseDouble(s.substring(0, zeichenIndex));
        } catch(java.lang.NumberFormatException e) { }
        try {
            zahlB = Double.parseDouble(s.substring(zeichenIndex +1));
        } catch(java.lang.NumberFormatException e) { }
        System.out.println(" >>> " + zahlA + " " + zeichen + " " + zahlB + " = " + berechnen(zahlA, zahlB, zeichen));
        return "" + berechnen(zahlA, zahlB, zeichen);
    }

    /**
     * Main-Methode für Konsolenstart
     * @param args Benutzung:
     * <ul>
     * <li> <[String] Mathematischer Ausdruck> </li>
     * <li> <[String] Mathematischer Ausdruck> <[boolean] PunktVorStrich> </li>
     * <li> <[String] Mathematischer Ausdruck> <[boolean] PunktVorStrich> <[char] Öffnendes Zeichen> <[char] Schließendes Zeichen> </li> 
     * </ul>
     */
    public static void main(String[] args) {
        if (args.length == 1)
            new Rechner().berechnen(args[0]);
        else if(args.length == 2) 
            new Rechner().berechnen(args[0], Boolean.parseBoolean(args[1]));
        else if(args.length == 4)
            new Rechner(args[2].charAt(0), args[3].charAt(1)).berechnen(args[0], Boolean.parseBoolean(args[1]));
        else {
            System.out.println("Benutzung:");
            System.out.println("    <[String] Mathematischer Ausdruck> ");
            System.out.println("oder");
            System.out.println("    <[String] Mathematischer Ausdruck> <[boolean] PunktVorStrich>");
            System.out.println("oder");
            System.out.println("    <[String] Mathematischer Ausdruck> <[boolean] PunktVorStrich> <[char] Öffnendes Zeichen> <[char] Schließendes Zeichen>"); 
        }
    }
}