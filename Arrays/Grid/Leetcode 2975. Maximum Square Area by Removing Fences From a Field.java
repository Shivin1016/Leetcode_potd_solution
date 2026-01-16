class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        int[] h = Arrays.copyOf(hFences , hFences.length + 2);
        int[] v = Arrays.copyOf(vFences , vFences.length + 2);

        int n1 = h.length;
        int n2 = v.length;

        h[n1 - 2] = 1;
        h[n1 - 1] = m;
        v[n2 - 2] = 1;
        v[n2 - 1] = n;

        Arrays.sort(h); //(nlogn)
        Arrays.sort(v);

        Set<Integer> hGaps = new HashSet<>();
        for(int i = 0 ; i < n1 ; i++){ //(n^2)
            for(int j = i + 1 ; j < n1 ; j++){
                hGaps.add(h[j] - h[i]);
            }
        }

        long maxLen = 0;
        for(int i = 0 ; i < n2 ; i++){
            for(int j = i + 1 ; j < n2 ; j++){
                int vGaps = v[j] - v[i];
                if(hGaps.contains(vGaps)){
                    maxLen = Math.max(maxLen , (long)vGaps);
                }
            }
        }
        long M = 1_000_000_007; 
        long area = (maxLen * maxLen) % M;
        return maxLen == 0 ? -1 : (int)area;

    }
}
