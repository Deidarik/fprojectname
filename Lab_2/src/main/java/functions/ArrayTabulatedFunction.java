package functions;
import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private final double[] xValues;
    private final double[] yValues;

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
}
