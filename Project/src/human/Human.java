package human;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Human implements HumanInterface {
    private int passportId;
    private String name;
    private String surName;
    private LocalDate birthDate;

    public Human() {
    }

    public Human(int passportId, String name, String surName, LocalDate birthDate) {
        this.passportId = passportId;
        this.name = name;
        this.surName = surName;
        this.birthDate = birthDate;
    }

    public Human(LocalDate birthDate, int passportId) {
        this.birthDate = birthDate;
        this.passportId = passportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getBirthDateString() {
        return birthDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }

    public int getPassportId() {
        return passportId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setPassportId(int passportId) {
        this.passportId = passportId;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthDate(String birthDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.birthDate = LocalDate.parse(birthDate, formatter);
        } catch (Exception e){
            throw new IllegalArgumentException("You should be insert birth date like yyyy-MM-dd");
        }
    }

    @Override
    public int getAge() {
        int age;
        if (LocalDate.now().getMonthValue() > birthDate.getMonthValue()){
            age = LocalDate.now().getYear() - birthDate.getYear();
        }
        else if (LocalDate.now().getMonthValue() == birthDate.getMonthValue() && LocalDate.now().getDayOfMonth() >= birthDate.getDayOfMonth()){
            age = LocalDate.now().getYear() - birthDate.getYear();
        }
        else {
            age = LocalDate.now().getYear() - birthDate.getYear() - 1;
        }
        return age;
    }

    @Override
    public void printInfo() {
        System.out.println("Name is: " + getName());
        System.out.println("SurName is: " + getSurName());
        System.out.println("Birthdate is: " + getBirthDateString());
        System.out.println("Age is: " + getAge());
        System.out.println("Passport ID is: " + getPassportId());
    }

    public String toString() {
        return getPassportId() + ", " + getName() + ", " + getSurName() + ", " + getBirthDate();
    }
}
