package ftp17;

import java.util.HashMap;
import java.util.Map;
public class Calc
{
    public int sum(int x, int y)
    {
        return x+y;

    }
    public int max(int x, int y, int c) {
    int m=x;
    if(m<y) {
        m=y;
    }
   if(m<c)
    {
        m=c;
    }
    return m;
    }
    

    public int minArray(int [] x) {
        int min= x[0];
        for (int i=0;i<x.length;i++) {
            if(min> x[i])
            min=x[i];

        }
        return min;
    }
    public boolean even(int x) {
        if(x%2==0) {
            return true;
        } else {
            return false;
        }
    }

    public String getPropertyTest(String key) {
        Map<String,String> m = new HashMap<String, String>();
        m.put("meena", "java");
        m.put("bhargav", "reddy");
        m.put("Harish","k");
        m.put("sai","bharath");
        return m.get(key);
    }
    public int div(int a, int b) {
        return a/b;
    }
}