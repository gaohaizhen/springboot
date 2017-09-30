package cn.demo.day2;

import java.lang.reflect.Method;

@MyDemoAnnotation(annotationAttr = @MetaAnnotation("flx"), color = "red", value = "abc",
        arrayAttr = 1)
public class AnnotationTest {

    @SuppressWarnings("deprecation")
    @MyDemoAnnotation("xyz")
    public static void main(String[] args) throws Exception {
        System.runFinalizersOnExit(true);
        if (AnnotationTest.class.isAnnotationPresent(MyDemoAnnotation.class)) {
            MyDemoAnnotation annotation =
                    (MyDemoAnnotation) AnnotationTest.class.getAnnotation(MyDemoAnnotation.class);
            System.out.println(annotation.color());
            System.out.println(annotation.value());
            System.out.println(annotation.arrayAttr().length);
            System.out.println(annotation.lamp().nextLamp().name());
            System.out.println(annotation.annotationAttr().value());
        }

        Method mainMethod = AnnotationTest.class.getMethod("main", String[].class);
        MyDemoAnnotation annotation2 =
                (MyDemoAnnotation) mainMethod.getAnnotation(MyDemoAnnotation.class);
        System.out.println(annotation2.value());

        System.out.println(AnnotationTest.class.getName());
        System.out.println(AnnotationTest.class.getSimpleName());
    }

    @Deprecated
    public static void sayHello() {
        System.out.println("hi,注解");
    }
}
