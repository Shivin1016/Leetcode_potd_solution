class Solution {
    private int isEqualToThreshold(int[][] gridSum , int startI , int startJ , int n , int m , int threshold){
        int maxLen = Math.min(n - startI , m - startJ);
        int bestLen = 0;

        for(int len = 1 ; len <= maxLen ; len++){
            int sum = 0;
            for(int i = 0 ; i < len ; i++){
                int[] prefixSum = gridSum[startI + i];
                int s = prefixSum[startJ + len - 1] -(startJ == 0 ? 0 : prefixSum[startJ - 1]) ;
                sum += s;
            }
            if(sum <= threshold){
                bestLen = Math.max(bestLen , len);
            }
        }

        return bestLen;
    }
    public int maxSideLength(int[][] mat, int threshold) {
        int n = mat.length;
        int m = mat[0].length;

        int[][] gridSum = new int[n][m]; // rowwise sum
        for(int i = 0 ; i < n ; i++){
            int sum = 0;
            for(int j = 0 ; j < m ; j++){
                sum += mat[i][j];
                gridSum[i][j] = sum;
            }
        }

        int maxLen = 0;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                maxLen = Math.max(maxLen , isEqualToThreshold(gridSum , i , j , n , m , threshold));
            }
        }

        return maxLen;
    }
}
