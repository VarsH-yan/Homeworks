package human;

import java.time.LocalDate;

public class Worker extends Human {
    private int workerId;
    private int salary;
    private int startWorkYear;

    public Worker() {
    }

    public Worker(int passportId, String name, String surName, LocalDate birthDate, int workerId, int salary, int startWorkYear) {
        super(passportId, name, surName, birthDate);
        this.workerId = workerId;
        this.salary = salary;
        this.startWorkYear = startWorkYear;
    }

    public Worker(LocalDate birthDate, int passportId, int workerId) {
        super(birthDate, passportId);
        this.workerId = workerId;
    }

    public Worker(Human human, int startWorkYear) {
        super(human.getBirthDate(), human.getPassportId());
        setName(human.getName());
        setSurName(human.getSurName());
        this.startWorkYear = startWorkYear;
    }

    public int getWorkerId() {
        return workerId;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getExperience() {
        return LocalDate.now().getYear() - startWorkYear;
    }

    public void setStartWorkYear(int startWorkYear) {
        this.startWorkYear = startWorkYear;
    }

    public int getStartWorkYear() {
        return startWorkYear;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String toString() {
        return getPassportId() + ", " + getName() + ", " + getSurName() + ", " + getBirthDate() + ", " + getWorkerId() + ", " + getSalary() + ", " + getStartWorkYear();
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Worker ID is: " + getWorkerId());
        System.out.println("Salary is: " + getSalary());
        System.out.println("Experience is: " + getExperience());
    }
}
