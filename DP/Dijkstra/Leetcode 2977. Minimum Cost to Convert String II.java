class Solution {

    static class Pair {
        long cost;
        String node;

        Pair(long cost, String node) {
            this.cost = cost;
            this.node = node;
        }
    }

    long INF = (long) 1e10;

    Map<String, List<Pair>> adjList = new HashMap<>();

    // start -> end -> shortest cost
    Map<String, Map<String, Long>> srcTotarget = new HashMap<>();

    long[] dp;

    String sourceStr, targetStr;
    TreeSet<Integer> validLengths = new TreeSet<>();

    private long dijkstra(String start, String end) {

        if (srcTotarget.containsKey(start) &&
            srcTotarget.get(start).containsKey(end)) {
            return srcTotarget.get(start).get(end);
        }

        PriorityQueue<Pair> pq =
                new PriorityQueue<>(Comparator.comparingLong(p -> p.cost));

        Map<String, Long> result = new HashMap<>();
        result.put(start, 0L); 

        pq.offer(new Pair(0, start));

        while (!pq.isEmpty()) {

            Pair curr = pq.poll();
            long currCost = curr.cost;
            String node = curr.node;

            if (node.equals(end))
                break;

            if (!adjList.containsKey(node))
                continue;

            for (Pair edge : adjList.get(node)) {
                String adjListNode = edge.node;
                long edgeCost = edge.cost;

                long newCost = currCost + edgeCost;
                if (!result.containsKey(adjListNode) || newCost < result.get(adjListNode)) {
                    result.put(adjListNode, newCost);
                    pq.offer(new Pair(newCost, adjListNode));
                }
            }
        }

        long finalCost = result.getOrDefault(end, INF);

        srcTotarget
            .computeIfAbsent(start, k -> new HashMap<>())
            .put(end, finalCost);

        return finalCost;
    }

    private long solve(int idx) {
        if (idx >= sourceStr.length())
            return 0;

        if (dp[idx] != -1)
            return dp[idx];

        long minCost = INF;

       
        if (sourceStr.charAt(idx) == targetStr.charAt(idx)) {
            minCost = solve(idx + 1);
        }

        
        for (int len : validLengths) {

            if (idx + len > sourceStr.length())
                break;

            String srcSub = sourceStr.substring(idx, idx + len);
            String tgtSub = targetStr.substring(idx, idx + len);

            if (!adjList.containsKey(srcSub))
                continue;

            long pathCost = dijkstra(srcSub, tgtSub);
            if (pathCost == INF)
                continue;

            minCost = Math.min(minCost, pathCost + solve(idx + len));
        }

        return dp[idx] = minCost;
    }

    public long minimumCost(
            String source,
            String target,
            String[] original,
            String[] changed,
            int[] cost) {

        sourceStr = source;
        targetStr = target;

        dp = new long[10001];
        Arrays.fill(dp, -1);

        for (int i = 0; i < original.length; i++) {
            adjList.computeIfAbsent(original[i], k -> new ArrayList<>())
               .add(new Pair(cost[i], changed[i]));
            validLengths.add(original[i].length());
        }

        long result = solve(0);
        return result == INF ? -1 : result;
    }
}
