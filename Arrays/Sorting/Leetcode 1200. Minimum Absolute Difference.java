class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int n = arr.length;

        Arrays.sort(arr);

        List<List<Integer>> ans = new ArrayList<>();

        int minAbsDiff = Integer.MAX_VALUE;

        for(int i = 0 ; i < n - 1 ; i++){
            minAbsDiff = Math.min(minAbsDiff , (arr[i + 1] - arr[i]));
        }

        for(int i = 0 ; i < n - 1 ; i++){
            if((arr[i + 1] - arr[i]) == minAbsDiff){
                List<Integer> ls = new ArrayList<>();
                ls.add(arr[i]);
                ls.add(arr[i + 1]);
                ans.add(ls);
            }
        }

        return ans;

    }
}
