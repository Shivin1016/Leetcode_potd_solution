class Solution {
    private int isMagicGrid(int[][] grid , int startI , int startJ , int n , int m){
        int maxLen = Math.min(n - startI , m - startJ);
        int best = 1;

        for(int len = 2 ; len <= maxLen ; len++){
            // diagonals sum
            int d1 = 0 , d2 = 0;
            for(int i = 0 ; i < len ; i++){
                d1 += grid[startI + i][startJ + i]; // first diagonal
                d2 += grid[startI + i][startJ + len - i - 1];
            }

            if(d1 != d2){
                continue;
            }

            int target = 0;
            boolean isMagic = true;
            for(int i = startJ ; i < startJ + len ; i++){
                target += grid[startI][i]; 
            }

            if(d1 != target) continue;

            for(int i = startI ; i < startI + len && isMagic ; i++){
                int sum = 0;
                for(int j = startJ ; j < startJ + l
                en ; j++){
                    sum += grid[i][j];
                }
                if(sum != target){
                    isMagic = false;
                    break;
                }
            }

            for(int i = startJ ; i < startJ + len && isMagic ; i++){
                int sum = 0;
                for(int j = startI ; j < startI + len ; j++){
                    sum += grid[j][i];
                }
                if(sum != target){
                    isMagic = false;
                    break;
                }
            }

            if(isMagic){
                best = Math.max(best , len);
            }

            
        }

        return best;

    }
    public int largestMagicSquare(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int maxLen = 1;

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                int len = isMagicGrid(grid , i , j , n , m);
                maxLen = Math.max(maxLen , len);
            }
        }

        return maxLen;

    }
}
