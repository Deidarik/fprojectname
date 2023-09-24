package myfirstpackage;
public class MySecondClass{
private int v1;
private int v2;
public int GetV1()
{
	return this.v1;
}
public int GetV2()
{
	return this.v2;
}
public void SetV1(int x)
{
this.v1=x;
}
public void SetV2(int x)
{
this.v2=x;
}
public MySecondClass() {
        this.v1 = 0;
        this.v2 = 0;
    }
public	 MySecondClass(int x, int y) {
        this.v1 = x;
        this.v2 = y;
    }
public	int V1MinusV2(){
		return this.v1-this.v2;
	}
}