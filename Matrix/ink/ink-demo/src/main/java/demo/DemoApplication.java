package demo;


public class DemoApplication {

    public static void main(String[] args) {
        System.out.println("hi " + DemoApplication.class.getName());
        System.out.println(DemoApplication.class.getClassLoader());
    }

}
