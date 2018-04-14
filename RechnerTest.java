

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Test-Klasse RechnerTest.
 *
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class RechnerTest
{
    /**
     * Konstruktor fuer die Test-Klasse RechnerTest
     */
    public RechnerTest()
    {
    }

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void add()
    {
        Rechner rechner2 = new Rechner();
        assertEquals(45.0, rechner2.berechnen("1+2+3+4+5+6+7+8+9"), 0);
    }
    
    @Test
    public void subtract()
    {
        Rechner rechner2 = new Rechner();
        assertEquals(-43.0, rechner2.berechnen("1-2-3-4-5-6-7-8-9"), 0);
    }
    
    @Test
    public void multiply()
    {
        Rechner rechner2 = new Rechner();
        assertEquals(362880.0, rechner2.berechnen("1*2*3*4*5*6*7*8*9"), 0);
    }
    
    @Test
    public void divide()
    {
        Rechner rechner2 = new Rechner();
        assertEquals(0, rechner2.berechnen("1/2/3/4/5/6/7/8/9"), 1);
    }
}

