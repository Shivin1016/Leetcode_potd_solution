class Solution {
    // make event(squares) class
    static class Event{
        double y , x1 , x2;
        int type; //+1 , -1

        Event(double y , double x1 , double x2 , int type){
            this.y = y;
            this.x1 = x1;
            this.x2 = x2;
            this.type = type;
        }
    }

    // segment tree class = Segment tree to maintain union length on x-axis
    static class SgTree{
        int n ; // no.of x-sengemnt 
        int[] cover; //how many events cove that segments
        double[] length , x; // covered length of this segements , x unique x-coordinates

        SgTree(double[] x){
            this.x = x;  // xs[i] → xs[i+1] --> one segement (1,3,5,6)
            n = x.length - 1; //{(1,3),(3,5),(5,6)}
            cover = new int[4 * n]; //size of segment tree
            length = new double[4 * n];
        }

        // width od the segements ==> add , or remove 
        void update(int node , int l , int r , int x1 , int x2 , int val){
            // x1 ,  x2 ==> are range of segemnt
            //  0, 2 => [x1 = 0 , x2 = 2] , 0 , 3
            if(x2 <= l || r <= x1) return ; // no overlap => (l (x1 , x2) r)

            if(x1 <= l && r <= x2){ 
                cover[node] += val; //full covered
            }else{ 
                //partial overlap
                int mid = (l + r) / 2;
                update(node * 2 , l , mid , x1 , x2 , val); // node * 2=> l to mid
                update(node * 2 + 1 , mid , r , x1 , x2 , val); // node * 2 + 1 => mid to r
            }

            // how to update length
            if(cover[node] > 0){ //cover full sqaure -> event 
                length[node] = x[r] - x[l]; //
            }else{
                 //no full cover if leaf node then 0 else check for childrens
                length[node] = (l + 1 == r) ? 0 : length[node * 2] + length[node * 2 + 1];
            }
        }

        double width() {
            return length[1]; // full union width --> node1 covers whole range
        }
    }

    public double separateSquares(int[][] squares) {
        // build events
        List<Event> events = new ArrayList<>();
        Set<Double> xCordinates = new TreeSet<>();

        for(int[] sq : squares){
            double x = sq[0];
            double y = sq[1];
            double l = sq[2];

            events.add(new Event(y , x , x + l , 1)); // enter
            events.add(new Event(y + l , x , x + l , -1)); // leave

            xCordinates.add(x);
            xCordinates.add(x + l);
        }

        events.sort(Comparator.comparingDouble(e -> e.y));

        // unique x -cordinates
        int n = xCordinates.size();
        double[] xu = new double[n];
        int i = 0;
        for(double x : xCordinates){
            xu[i++] = x;
        }

        Map<Double , Integer> idx = new HashMap<>();
        for(i = 0 ; i < n ; i++){
            idx.put(xu[i] , i);
        }

        double area = 0.0;
        double prevY = events.get(0).y;
        SgTree tree = new SgTree(xu);

        for(Event e : events){
            double currY = e.y;
            area += tree.width() * (currY - prevY);
            tree.update(1 , 0 , tree.n , idx.get(e.x1) , idx.get(e.x2) , e.type);
            prevY = currY;
        }

        double half = area / 2.0;

        // y-cut 
        tree = new SgTree(xu);
        prevY = events.get(0).y;
        double a = 0.0;

        for(Event e : events){
            double currY = e.y;
            double slice = (currY - prevY) * tree.width(); // current area
            if(a + slice >= half){
                double upperY = (half - a) / tree.width() ;
                return prevY + upperY;
            }
            a += slice;
            tree.update(1 , 0 , tree.n , idx.get(e.x1) , idx.get(e.x2) , e.type);
            prevY = currY;
        }

        return prevY;
        
    }
}
