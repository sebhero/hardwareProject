package testJonte;

/**
 * Created by jonatan on 2016-04-13.
 */
public class testIf {
    public static void main(String[] args) {
        testIf tet = new testIf();
        String res[] = {"A448182B","AAA448182B","48182B","0123456"};
        for (int i = 0; i < res.length; i++) {
            tet.test(res[i]);
        }
    }
    public void test(String inputline){
        System.out.println("Length: " + inputline.length());
        if(inputline.length() > 8 || inputline.length() < 7){
            System.out.println(inputline + " inside");
        }else{
            System.out.println(inputline + " outside");
        }
    }
}
