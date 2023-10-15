package functions;


import java.lang.reflect.Array;
import java.util.Objects;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Removable, Cloneable {
    protected int count;
    private Node head = null;

    class Node implements Cloneable {
        public Node next, prev;
        private static Node clonedHead = null;
        public double y = 0, x = 0;

        Node(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return ("(" + x + ";" + y + ")");
        }

        @Override
        public boolean equals(Object o) {

            if (this == o)
                return true;
            return ((o != null) && (o.getClass() == this.getClass()) && (x == ((LinkedListTabulatedFunction.Node) o).x) && (y == ((LinkedListTabulatedFunction.Node) o).y));
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public Node clone() throws CloneNotSupportedException {
            Node newNode = (Node) super.clone();
            newNode.next = null;
            newNode.prev = null;

            return newNode;
        }
    }

    LinkedListTabulatedFunction(double[] xValues, double[] yValues) throws IllegalArgumentException {
        int size = xValues.length;
        if (size < 2)
            throw new IllegalArgumentException("Size of list less than 2");
        for (int i = 0; i < size; ++i) {
            addNode(xValues[i], yValues[i]);
        }

    }

    LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) throws IllegalArgumentException{
        double tmp;
        if (xFrom > xTo) {
            tmp = xTo;
            xTo = xFrom;
            xFrom = tmp;
        }

        if (count < 2)
            throw new IllegalArgumentException("Size of list less than 2");
        this.count = count;

        double step = (xFrom + xTo) / (count - 1);
        double xCordinate = xFrom;

        for (int i = 0; i < count; i++) {
            addNode(xCordinate, source.apply(xCordinate));
            xCordinate += step;
        }

    }

    private void addNode(double x, double y) {
        Node last;
        if (head == null) {
            head = new Node(x, y);
            head.next = head;
            head.prev = head;
        } else {
            last = head.prev;
            head.prev = new Node(x, y);
            head.prev.next = head;
            head.prev.prev = last;
            last.next = head.prev;
        }
        count += 1;
    }

    protected Node getNode(int index) throws IllegalArgumentException{
        Node tmp = head;
        int counter;
        if(count > index && index >= 0) {
            if (index <= count / 2) {
                counter = 0;
                while (counter < count) {
                    if (counter == index)
                        break;
                    tmp = tmp.next;
                    counter++;
                }
            } else {
                counter = count;
                while (counter > 0) {
                    if (counter == index)
                        break;
                    tmp = tmp.prev;
                    counter--;
                }
            }
            return tmp;
        }else throw new IllegalArgumentException("Couldn't get node by index!");
    }

    protected int floorIndexOfX(double x) throws IllegalArgumentException{
        int index = 0;
        if (x < leftBound())
            throw new IllegalArgumentException("Arg less than left bound!");
        while (index < count && getNode(index).x < x) ++index;
        return (index == count || index == 0) ? index : --index;
    }

    protected double extrapolateLeft(double x) {
        return interpolate(x, 0);
    }

    protected double extrapolateRight(double x) {
        return interpolate(x, count - 2);
    }

    protected double interpolate(double x, int floorIndex) {
        return interpolate(x, getX(floorIndex), getX(floorIndex + 1), getY(floorIndex), getY(floorIndex + 1));
    }

    public int getCount() {
        return count;
    }

    public double getX(int index){
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
        do {
            if (tmp.x == x) return counter;
            counter++;
            tmp = tmp.next;
        } while (tmp != head);
        return -1;
    }

    public int indexOfY(double y) {
        int counter = 0;

        Node tmp = head;
        do {
            if (tmp.y == y) return counter;
            counter++;
            tmp = tmp.next;
        } while (tmp != head);
        return -1;
    }

    public double leftBound() {
        return head.x;
    }

    public double rightBound() {
        return head.prev.x;
    }

    public int floorNodeOfX(double x) throws IllegalArgumentException {
        Node n = head;
        int index = 0;
        if (x < leftBound())
            throw new IllegalArgumentException("Arg less than left bound!");
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

    public void remove(int index) throws IllegalArgumentException {
        if(index < 0 || index > count)
            throw new IllegalArgumentException("Element with index = " + index + " doesn't exist");
        Node tmp = head;
        if (index == 0)
            head = tmp.next;
        while (index > 0) {
            tmp = tmp.next;
            index = index - 1;
        }
        tmp.prev.next = tmp.next;
        tmp.next.prev = tmp.prev;
        count--;
    }

    public void insert(double x, double y) {
        if (head == null) {
            addNode(x, y);
        } else {
            double tmpcount = 0;
            Node tmp = head;
            while (x > tmp.x && tmpcount < count) {
                tmpcount++;
                tmp = tmp.next;
            }
            Node tmp1 = new Node(x, y);
            Node tmp2 = tmp1;
            tmp2 = tmp.prev.next;
            tmp.prev.next = tmp1;
            tmp1.next = tmp2;
            tmp1.prev = tmp.prev;
            tmp2.prev = tmp1;
            if (tmpcount == 0)
            {
                head = tmp1;
            }

        }
        count += 1;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        Node tmp = head;
        do {
            line.append(tmp.toString());
            tmp = tmp.next;

        } while (tmp != head);
        return line.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        Node list = head;
        if (o.getClass() == o.getClass() && count == ((LinkedListTabulatedFunction) o).getCount()) {
            Node equalList = ((LinkedListTabulatedFunction) o).getNode(0);
            do {
                if (!list.equals(equalList))
                    return false;
                list = list.next;
                equalList = equalList.next;

            } while (list != head);
            return true;

        }
        return false;
    }

    @Override
    public int hashCode() {
        Node temp = head;
        int result = 17;
        do {
            result = 31 * result + temp.hashCode();
            temp = temp.next;
        } while (temp != head);

        return result;
    }

    @Override
    public LinkedListTabulatedFunction clone() throws CloneNotSupportedException {
        LinkedListTabulatedFunction list = (LinkedListTabulatedFunction) super.clone();
        list.head = head.clone();

        Node clonedNode = list.head;
        Node originalNode = head.next;
        Node tmpClone;

        do{

            tmpClone = originalNode.clone();
            clonedNode.next = tmpClone;
            clonedNode.next.prev = clonedNode;
            clonedNode = tmpClone;
            originalNode = originalNode.next;
        }while(originalNode !=  head);
        list.head.prev = clonedNode;
        clonedNode.next = list.head;

        return list;

    }

}
