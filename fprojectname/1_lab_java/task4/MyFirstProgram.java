import myfirstpackage.*;
class MyFirstClass {
	public static void main(String[] s) {
	MySecondClass x = new MySecondClass();
	System.out.println(x.V1MinusV2());

        for (int y = 1; y <= 8; ++y) {
            for (int z = 1; z <= 8; ++z) {
                x.SetV1(y);
                x.SetV2(z);
                System.out.print(x.V1MinusV2());
                System.out.print(" ");
            }

            System.out.println();
        }
    }
}

