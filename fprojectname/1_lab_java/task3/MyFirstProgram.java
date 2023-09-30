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

class MySecondClass{
private int v1;
private int v2;
int GetV1()
{
	return this.v1;
}
int GetV2()
{
	return this.v2;
}
void SetV1(int x)
{
this.v1=x;
}
void SetV2(int x)
{
this.v2=x;
}
MySecondClass() {
        this.v1 = 0;
        this.v2 = 0;
    }
	 MySecondClass(int x, int y) {
        this.v1 = x;
        this.v2 = y;
    }
	int V1MinusV2(){
		return this.v1-this.v2;
	}
}
