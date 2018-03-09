package com.hexaware;

/**
 * Hello world!
 *
 */
public class TrackingService
{
    private int total;
    private int goal;

    public  void addProtein( int amount )
    {
        total+=amount;
    }
    public void removeProtein(int amount)
    {
        total-=amount;
        if(total<0)
        total=0;
    }
    public int getTotal()
    {
        return total;
    }
    public void setTotal()
    {
       this.total=total;
    }
    public void setGoal(int value)throws InvalidGoalException
    {
        if (value<0)
        throw new InvalidGoalException();
        goal=value;
    }
    public boolean isGoalMet(){
        return total>=goal;
    }
}
