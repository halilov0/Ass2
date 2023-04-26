
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
        addXPoint(point);
        addYPoint(point);
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
        if (yAxis.isEmpty())
            yAxis.add(point);
        else {
            for (int i = 0; i < yAxis.size(); i = i + 1)
                if (yAxis.get(i).getX() > point.getX())
                    yAxis.add(i, point);
        }
    }

    @Override
    public Point[] getPointsInRangeRegAxis(int min, int max, Boolean axis) {
        DynamicArray<Point> smartArray = new DynamicArray<>();
        boolean stop = false;
        if(axis){
            for(int i=0; i<xAxis.size() && !stop; i=i+1){
                if(xAxis.get(i).getX()>=min && xAxis.get(i).getX()<=max) {
                    smartArray.add(xAxis.get(i));
                }
                else if (xAxis.get(i).getX()>max)
                    stop = true;
            }
        }
        else{
            for(int i=0; i<yAxis.size() && !stop; i=i+1){
                if(yAxis.get(i).getX()>=min && yAxis.get(i).getX()<=max) {
                    smartArray.add(yAxis.get(i));
                }
                else if (yAxis.get(i).getX()>max)
                    stop = true;
            }

        }
        Point[] array = new Point[smartArray.size()];
        for(int i=0; i<array.length; i=i+1){
            array[i] = smartArray.get(i);
        }
        return array;
    }

    @Override
    public Point[] getPointsInRangeOppAxis(int min, int max, Boolean axis) {
        DynamicArray<Point> smartArray = new DynamicArray<>();
        boolean stop = false;
        if(!axis){
            for(int i=0; i<xAxis.size() && !stop; i=i+1){
                if(xAxis.get(i).getX()>=min && xAxis.get(i).getX()<=max) {
                    smartArray.add(xAxis.get(i));
                }
                else if (xAxis.get(i).getX()>max)
                    stop = true;
            }
        }
        else{
            for(int i=0; i<yAxis.size() && !stop; i=i+1){
                if(yAxis.get(i).getX()>=min && yAxis.get(i).getX()<=max) {
                    smartArray.add(yAxis.get(i));
                }
                else if (yAxis.get(i).getX()>max)
                    stop = true;
            }

        }
        Point[] array = new Point[smartArray.size()];
        for(int i=0; i<array.length; i=i+1){
            array[i] = smartArray.get(i);
        }
        return array;
    }

    @Override
    public double getDensity() {
        double ans;
        int n = xAxis.size();
        int xMin = xAxis.get(0).getX();
        int xMax = xAxis.get(n-1).getX();
        int yMin = yAxis.get(0).getY();
        int yMax = yAxis.get(n-1).getY();
        int disX = xMax - xMin;
        int disY = yMax - yMin;
        ans = n/(disX*disY);
        return ans;
    }

    @Override
    public void narrowRange(int min, int max, Boolean axis) {

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
