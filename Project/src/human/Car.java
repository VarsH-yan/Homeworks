package human;

public class Car {
    private String carNumber;
    private String model;
    private int maxSpeed;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void printCarInfo(){
        System.out.println("CarNumber is: " + this.carNumber);
        System.out.println("<axSpeed is: " + this.maxSpeed);
        System.out.println("Model is: " + this.model);
    }
}
