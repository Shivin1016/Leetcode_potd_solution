class Pair{
    long l;
    int idx;

    Pair(long l , int idx){
        this.l = l;
        this.idx = idx;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair p = (Pair) o;
        return l == p.l && idx == p.idx;
    } 

    public int hashCode() {
        return Objects.hash(l, idx);
    } 
}

class Solution {
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;

        long[] temp = new long[n];
        for(int i = 0 ; i < n ; i++){
            temp[i] = nums[i];
        }
        TreeSet<Pair> minSet = new TreeSet<>(
            (a, b) ->{
                if(a.l != b.l) return Long.compare(a.l , b.l);
                return Integer.compare(a.idx , b.idx);
            }
        );

        // prev Idx
        int[] prevIdx = new int[n];
        //nextIdx
        int[] nextIdx = new int[n];

        for(int i = 0 ; i < n ; i++){
            prevIdx[i] = i - 1;
            nextIdx[i] = i + 1;
        }

        int possibleWrongPairs = 0;
        for(int i = 0 ; i < n - 1; i++){
            if(temp[i] > temp[i + 1]){
                possibleWrongPairs++;
            }
            minSet.add(new Pair(temp[i] + temp[i + 1] , i));
        }

        int operations = 0;

        while(possibleWrongPairs > 0){
            Pair curr = minSet.first();
            minSet.remove(curr);

            int first = curr.idx;
            int second = nextIdx[first];

            int first_prev = prevIdx[first];
            int second_next = nextIdx[second];

            if(temp[first] > temp[second]) possibleWrongPairs--;

            if(first_prev >= 0){
                // a , (b + c)
                if((temp[first_prev] > temp[first]) && (temp[first] + temp[second] >= temp[first_prev])){
                    possibleWrongPairs--;
                }else if((temp[first_prev] <= temp[first]) && (temp[first] + temp[second] < temp[first_prev])){
                    possibleWrongPairs++;
                }
            }

            if(second_next < n){
                // (b + c) , d
                if((temp[second_next] >= temp[second]) && (temp[first] + temp[second] > temp[second_next])){
                    possibleWrongPairs++;
                }
                else if((temp[second_next] < temp[second]) && (temp[first] + temp[second] <= temp[second_next])){
                    possibleWrongPairs--;
                }
            }

            if(first_prev >= 0){
                // a , b , c
                minSet.remove(new Pair(temp[first_prev] + temp[first] , first_prev));
                minSet.add(new Pair(temp[first_prev] + temp[first] + temp[second] , first_prev ));
            }

            if(second_next < n){
                //b , c , d
                minSet.remove(new Pair(temp[second] + temp[second_next] , second));
                minSet.add(new Pair(temp[second_next] + temp[first] + temp[second] , first ));
                prevIdx[second_next] = first;
            }

            nextIdx[first] = second_next;
            temp[first] += temp[second];
            operations++; 

        }

        return operations;
    }
}
