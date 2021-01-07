package registrationAndLogin;

public class User {

    public String name;
    public String surname;
    public String username;
    public String email;
    public String password;

    public User(String name, String surname, String username, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return name + ", " + surname + ", " + username + ", " + email + ", " + password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
