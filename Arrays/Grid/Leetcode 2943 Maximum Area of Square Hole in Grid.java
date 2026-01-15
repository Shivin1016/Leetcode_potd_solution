class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int n1 = hBars.length;
        int n2 = vBars.length; 

        // sorting both
        Arrays.sort(hBars);
        Arrays.sort(vBars);

        // continues form maximum length
        int length1 = 0; // h
        int length2 = 0; // b

        int i = 1 , j = 1;
        int currL1 = 0 ; // h
        int currL2 = 0; // b
        while(i < n1){
            if(hBars[i - 1] + 1 == hBars[i]){
                currL1++;
                length1 = Math.max(length1 , currL1);
            }else{
                currL1 = 0;
            }
            i++;
        }

        // vertical bars
         while(j < n2){
            if(vBars[j - 1] + 1 == vBars[j]){
                currL2++;
                length2 = Math.max(length2 , currL2);
            }else{
                currL2 = 0;
            }
            j++;
        }

        // take minimum of both continues fromlength
        int length = Math.min(length1 , length2) + 2;
        

        return length * length;

    }
}
