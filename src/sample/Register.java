package sample;

public class Register {

    private String name;
    private int number;
    private int value;

    public Register(String name, int number, int value) {
        this.name = name;
        this.number = number;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return this.number;
    }
    public void setNumer(int number) {
        this.number = number;
    }

    public int getValue() {
        return this.value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
