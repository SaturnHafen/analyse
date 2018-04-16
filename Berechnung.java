
/**
 * Beschreiben Sie hier die Klasse Berechnung.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Berechnung
{
    private static String string;
    private static int index;
    private static int rekursionsTiefe;

    private Berechnung() { return; }

    public static double berechnen(String s) {
        string = s;
        index = 0;
        rekursionsTiefe = 0;
        double result;
        System.out.println();
        System.out.println(" -----------------Berechnung-----------------");
        result = rekursivPruefen();
        System.out.println(" -------------Berechnung fertig--------------");
        return result;
    }

    private static double rekursivPruefen() {
        rekursionsTiefe++;
        double a = 0;
        double b = 0;
        double result = 0;

        int lastOperatorIndex = index;
        Integer operatorIndex = 0;
        boolean firstOperator = true;;

        while(index < string.length() && string.charAt(index) != Main.closing) {
            if(string.charAt(index) == Main.opening) {
                index++;
                printFormattedMessage(" <neue Rekursion> ");
                double tempResult = rekursivPruefen();
                printFormattedMessage(" <Rekursion beendet> ");
                if(firstOperator) {
                    result = tempResult;
                    firstOperator = false;
                } else {
                    a = result;
                    result = berechnen(a, tempResult, string.charAt(operatorIndex));
                }
                operatorIndex = index;
                index++;
                continue;
            }
            if(string.charAt(index) == Main.add || string.charAt(index) == Main.subtract 
            || string.charAt(index) == Main.multiply || string.charAt(index) == Main.divide) {
                if(firstOperator) {
                    try {
                        result = Double.parseDouble(string.substring(lastOperatorIndex, index));
                    } catch(java.lang.NumberFormatException e) { }
                    firstOperator = false;
                    operatorIndex = index;
                    index++;
                    continue;
                }
                if(string.charAt(operatorIndex) != Main.closing) {
                    a = result;
                    b = 0;
                    try {
                        b = Double.parseDouble(string.substring(operatorIndex +1, index));
                    } catch(java.lang.NumberFormatException e) { }
                    result = berechnen(a, b, string.charAt(operatorIndex));
                    operatorIndex = index;
                } else
                    operatorIndex++;
            }
            index++;
        }
        if(firstOperator) {
            try {
                return Double.parseDouble(string.substring(lastOperatorIndex, index));
            } catch (java.lang.NumberFormatException e) {
                return 0;
            }
        } else if(operatorIndex < string.length()) {
            if(string.charAt(operatorIndex) != Main.closing) {
                a = result;
                b = 0;
                try {
                    b = Double.parseDouble(string.substring(operatorIndex +1, index));
                } catch(java.lang.NumberFormatException e) { }
                result = berechnen(a,b,string.charAt(operatorIndex));
            }
        }
        rekursionsTiefe--;
        return result;
    }

    private static double berechnen(double a, double b, char operation) {
        switch(operation) {
            case Main.add        : printFormattedMessage("" + a + ' ' + operation + ' ' + b + " = " + (a+b)); return a + b;
            case Main.subtract   : printFormattedMessage("" + a + ' ' + operation + ' ' + b + " = " + (a-b)); return a - b;
            case Main.multiply   : printFormattedMessage("" + a + ' ' + operation + ' ' + b + " = " + (a*b)); return a * b;
            case Main.divide     : printFormattedMessage("" + a + ' ' + operation + ' ' + b + " = " + (a/b)); return a / b;
            default              : printFormattedMessage("" + a + ' ' + operation + ' ' + b + " = ???")     ; return 0;
        }
    }

    private static void printFormattedMessage(String msg) {
        System.out.print(" > ");
        for(int i = 1; i < rekursionsTiefe; i++)
            System.out.print(" | ");
        System.out.println(msg);
    }
}