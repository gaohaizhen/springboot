package cn.demo.day1;

public class AutoBox {
    public static void main(String[] args) {
        String s1 = new String("abc");
        String s2 = new String("abc");
        System.out.println(s1 == s2);

        String s3 = "abc";
        String s4 = "abc";
        System.out.println(s3 == s4);

        Integer i1 = 137;
        Integer i2 = 137;
        System.out.println(i1 == i2);

        Integer i3 = Integer.valueOf(213);
        Integer i4 = Integer.valueOf(213);
        System.out.println(i3 == i4);

    }

}
