import java.util.Iterator;
import java.util.Arrays;
import java.util.Comparator;

public class DataStructure implements DT {

    private LinkedList<Point> xAxis;
    private LinkedList<Point> yAxis;

    //////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
    public DataStructure()
    {
        //Initializing two linked lists.
        xAxis = new LinkedList<>(); //Sorted by his x value
        yAxis = new LinkedList<>(); //Sorted by his y value
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

            if (curr == null){ //Case: we got to the last link
                xAxis.sizeBy1();
                xAxis.setLast(newLink);
                prev.setNext(newLink);
                newLink.setPrev(prev);
            }
            else if (curr == prev) { //Case: the first link X value is smaller than point X value
                xAxis.addFirst(newLink);
            }
            else { //Case: somewhere in the middle of the list
                xAxis.sizeBy1();
                prev.setNext(newLink);
                curr.setPrev(newLink);
                newLink.setNext(curr);
                newLink.setPrev(prev);
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
            if (curr == null){ //Case: we got to the last link
                yAxis.sizeBy1();
                yAxis.setLast(newLink);
                prev.setNext(newLink);
                newLink.setPrev(prev);
            }
            else if (curr == prev) { //Case: the first link X value is smaller than point X value
                yAxis.addFirst(newLink);
            }
            else { //Case: somewhere in the middle of the list
                yAxis.sizeBy1();
                prev.setNext(newLink);
                curr.setPrev(newLink);
                newLink.setNext(curr);
                newLink.setPrev(prev);
            }
            return newLink;
        }
    }

    @Override
    public Point[] getPointsInRangeRegAxis(int min, int max, Boolean axis) {
       int count = 0;//To count how much elements are there
       if(axis){
           Link<Point> curr = xAxis.getFirst();
           while (curr != null && curr.getData().getX() < min){ // iterating all over the points that their x value is smaller than min
               curr = curr.getNext();
           }

           Link<Point> start = curr;
           Link<Point> end;

           while (curr != null && curr.getData().getX() >= min && curr.getData().getX() <= max){// iterating all over the
               // points that their x value is in between min and max value
               count = count + 1;
               end = curr;
               curr = curr.getNext();
           }
           Point[] ans = new Point[count];
           for(int i = 0; i < ans.length; i++)//Inserting the points into the array
           {
               ans[i] = start.getData();
               start = start.getNext();
           }
           return ans;
       }
       else {
           Link<Point> curr = yAxis.getFirst();
           while (curr != null && curr.getData().getY() < min){ // iterating all over the points that their y value is smaller than min
               curr = curr.getNext();
           }

           Link<Point> start = curr;
           Link<Point> end;

           while (curr != null && curr.getData().getY() >= min && curr.getData().getY() <= max){// iterating all over the
               // points that their y value is in between min and max value
               count = count + 1;
               end = curr;
               curr = curr.getNext();
           }
           Point[] ans = new Point[count];
           for(int i = 0; i < ans.length; i++) //Inserting the points into the array
           {
               ans[i] = start.getData();
               start = start.getNext();
           }

           return ans;
       }
    }

    @Override
    public Point[] getPointsInRangeOppAxis(int min, int max, Boolean axis) {
        int count = 0;
        if(axis){
            Iterator<Point> it1 = yAxis.iterator();
            while(it1.hasNext()){//iterating over y-axis to get the number of the points that is legal
                Point p = it1.next();
                if (p.getX()>=min & p.getX()<=max)
                    count++;
            }

            Point[] ans = new Point[count];
            int i =0;
            Iterator<Point> it2 = yAxis.iterator();//Inserting the points into array
            while(it2.hasNext()){
                Point p = it2.next();
                if((p.getX()>=min & p.getX()<=max)){
                    ans[i] = p;
                    i++;
                }
            }
            return ans;
        }
        else{
            Iterator<Point> it1 = xAxis.iterator();//iterating over x-axis to get the number of the points that is legal
            while(it1.hasNext()){
                Point p = it1.next();
                if (p.getY()>=min & p.getY()<=max)
                    count++;
            }

            Point[] ans = new Point[count];
            int i =0;
            Iterator<Point> it2 = xAxis.iterator();//Inserting the points into array
            while(it2.hasNext()){
                Point p = it2.next();
                if((p.getY()>=min & p.getY()<=max)){
                    ans[i] = p;
                    i++;
                }
            }
            return ans;
        }
    }

    @Override
    public double getDensity() {
        double ans;
        double n = (double) xAxis.getSize(); //Casting
        int xMin = xAxis.getFirst().getData().getX();
        int xMax = xAxis.getLast().getData().getX();
        int yMin = yAxis.getFirst().getData().getY();
        int yMax = yAxis.getLast().getData().getY();
        int disX = xMax - xMin;
        double dX = (double) disX; //Casting
        int disY = yMax - yMin;
        double dY = (double) disY; //Casting

        ans = n/(disX*disY); //return in double the Density
        return ans;
    }

    //Remove link from  Linkedlist ls
    public void remove(Link<Point> p, LinkedList<Point> ls){
        if(p==ls.getFirst()){ //Remove first element
            ls.setFirst(p.getNext());
            if(p.getNext()!=null){
                p.getNext().setPrev(null);
            }
        }
        else if (p==ls.getLast()) { //Remove last element
            ls.setLast(p.getPrev());
            if(p.getPrev()!=null){
                p.getPrev().setNext(null);
            }
        }
        else if(p!= ls.getLast() & p!=ls.getFirst()) { //Remove element in the middle
            Link<Point> next = p.getNext();
            Link<Point> prev = p.getPrev();
            if (next!=null){
                next.setPrev(prev);
            }
            if(prev !=null){
                prev.setNext(next);
            }
        }
    }

    @Override
    public void narrowRange(int min, int max, Boolean axis) {
        if(axis){
            Link<Point> last = xAxis.getLast();
            Link<Point> first = xAxis.getFirst();

            //removing the links their x Value smaller than min
            while (first != null && first.getData().getX() < min){
                remove(first,xAxis);
                remove(first.getPar(),yAxis);
                xAxis.sizeMinus1();
                yAxis.sizeMinus1();
                first = xAxis.getFirst();
            }

            Link<Point> end = last;

            //removing the links their x Value larger than min
            while (last != null && last.getData().getX() > max){
                remove(last,xAxis);
                remove(last.getPar(),yAxis);
                xAxis.sizeMinus1();
                yAxis.sizeMinus1();
                last = xAxis.getLast();
            }
        }
        else{
            Link<Point> last = xAxis.getLast();
            Link<Point> first = xAxis.getFirst();

            //removing the links their y Value smaller than min
            while (first != null && first.getData().getY() < min){
                remove(first,yAxis);
                remove(first.getPar(),xAxis);
                yAxis.sizeMinus1();
                xAxis.sizeMinus1();
                first = yAxis.getFirst();
            }

            Link<Point> end = last;
            //removing the links their y Value larger than min
            while (last != null && last.getData().getY() > max){
                remove(last,yAxis);
                remove(last.getPar(),xAxis);
                xAxis.sizeMinus1();
                yAxis.sizeMinus1();
                last = yAxis.getLast();
            }
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
            ans = xAxis.get(index); //Linkedlist get by index
            //O(n)
        }
        else
            ans = yAxis.get(index);//O(n)
        return new Container(ans);
    }

    @Override
    public Point[] nearestPairInStrip(Container container, double width, Boolean axis) {
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
        int len = arr.length;
        Point[] ans = new Point[2];

        if(len<2){
            return new Point[0];
        }
        if(len == 2 || len == 3)
            return twoOrthree(arr);


        double minDis = Double.MAX_VALUE;
        if(axis){
            Arrays.sort(arr, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return o1.getY() - o2.getY();
                }
            });

            for(int i=0;i< arr.length-1;i++){
                for (int j=1;j<7;j++){
                    int index =  i+j;
                    if(index<arr.length){
                        if(distance(arr[i],arr[index]) < minDis){
                            minDis = distance(arr[i],arr[index]);
                            ans[0] = arr[i];
                            ans[1] = arr[index];
                        }
                    }
                }
            }

        }
        else {
            Arrays.sort(arr, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return o1.getX() - o2.getX();
                }
            });

            for(int i=0;i< arr.length-1;i++){
                for (int j=1;j<7;j++){
                    int index =  i+j;
                    if(index<arr.length){
                        if(distance(arr[i],arr[index]) < minDis){
                            minDis = distance(arr[i],arr[index]);
                            ans[0] = arr[i];
                            ans[1] = arr[index];
                        }
                    }
                }
            }
        }
        return ans;
    }

    //Returns distance between two points
    public double distance(Point p1, Point p2){
        double ans = Math.sqrt((Math.pow(p1.getX() - p2.getX(),2)) + (Math.pow(p1.getY() - p2.getY(),2) ));
        return ans;
    }

    //Base scenarios of an array with two or three points only!
    public Point[] twoOrthree(Point[] arr){
        int n = arr.length;
        if(n==2) return arr;
        else {
            Point[] ans = new Point[2];
            double a = Math.sqrt(Math.pow((arr[0].getX() - arr[1].getX()),2) +
                    Math.pow((arr[0].getY() - arr[1].getY()),2) ); //distance between the points 0 and 1

            double b = Math.sqrt(Math.pow((arr[0].getX() - arr[2].getX()),2) +
                    Math.pow((arr[0].getY() - arr[2].getY()),2) ); //distance between the points 0 and 2

            double c = Math.sqrt(Math.pow((arr[2].getX() - arr[1].getX()),2) +
                    Math.pow((arr[2].getY() - arr[1].getY()),2) ); //distance between the points 1
            double minVal = Math.min(a,Math.min(b,c));
            if(minVal == a){
                ans[0] = arr[0];
                ans[1] = arr[1];
            }
            else if (minVal == b) {
                ans[0] = arr[0];
                ans[1] = arr[2];
            }
            else{
                ans[0] = arr[1];
                ans[1] = arr[2];
            }
            return ans;
        }
    }




    @Override
    public Point[] nearestPair() {
        //Step 1
        int n = xAxis.size();
        if(n<=1){
            return new Point[2];
        }
        if(n==2 | n==3){
            int min = xAxis.getFirst().getData().getX();
            int max = xAxis.getLast().getData().getX();
            return twoOrthree(getPointsInRangeRegAxis(min,max,true));
        }

        //Step 2
        boolean axis = getLargestAxis();

        //Step 3
        Container mid = getMedian(axis);

        //Step 4
        Point[] ans;
        if(axis){
            int left = mid.getData().getX() - xAxis.getFirst().getData().getX();
            int right = xAxis.getLast().getData().getX() - mid.getData().getX();
            int max = Math.max(left,right);
            ans = nearestPairInStrip(mid,((double)max)*2,axis); //because the function we call divide the width by 2
        }
        else {
            int left = mid.getData().getY() - yAxis.getFirst().getData().getY();
            int right = yAxis.getLast().getData().getY() - mid.getData().getY();
            int max = Math.max(left,right);
            ans = nearestPairInStrip(mid,((double)max)*2,axis); //because the function we call divide the width by 2
        }

        //Step 5
        double minDist = distance(ans[0],ans[1]);

        //Step 6
        Point[] check = nearestPairInStrip(mid,2*minDist,axis);

        //Step 6 : A
        if(check.length != 0) {
            double temp_dis = distance(check[0], check[1]);
            if (temp_dis < minDist) {
                return check;
            }
        }
        //Step 6 : B
        return ans;
    }



}
