package functions;

class Node
{
    public Node next,prev;
    public double y,x;
    Node(double x,double y){this.x = x; this.y = y;}
    Node(){this.x = 0;this.y = 0;}
}
public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Removable{
    protected int count;
    private Node head = null;

    LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for(int i = 0; i < xValues.length; ++i)
        {
            addNode(xValues[i],yValues[i]);
        }

    }
    LinkedListTabulatedFunction(MathFunction source,double xFrom,double xTo, int count)
    {
        double tmp;
        if(xFrom > xTo)
        {
            tmp = xTo;
            xTo = xFrom;
            xFrom = tmp;
        }

        this.count = count;

        double step = (xFrom + xTo) / (count - 1);
        double xCordinate = xFrom;

        for (int i = 0; i < count; i++) {
            addNode(xCordinate,source.apply(xCordinate));
            xCordinate += step;
        }

    }
    private void addNode(double x,double y){
        Node last;
        if (head == null) {
            head = new Node(x, y);
            head.next = head;
            head.prev =head;
        }
        else {
            last = head.prev;
            head.prev = new Node(x,y);
            head.prev.next = head;
            head.prev.prev = last;
            last.next =head.prev;
        }
        count +=1;
    }
    private Node getNode(int index) {
       Node tmp = head;
       int counter;
       if (index <= count/2)
       {
           counter = 0;
           while(counter < count)
           {
               if(counter == index)
                   break;
               tmp = tmp.next;
               counter++;
           }
       }else{
           counter = count;
           while(counter > 0)
           {
               if(counter== index)
                   break;
               tmp = tmp.prev;
               counter--;
           }
       }
       return tmp;
    }
    protected int floorIndexOfX(double x){
        int index = 0;
        while (index < count && getNode(index).x < x) ++index;
        return (index == count || index == 0) ? index : --index;}
    protected double extrapolateLeft(double x){
        return interpolate(x, 0);}
    protected double extrapolateRight(double x){
        return interpolate(x, count - 2);}
    protected double interpolate(double x, int floorIndex){
        if (count == 1)
            return head.y;
        else
            return interpolate(x, getX(floorIndex), getX(floorIndex + 1), getY(floorIndex), getY(floorIndex + 1));
    }

    public int getCount() {
        return count;
    }

    public double getX(int index) {
        return getNode(index).x;
    }

    public double getY(int index) {
        return getNode(index).y;
    }

    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    public int indexOfX(double x) {
        int counter = 0;
        Node tmp = head;
        do{
            if(tmp.x == x) return counter;
            counter++;
            tmp= tmp.next;
        }while(tmp != head);
        return -1;
    }

    public int indexOfY(double y) {
        int counter = 0;

        Node tmp = head;
        do
        {
            if(tmp.y == y) return counter;
            counter++;
            tmp = tmp.next;
        }while(tmp != head);
        return -1;
    }

    public double leftBound() {
        return head.x;
    }

    public double rightBound() {
        return head.prev.x;
    }
    public int floorNodeOfX(double x)
    {
        Node n = head;
        int index = 0;
        while (index < count && n.x < x) {
            ++index;
            n = n.next;
        }
        return (index == count || index == 0) ? index : --index;
    }
    @Override
    public double apply(double x) {
        if (x < leftBound())
            return extrapolateLeft(x);
        else if (x > rightBound())
            return extrapolateRight(x);
        else {
            int searchIndexOfX = indexOfX(x);

            if (searchIndexOfX != -1)
                return getY(searchIndexOfX);
            else
                return interpolate(x, floorNodeOfX(x));
        }
    }
    public void remove(int index)
    {
        Node tmp = head;
        Node lastTmp;
        while(index > 0)
        {
            tmp = tmp.next;
            index--;
        }
        tmp.prev.next = tmp.next;
        tmp.next.prev = tmp.prev;
        count--;
    }

}
