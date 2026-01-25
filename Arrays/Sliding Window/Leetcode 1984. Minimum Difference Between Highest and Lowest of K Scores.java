class Solution {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;

        Arrays.sort(nums);

        int  minScore = Integer.MAX_VALUE;

        for(int i = 0 ; i < n - k + 1 ; i++){
            minScore = Math.min(minScore , nums[i + k - 1] - nums[i]);
        }

        return minScore;

    }
}
