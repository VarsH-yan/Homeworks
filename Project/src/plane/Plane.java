package plane;

public class Plane {
    private String model;
    private String country;
    private int year;
    private int hours;
    private int weight;
    private int wingspan;
    private int topSpeed;
    private int seats;
    private double cost;
    private boolean isMilitary;

    public Plane(String model, String country, int year, int hours, int weight, int wingspan, int topSpeed, int seats, double cost, boolean isMilitary) {
        this.model = model;
        this.country = country;
        this.year = year;
        this.hours = hours;
        this.weight = weight;
        this.wingspan = wingspan;
        this.topSpeed = topSpeed;
        this.seats = seats;
        this.cost = cost;
        this.isMilitary = isMilitary;
    }

    public Plane() {
    }

    public String toString(){
        return this.model + ", " + this.country + ", " + this.year + ", " + this.hours + ", " + this.weight + ", " + this.wingspan
                + ", " + this.topSpeed + ", " + this.seats + ", " + this.cost + ", " +this.isMilitary;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year >= 1903 && year <= 2020){
            this.year = year;
        } else {
            throw new IllegalArgumentException("You should be insert Integer number between 1903 to 2020.");
        }
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        if (hours >= 0 && hours <= 10000){
            this.hours = hours;
        } else {
            throw new IllegalArgumentException("You should be insert Integer number between 0 to 10000.");
        }
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if (weight >= 1000 && weight <= 160000){
            this.weight = weight;
        } else {
            throw new IllegalArgumentException("You should be insert Integer number between 1000 to 160000.");
        }
    }

    public int getWingspan() {
        return wingspan;
    }

    public void setWingspan(int wingspan) {
        if (wingspan >= 10 && wingspan <= 45){
            this.wingspan = wingspan;
        } else {
            throw new IllegalArgumentException("You should be insert Integer number between 10 to 45.");
        }
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        if (topSpeed >= 0 && topSpeed <= 1000){
            this.topSpeed = topSpeed;
        } else {
            throw new IllegalArgumentException("You should be insert Integer number between 0 to 1000.");
        }
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        if (seats >= 0){
            this.seats = seats;
        }
        else {
            throw new IllegalArgumentException("You should be insert positive number.");
        }
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {

        if (cost >= 0){
            this.cost = cost;
        } else {
            throw new IllegalArgumentException("You should be insert positive number.");
        }
    }

    public boolean isMilitary() {
        return isMilitary;
    }

    public void setMilitary(boolean military) {
        isMilitary = military;
    }



}
