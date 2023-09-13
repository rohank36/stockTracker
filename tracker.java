//Program: Stock Profit Tracker
//By: Rohan Kanti
//Date: 2023-09-12

import java.util.*;

public class tracker{

    public static void main (String[] args){
        List<String> events = new ArrayList<>();
        
        events.add("BUY googl 20");
        events.add("BUY aapl 50");
        events.add("CHANGE googl 6");
        events.add("QUERY");
        events.add("SELL aapl 10");
        events.add("CHANGE aapl -2");
        events.add("QUERY");
    
        System.out.println(profitTracker(events));
    }

    private static List<Long> profitTracker(List<String> events){
        List<Long> profit = new ArrayList<Long>(); //store profit/loss at each QUERY command
        HashMap<String,Integer> hm = new HashMap<>(); //key:stock, val:stock qty
        long currentProfit = 0; //counter to keep track of current profit

        for(int i=0; i<events.size(); i++){
            String[] arr = events.get(i).split(" "); //acccess stock data
            if(arr.length == 1){
                //QUERY
                profit.add(currentProfit);
            }else{
                String command = arr[0];
                String stock = arr[1];
                int qty = Integer.parseInt(arr[2]);
                int curQty = 0;

                if(command.equals("BUY")){
                    //BUY
                    if(!hm.containsKey(stock))hm.put(stock,qty);
                    else{
                        curQty = hm.get(stock) + qty;
                        hm.put(stock,curQty);
                    }
                }
                else if(command.equals("SELL")){
                    //SELL
                    curQty = hm.get(stock) - qty;
                    hm.put(stock,curQty);
                }
                else if(command.equals("CHANGE")){
                    //CHANGE
                    int change = hm.get(stock) * qty;
                    currentProfit = currentProfit + change;
                }
            }
        } 
        return profit;

        //Run Time Complexity: O(n*m) where n = events.size() and m = average of events.get(i).length()
    }
}