class Solution {
    public int minPairSum(int[] nums) {
        int n = nums.length;

        int maxSum = 0;

        Arrays.sort(nums);

        for(int i = 0 ; i < n / 2 ; i++){
            maxSum = Math.max(maxSum , nums[i] + nums[n - i - 1]);
        }
        return maxSum;
    }
}
