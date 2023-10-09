package functions;
import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable{
    private double[] xValues;
    private double[] yValues;

    ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
        count = xValues.length;
    }

    ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            double tmp;
            tmp = xTo;
            xTo = xFrom;
            xFrom = tmp;
        }
        xValues = new double[count];
        yValues = new double[count];
        this.count = count;

        double step = (xFrom + xTo) / (count - 1);
        double xCordinate = xFrom;

        for (int i = 0; i < count; i++) {
            xValues[i] = xCordinate;
            yValues[i] = source.apply(xCordinate);
            xCordinate += step;
        }
    }
    protected int floorIndexOfX(double x) {
        int index = 0;
        while (index < count && xValues[index] < x) ++index;
        return (index == count || index == 0) ? index : --index;
    }
    protected double extrapolateLeft(double x) {
        return interpolate(x, 0);
    }

    protected double extrapolateRight(double x) {
        return interpolate(x, count - 2);
    }

    protected double interpolate(double x, int floorIndex) {
        if (count == 1)
            return yValues[0];
        else
            return interpolate(x, getX(floorIndex), getX(floorIndex + 1), getY(floorIndex), getY(floorIndex + 1));
    }

    public int getCount() {
        return count;
    }

    public double getX(int index) {
        return xValues[index];
    }

    public double getY(int index) {
        return yValues[index];
    }

    public void setY(int index, double value) {
        yValues[index] = value;
    }

    public int indexOfX(double x) {
        return binarySearch(xValues, x);
    }

    public int indexOfY(double y) {
        return binarySearch(yValues, y);
    }

    private int binarySearch(double[] arr, double target) {
        int left = 0;
        int right = count - 1;
        double epsilon = 0.000_000_001;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (Math.abs(arr[mid] - target) < epsilon)
                return mid;
            else if (arr[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    public double leftBound() {
        return xValues[0];
    }

    public double rightBound() {
        return xValues[count - 1];
    }
    public void insert(double x, double y)
    {
        double[] newXValues = new double[count+1],newYValues = new double[count+1];

        int index = indexOfX(x);
        if(index != -1)
            yValues[index] = y;
        else{
            index = floorIndexOfX(x);
            if(index != count && index !=0) {
                System.arraycopy(xValues, 0, newXValues, 0, index+1);
                newXValues[index + 1] = x;
                System.arraycopy(xValues, index+1, newXValues, index + 2, count - index-1 );

                System.arraycopy(yValues, 0, newYValues, 0, index+1);
                newYValues[index + 1] = y;
                System.arraycopy(yValues, index+1, newYValues, index + 2, count - index-1 );
            }else if(index == count) {
                System.arraycopy(xValues, 0, newXValues, 0, count);
                newXValues[index] = x;

                System.arraycopy(yValues, 0, newYValues, 0, count);
                newYValues[index] = y;
            }
            else
            {
                System.arraycopy(xValues, 0, newXValues, 1, count);
                newXValues[index] = x;

                System.arraycopy(yValues, 0, newYValues, 1, count);
                newYValues[index] = y;
            }
            count++;
            xValues = Arrays.copyOf(newXValues, count);
            yValues = Arrays.copyOf(newYValues, count);

        }
    }
}
