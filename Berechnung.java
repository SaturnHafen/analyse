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

    private Berechnung() { return; }

    public static double berechnen(String s) {
        string = s;
        index = 0;
        return rekursivPruefen();
    }

    private static double rekursivPruefen() {
        double a = 0;
        double b = 0;
        double result = 0;

        int lastOperatorIndex = index;
        int operatorIndex = 0;
        boolean firstOperator = true;

        while(index < string.length() && string.charAt(index) != Main.closing) {
            if(string.charAt(index) == Main.opening) {
                index++;
                System.out.println(" <neue Rekursion> ");
                double tempResult = rekursivPruefen();
                System.out.println(" <Rekursion beendet> ");
                if(firstOperator) {
                    result = tempResult;
                    firstOperator = false;
                    index++;
                    operatorIndex = index;
                    continue;
                } else {
                    a = result;
                    result = berechnen(a, tempResult, string.charAt(operatorIndex));
                    index++;
                    operatorIndex = index;
                    continue;
                }
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
                a = result;
                b = 0;
                try {
                    b = Double.parseDouble(string.substring(operatorIndex +1, index));
                } catch(java.lang.NumberFormatException e) { }
                result = berechnen(a, b, string.charAt(operatorIndex));
                operatorIndex = index;
            }
            index++;
        }
        if(operatorIndex < string.length()) {
            a = result;
            b = 0;
            try {
                b = Double.parseDouble(string.substring(operatorIndex +1, index));
            } catch(java.lang.NumberFormatException e) { }
            result = berechnen(a,b,string.charAt(operatorIndex));
        }
        return result;
    }

    private static double berechnen(double a, double b, char operation) {
        System.out.println(" >>> " + a + "  " + operation + " " + b + " = ???" );
        switch(operation) {
            case Main.add        : return a + b;
            case Main.subtract   : return a - b;
            case Main.multiply   : return a * b;
            case Main.divide     : return a / b;
            default              : return 0;
        }
    }
}
