class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();

        int[] ans = new int[n];

        for(int i = 0 ; i < n ; i++){
            int prime = nums.get(i); 
            int prev = -1;
            for(int j = 0 ; j < 32 ; j++){
                if((prime & (1 << j)) > 0) continue;

                prev = j - 1; 
                break;
            }
            if(prev == -1) ans[i] = -1;
            else{

                System.out.println(prev);
                ans[i] = prime ^ (1 << prev);
            }
        }

        return ans;
    }
}
