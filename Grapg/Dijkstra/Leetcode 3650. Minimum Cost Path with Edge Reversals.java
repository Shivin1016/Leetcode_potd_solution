class Solution {
    public int minCost(int n, int[][] edges) {
        List<List<int[]>> adjList = new ArrayList<>();

        for(int i =0  ; i < n ; i++){
            adjList.add(new ArrayList<>());
        }

        for(int[] edge :edges){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            adjList.get(u).add(new int[]{v , w});
            adjList.get(v).add(new int[]{u , 2 * w});
        }

        //  implement dijesktra

        int[] dist = new int[n];
        Arrays.fill(dist , Integer.MAX_VALUE);
        dist[0] = 0;

        var pq = new PriorityQueue<int[]>((a , b) -> a[1] - b[1]); //{v , w}
        pq.add(new int[]{0,0});

        boolean[] visited = new boolean[n];

        while(!pq.isEmpty()){
            int u = pq.poll()[0];

            if(visited[u]) continue;
            visited[u] = true;

            for(int[] e : adjList.get(u)){
                int v= e[0];
                int w = e[1];
                if(dist[u] + w < dist[v]){
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{v , dist[u] + w});
                }
            }
        }

        return dist[n - 1] == Integer.MAX_VALUE ? -1 : dist[n - 1];

    }
}
