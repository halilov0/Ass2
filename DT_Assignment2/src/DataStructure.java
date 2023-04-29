
public class DataStructure implements DT {

    private LinkedList<Point> xAxis;
    private LinkedList<Point> yAxis;

    //////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
    public DataStructure()
    {
        xAxis = new LinkedList<>();
        yAxis = new LinkedList<>();
    }

    // par is a field of the parallel link in the other list (x->y, y->x)
    @Override
    public void addPoint(Point point) {
        Link<Point> x = addXPoint(point);
        Link<Point> y = addYPoint(point);
        x.setPar(y);
        y.setPar(x);
    }

    public Link<Point> addXPoint(Point point) {
        if(xAxis.isEmpty()) {
            xAxis.addFirst(point);
            return xAxis.getFirst();
        }
        else {
            Link<Point> newLink = new Link<>(point);
            Link<Point> curr = xAxis.getFirst();
            Link<Point> prev = curr;
            while (curr != null && curr.getData().getX()<point.getX()){
                prev = curr;
                curr = curr.getNext();
            }
            if (curr == null){
                prev.setNext(newLink);
            }
            else {
                prev.setNext(newLink);
                newLink.setNext(curr);
            }
            return newLink;
        }
    }

    public Link<Point> addYPoint(Point point) {
        if(yAxis.isEmpty()) {
            yAxis.addFirst(point);
            return yAxis.getFirst();
        }
        else {
            Link<Point> newLink = new Link<>(point);
            Link<Point> curr = yAxis.getFirst();
            Link<Point> prev = curr;
            while (curr != null && curr.getData().getY()<point.getY()){
                prev = curr;
                curr = curr.getNext();
            }
            if (curr == null){
                prev.setNext(newLink);
            }
            else {
                prev.setNext(newLink);
                newLink.setNext(curr);
            }
            return newLink;
        }
    }

    @Override
    public Point[] getPointsInRangeRegAxis(int min, int max, Boolean axis) {
       int count = 0;
       if(axis){
           Link<Point> curr = xAxis.getFirst();
           while (curr != null && curr.getData().getX() < min){
               curr = curr.getNext();
           }
           Link<Point> start = curr;
           Link<Point> end;
           while (curr != null && curr.getData().getX() >= min && curr.getData().getX() <= max){
               count = count + 1;
               end = curr;
               curr = curr.getNext();
           }
           Point[] ans = new Point[count];
           for(int i = 0; i < ans.length; i++)
           {
               ans[i] = start.getData();
               start = start.getNext();
           }
           return ans;
       }
       else {
           Link<Point> curr = yAxis.getFirst();
           while (curr != null && curr.getData().getY() < min){
               curr = curr.getNext();
           }
           Link<Point> start = curr;
           Link<Point> end;
           while (curr != null && curr.getData().getY() >= min && curr.getData().getY() <= max){
               count = count + 1;
               end = curr;
               curr = curr.getNext();
           }
           Point[] ans = new Point[count];
           for(int i = 0; i < ans.length; i++)
           {
               ans[i] = start.getData();
               start = start.getNext();
           }
           return ans;
       }
    }

    @Override
    public Point[] getPointsInRangeOppAxis(int min, int max, Boolean axis) {
       return this.getPointsInRangeRegAxis(min, max, !axis);
    }

    @Override
    public double getDensity() {
        double ans;
        int n = xAxis.getSize();
        int xMin = xAxis.getFirst().getData().getX();
        int xMax = xAxis.getLast().getData().getX();
        int yMin = yAxis.getFirst().getData().getY();
        int yMax = yAxis.getLast().getData().getY();
        int disX = xMax - xMin;
        int disY = yMax - yMin;
        ans = n/(disX*disY);
        return ans;
    }

    @Override
    public void narrowRange(int min, int max, Boolean axis) {
        Point[] arr = getPointsInRangeRegAxis(min, max, axis);
        if(axis){
            Link<Point> last = xAxis.getLink(arr[arr.length-1]);
            Link<Point> first = xAxis.getFirst();
            while (first.getData().getX() != arr[0].getX()){
                xAxis.remove(0);
                yAxis.remove(first.getData());
                first = first.getNext();
            }
            last = last.getNext();
            Link<Point> end = last;
            while (last != null){
                yAxis.remove(last.getData());
                last = last.getNext();
            }
            xAxis.setLast(end);
            end.setNext(null);
        }
        else{
            Link<Point> last = yAxis.getLink(arr[arr.length-1]);
            Link<Point> first = yAxis.getFirst();
            while (first.getData().getY() != arr[0].getY()){
                yAxis.remove(0);
                xAxis.remove(first.getData());
                first = first.getNext();
            }
            last = last.getNext();
            Link<Point> end = last;
            while (last != null){
                xAxis.remove(last.getData());
                last = last.getNext();
            }
            yAxis.setLast(end);
            end.setNext(null);
        }
    }

    @Override
    public Boolean getLargestAxis() {
        int xMin = xAxis.getFirst().getData().getX();
        int xMax = xAxis.getLast().getData().getX();
        int yMin = yAxis.getFirst().getData().getY();
        int yMax = yAxis.getLast().getData().getY();
        return (xMax-xMin) > (yMax-yMin);
    }

    @Override
    public Container getMedian(Boolean axis) {
        Point ans;
        int index = xAxis.getSize();
        if(index%2 == 0)
            index = index/2;
        else
            index = (index-1)/2;
        if(axis){
            ans = xAxis.get(index);
        }
        else
            ans = yAxis.get(index);
        return new Container(ans);
    }

    @Override
    public Point[] nearestPairInStrip(Container container, double width,
                                      Boolean axis) {
        int min;
        int max;
        if(axis){
            min = container.getData().getX() - ((int) (width/2));
            max = container.getData().getX() + ((int) (width/2));
        }
        else {
            min = container.getData().getY() - ((int) (width/2));
            max = container.getData().getY() + ((int) (width/2));
        }
        Point[] arr = getPointsInRangeRegAxis(min, max, axis);
        // מערך עם שני איברים, במקום ה0 יהיה הקרוב ביותר לקונטיינר, איבר 1 יהיה הקרוב פחות
        double big; // המרחק של הקרוב פחות
        double small; // המרחק של הקרוב ביותר
        return null;
    }

    @Override
    public Point[] nearestPair() {
        // TODO Auto-generated method stub
        return null;
    }


    //TODO: add members, methods, etc.

}
