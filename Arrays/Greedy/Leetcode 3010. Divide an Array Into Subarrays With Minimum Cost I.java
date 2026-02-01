//optimal approach
class Solution {
    public int minimumCost(int[] nums) {
        int n = nums.length;

        int firstCost = nums[0];

        int minCost = Integer.MAX_VALUE; // last tqo subarray ki cost

        for(int i = 1 ; i < n - 1 ; i++){
            for(int j = i + 1 ; j < n ; j++){
                minCost = Math.min(minCost , nums[i] + nums[j]);
            }
        }
        return firstCost + minCost;
    }
}

// Optimal approach -> just find two minimum numbers
class Solution {
    public int minimumCost(int[] nums) {
        int n = nums.length;

        int firstCost = nums[0];

        int firstMin = Integer.MAX_VALUE;  
        int secondMin = Integer.MAX_VALUE;

        for(int i = 1 ; i < n ; i++){
            if(firstMin > nums[i]){
                secondMin = firstMin;
                firstMin = nums[i];
            }else if(secondMin > nums[i]){
                secondMin = nums[i];
            }
        }
        return firstCost + firstMin + secondMin;
    }
}
