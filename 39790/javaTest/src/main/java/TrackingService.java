public class TrackingService{
    private int total;
    private int goal;
    public void addProtein(int amount){
        total += amount;
        //history.add(new HistoryItem(historyId++, amount "add", total));
    }

    public void removeProtein(int amount){
        total-= amount;
        if(total<0)
            total=0;
        //history.add(new HistoryItem(historyId++, amount "substract", total));
    }

    public int getTotal(){
        return total;
    }

    public void setGoal(int value){
        goal=value;
    }

}