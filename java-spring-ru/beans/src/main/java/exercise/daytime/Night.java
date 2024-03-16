package exercise.daytime;

public class Night implements Daytime {
    private String name = "night";

    public String getName() {
        return name;
    }

    // BEGIN
    public void beanCreated() {
        System.out.println("Night bean created");
    }
    // END
}
