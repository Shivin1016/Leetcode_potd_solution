// approach 01 ->
class Solution {
    private void dijkstra(List<int[]>[] adj , int src , int[] dist){
        var pq = new PriorityQueue<int[]>((a , b) -> a[1] - b[1]);
        pq.add(new int[]{src , 0});

        dist[src] = 0;

        while(!pq.isEmpty()){
            int[] e = pq.poll();
            int u = e[0];
            int c = e[1];

            if(c > dist[u]) continue;

            for(int[] ed : adj[u]){
                int v = ed[0];
                int w = ed[1];

                if(w + c < dist[v]){
                    dist[v] = dist[u] + w;
                    pq.add(new int[]{v , w + dist[u]});
                }
            }
        }


    }
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        List<int[]>[] adj = new ArrayList[26];
        int n = original.length;

        for(int i = 0 ; i < 26 ; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < n ; i++){
            char u = original[i];
            char v = changed[i];
            int c = cost[i];

            adj[u - 'a'].add(new int[]{v - 'a' , c}); // u -> {v ,c}
        }

        int[][] dist = new int[26][26];
        for(int i = 0 ; i < 26 ; i++){
            Arrays.fill(dist[i] , Integer.MAX_VALUE);
            dijkstra(adj , i , dist[i]);
        }

        int m = source.length();
        long totalCost = 0;
        for(int i = 0 ; i < m ; i++){
            int s = source.charAt(i) - 'a';
            int t = target.charAt(i) - 'a';

            if(s == t) continue;

            if(dist[s][t] == Integer.MAX_VALUE) return -1;
            totalCost += dist[s][t];
        }

        return totalCost;



    }
}


// optimal approach 
class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int inf = (int)1e9;
        int[][] dist = new int[26][26];
        for(int i = 0 ; i < 26 ; i++){
            Arrays.fill(dist[i] , inf);
            dist[i][i] = 0;
        }
        int n = original.length;
        for(int i = 0 ; i < n ; i++){
            int o = original[i] - 'a';
            int c = changed[i] - 'a';

            dist[o][c] = Math.min(dist[o][c] , cost[i]);
        }

        //O(26 ^3)
        for(int v = 0 ; v < 26 ; v++){ // 
            for(int s = 0 ; s < 26 ; s++){
                for(int t = 0 ; t < 26 ; t++){
                    dist[s][t] = Math.min(dist[s][t] , dist[s][v] + dist[v][t]);
                }
            }
        }

        int m = source.length();
        long totalCost = 0;
        for(int i = 0 ; i < m ; i++){
            int s = source.charAt(i) - 'a';
            int t = target.charAt(i) - 'a';

            if(s == t) continue;

            if(dist[s][t] == inf) return -1;
            totalCost += dist[s][t];
        }

        return totalCost;



    }
}
