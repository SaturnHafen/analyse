

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Test-Klasse MainTest.
 *
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class MainTest
{
    /**
     * Konstruktor fuer die Test-Klasse MainTest
     */
    public MainTest() {  }

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @Before
    public void setUp() {  }

    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @After
    public void tearDown() {  }

    @Test
    public void einfach() { assertEquals(2.2, Main.analysierenUndBerechnen("(1+4*3/(2+8))"), 0); }

    @Test
    public void viel() { assertEquals(362880, Main.analysierenUndBerechnen("(1*(2*(3*(4*(5*(6*(7*(8*(9)))))))))"), 0); }
    
    @Test
    public void falsch() { assertEquals(3, Main.analysierenUndBerechnen("(1+2))"), 0); }
}


