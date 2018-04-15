public class Klammeranalyse {
    
    private Klammeranalyse() { }

    public static boolean analysiere(String s) {
        int anzahl = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == Main.opening)
                anzahl++;
            else if(s.charAt(i) == Main.closing)
                if(anzahl > 0)
                    anzahl--;
                else
                    return false;
        }
        if(anzahl == 0)
            return true;
        return false;
    }
}