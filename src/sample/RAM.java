package sample;

public class RAM {

    private int address;
    private int value;

    public RAM(int address, int value) {
        this.address = address;
        this.value = value;
    }

    public int getAddress() {
        return this.address;
    }
    public void setAddress(int address) {
        this.address = address;
    }

    public int getValue() {
        return this.value;
    }
    public void setValue(int value) {
        this.value = value;
    }

}
