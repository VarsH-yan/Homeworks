package human;


public class Student {
    private int id;
    private String name;
    private String surName;
    private int birthDate;
    private char gender;
    private double mark;

    public Student() {
    }

    public Student(int id, String name, String surName, int birthDate, char gender, double mark) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.mark = mark;
    }

    public String toString() {
        return this.id + ", " + this.name + ", " + this.surName + ", " + this.birthDate + ", " + this.gender + ", " + this.mark;
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

    public int getBirthDate() {
        return birthDate;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(String gender) {

        if(gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("male")) {
            this.gender = gender.charAt(0);
        } else {
            throw new IllegalArgumentException("You should insert f/m/female/male");
        }

    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }
}
