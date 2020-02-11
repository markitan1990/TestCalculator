package TestTask;

public class Calculator {
    private String condition;

    public void run() {
        System.out.println("Мы должны что то посчитать");
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
