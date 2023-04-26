
public class DataStructure implements DT {

    private DynamicArray<Point> xAxis;
    private DynamicArray<Point> yAxis;

    //////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
    public DataStructure()
    {
        xAxis = new DynamicArray<>();
        yAxis = new DynamicArray<>();
    }

    @Override
    public void addPoint(Point point) {
        planes.add(point);
    }

    public void addXPoint(Point point) {
        if(xAxis.isEmpty())
            xAxis.add(point);
        else {
            for(int i=0; i<xAxis.size(); i=i+1)
                if(xAxis.get(i).getX() > point.getX())
                    xAxis.add(i,point);
        }
    }

    public void addYPoint(Point point) {
        planes.add(point);
    }

    @Override
    public Point[] getPointsInRangeRegAxis(int min, int max, Boolean axis) {

        return null;
    }

    @Override
    public Point[] getPointsInRangeOppAxis(int min, int max, Boolean axis) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getDensity() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void narrowRange(int min, int max, Boolean axis) {
        // TODO Auto-generated method stub

    }

    @Override
    public Boolean getLargestAxis() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Container getMedian(Boolean axis) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Point[] nearestPairInStrip(Container container, double width,
                                      Boolean axis) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Point[] nearestPair() {
        // TODO Auto-generated method stub
        return null;
    }


    //TODO: add members, methods, etc.

}
