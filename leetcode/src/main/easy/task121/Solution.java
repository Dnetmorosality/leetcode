package main.easy.task121;

public class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int currentPrice : prices) {
            minPrice = Math.min(minPrice, currentPrice);
            if (currentPrice > maxProfit) {
                maxProfit = Math.max(maxProfit, currentPrice - minPrice);
            }
        }
        return maxProfit;
    }
}