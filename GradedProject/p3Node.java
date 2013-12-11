package GradedProject;

public class p3Node
{
    private final Integer cost;
    private final Integer benefit;

    public p3Node(int aCost, int aBenefit)
    {
        cost   = aCost;
        benefit = aBenefit;
    }

    public int cost()   { return cost; }
    public int benefit() { return benefit; }
    
    public String toString() { return (cost.toString() + "  " + benefit.toString() ); }
}