
/**
 * @author Luke 
 * @version 7.5.2018
 */
public class Task implements Runnable
{
    private String s;
    private boolean forceCheck;
    private Notifyable n;
    
    public Task(String s, boolean forceCheck, Notifyable n)
    {
        this.s = s;
        this.forceCheck = forceCheck;
        this.n = n;
    }

    @Override
    public void run() { n.notify(Main.analysierenUndBerechnen(s, forceCheck)); }
}