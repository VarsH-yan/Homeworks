package human;

import java.time.LocalDate;

public class Driver extends Worker{
    private int driverLicenceId;
    private String carModel;
    private String carNumber;

    public Driver() {
    }

    public Driver(int passportId, String name, String surName, LocalDate birthDate, int workerId, int salary, int startWorkYear, int driverLicenceId, String carModel, String carNumber) {
        super(passportId, name, surName, birthDate, workerId, salary, startWorkYear);
        this.driverLicenceId = driverLicenceId;
        this.carModel = carModel;
        this.carNumber = carNumber;
    }

    public Driver(int passportId, int workerId, LocalDate birthDate, int driverLicenceId) {
        super(birthDate, passportId, workerId);
        this.driverLicenceId = driverLicenceId;
    }

    public Driver(Worker worker, int driverLicenceId) {
        super(worker.getBirthDate(), worker.getPassportId(), worker.getWorkerId());
        setName(worker.getName());
        setSurName(worker.getSurName());
        setSalary(worker.getSalary());
        setStartWorkYear(worker.getStartWorkYear());
        this.driverLicenceId = driverLicenceId;
    }



    public int getDriverLicenceId() {
        return driverLicenceId;
    }


    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Driver licence ID is: " + getDriverLicenceId());
        System.out.println("Car model is: " + carModel);
        System.out.println("Car number is: " + carNumber);
    }

    public void setDriverLicenceId(int driverLicenceId) {
        this.driverLicenceId = driverLicenceId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String toString() {
        return getPassportId() + ", " + getName() + ", " + getSurName() + ", " + getBirthDate() + ", " + getWorkerId() + ", " + getSalary() + ", " + getStartWorkYear() + ", " + driverLicenceId + ", " + carModel + ", " + carNumber;
    }
}
