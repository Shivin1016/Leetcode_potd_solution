class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n1 = bottomLeft.length;
        int n2 = topRight.length;

        long area = 0;

        for(int i = 0 ; i < n1 ; i++){
            for(int j = i + 1 ; j < n1 ; j++){
                // sq1
                int l1 = bottomLeft[i][0];
                int b1 = bottomLeft[i][1];
                int r1 = topRight[i][0];
                int t1 = topRight[i][1];

                //sq2
                int l2 = bottomLeft[j][0];
                int b2 = bottomLeft[j][1];
                int r2 = topRight[j][0];
                int t2 = topRight[j][1];

                int left = Math.max(l1 , l2);
                int right = Math.min(r1 , r2);
                int bottom = Math.max(b1 , b2);
                int top = Math.min(t1 , t2);

                // overlapping
                if(left < right && bottom < top){
                    long maxLen = Math.min(right - left , top - bottom);
                    area = Math.max(area , maxLen * maxLen);
                }


            }
        }

        return area;
    }
}
