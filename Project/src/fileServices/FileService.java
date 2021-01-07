package fileServices;

import human.Driver;
import human.Human;
import human.Student;
import human.Worker;
import plane.Plane;
import registrationAndLogin.User;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code FileService} class represent data access object.
 *
 * @author Vars Hovhannisyan
 * @since 1.0
 */
public class FileService {
    private static String pathPlaneData;
    private static String pathStudentData;
    private static String pathHumanData;
    private static String pathWorkerData;
    private static String pathDriverData;
    private static String pathUserData;
    private static Student[] allStudents;
    private static Plane[] allPlanes;
    private static Driver[] allDrivers;
    private static Worker[] allWorkers;
    private static Human[] allHumans;

    /**
     * This method read all information in students data file.
     * If there are some problems linked the file then print stack trace of exception
     * and return empty array. Else make array of Student from file data.
     *
     * @return The array of Student.
     * @since 1.0
     */
    public static Student[] readStudents() {
        String[] strings = new String[0];
        try {
            strings = Files.readAllLines(Paths.get(pathStudentData)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Student[] students = new Student[strings.length];

        for (int i = 0; i < strings.length; i++) {
            String[] splittedArr = strings[i].split(", ");
            students[i] = new Student(Integer.parseInt(splittedArr[0]), splittedArr[1], splittedArr[2],
                    Integer.parseInt(splittedArr[3]), splittedArr[4].charAt(0), Double.valueOf(splittedArr[5]));
        }
        return students;
    }

    /**
     * This method check, if students data file is empty then write student data in the file.
     * If students data file is not empty write newline and student data.
     *
     * @param student the Student object to be write
     * @return True value for successfully written, false value otherwise.
     * @since 1.0
     */
    public static boolean writeStudent(Student student) {
        try {
            FileWriter myWriter = new FileWriter(pathStudentData, true);
            if (allStudents.length == 0) {
                myWriter.write(student.toString());
            } else {
                myWriter.write("\n" + student.toString());
            }
            myWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return false;
    }

    /**
     * This method clear students data file and rewrite taken students data.
     *
     * @param students the array of Student to be rewrite
     * @return True value for successfully written, false value otherwise.
     * @since 1.0
     */
    public static boolean rewriteStudents(Student[] students) {
        try {
            FileWriter myWriter = new FileWriter(pathStudentData, false);
            myWriter.write(toStringStudents(students));
            myWriter.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * This method make string by taken array.
     *
     * @param students the array of Student.
     * @return String which contain students data.
     * @since 1.0
     */
    public static String toStringStudents(Student[] students) {
        String result = "";
        for (Student student : students) {
            result += student.toString() + "\n";
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }

    /**
     * This method clear humans data file and rewrite taken humans data.
     *
     * @param humans the array of Human to be rewrite.
     * @return True value for successfully written, false value otherwise.
     * @since 1.0
     */
    public static boolean rewriteHumans(Human[] humans) {
        try {
            FileWriter myWriter = new FileWriter(pathHumanData, false);
            myWriter.write(toStringHumans(humans));
            myWriter.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method update humans data in application.
     * @since 1.0
     */
    public static void updateHumanData() {
        allHumans = readHumans();
    }

    /**
     * This method make string by taken array.
     *
     * @param humans the array of Human.
     * @return String which contain humans data.
     * @since 1.0
     */
    public static String toStringHumans(Human[] humans) {
        String result = "";
        for (Human human : humans) {
            result += human.toString() + "\n";
        }
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    /**
     * This method clear workers data file and rewrite taken workers data.
     *
     * @param workers the array of Worker to be rewrite.
     * @return True value for successfully written, false value otherwise.
     * @since 1.0
     */
    public static boolean rewriteWorkers(Worker[] workers) {
        try {
            FileWriter myWriter = new FileWriter(pathWorkerData, false);
            myWriter.write(toStringHumans(workers));
            myWriter.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method clear drivers data file and rewrite taken drivers data.
     *
     * @param drivers the array of Driver to be rewrite.
     * @return True value for successfully written, false value otherwise.
     * @since 1.0
     */
    public static boolean rewriteDrivers(Driver[] drivers) {
        try {
            FileWriter myWriter = new FileWriter(pathDriverData, false);
            myWriter.write(toStringHumans(drivers));
            myWriter.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method read all information in planes data file.
     * If there are some problems linked the file then print stack trace of exception
     * and return empty array. Else make array of Plane from file data.
     *
     * @return The array of Plane.
     * @since 1.0
     */
    public static Plane[] readPlanes() {
        String[] strings = new String[0];
        try {
            strings = Files.readAllLines(Paths.get(pathPlaneData)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Plane[] planes = new Plane[strings.length];

        for (int i = 0; i < strings.length; i++) {
            String[] splittedArr = strings[i].split(", ");
            planes[i] = new Plane(splittedArr[0], splittedArr[1], Integer.parseInt(splittedArr[2]), Integer.parseInt(splittedArr[3]),
                    Integer.parseInt(splittedArr[4]), Integer.parseInt(splittedArr[5]), Integer.parseInt(splittedArr[6]),
                    Integer.parseInt(splittedArr[7]), Double.parseDouble(splittedArr[8]), Boolean.parseBoolean(splittedArr[9]));
        }
        return planes;
    }

    /**
     * This method check, if planes data file is empty then write plane data in the file.
     * If planes data file is not empty write newline and plane data.
     *
     * @param plane the Plane object to be write.
     * @param isFirstPlane the boolean value is it first plane or not.
     * @return True value for successfully written, false value otherwise.
     * @since 1.0
     */
    public static boolean writePlane(Plane plane, boolean isFirstPlane) {
        try {
            FileWriter myWriter = new FileWriter(pathPlaneData, true);
            if (isFirstPlane) {
                myWriter.write(plane.toString());
            } else {
                myWriter.write("\n" + plane.toString());
            }
            myWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return false;
    }

    /**
     * This method clear planes data file and rewrite taken planes data.
     *
     * @param planes the array of Plane to be rewrite.
     * @return True value for successfully written, false value otherwise.
     * @since 1.0
     */
    public static boolean rewritePlanes(Plane[] planes) {
        try {
            FileWriter myWriter = new FileWriter(pathPlaneData, false);
            myWriter.write(toStringPlanes(planes));
            myWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return false;
    }

    /**
     * This method make string by taken array.
     *
     * @param planes the array of Plane.
     * @return String which contain planes data.
     * @since 1.0
     */
    public static String toStringPlanes(Plane[] planes) {
        String result = "";
        for (Plane plane : planes) {
            result += plane.toString() + "\n";
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }

    /**
     * This method read all information in workers data file.
     * If there are some problems linked the file then print stack trace of exception
     * and return empty array. Else make array of Worker from file data.
     *
     * @return The array of Worker.
     * @since 1.0
     */
    public static Worker[] readWorkers() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] strings = new String[0];
        try {
            strings = Files.readAllLines(Paths.get(pathWorkerData)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Worker[] workers = new Worker[strings.length];

        for (int i = 0; i < strings.length; i++) {
            String[] splittedArr = strings[i].split(", ");
            workers[i] = new Worker(Integer.parseInt(splittedArr[0]), splittedArr[1], splittedArr[2], LocalDate.parse(splittedArr[3], formatter), Integer.parseInt(splittedArr[4]),
                    Integer.parseInt(splittedArr[5]), Integer.parseInt(splittedArr[6]));
        }
        return workers;
    }

    /**
     * This method check, if workers data file is empty then write worker data in the file.
     * If workers data file is not empty write newline and worker data.
     *
     * @param worker the Plane object to be write.
     * @return True value for successfully written, false value otherwise.
     * @since 1.0
     */
    public static boolean writeWorker(Worker worker) {
        try {
            FileWriter myWriter = new FileWriter(pathWorkerData, true);
            if (allWorkers.length == 0) {
                myWriter.write(worker.toString());
            } else {
                myWriter.write("\n" + worker.toString());
            }
            myWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return false;
    }

    /**
     * This method read all information in humans data file.
     * If there are some problems linked the file then print stack trace of exception
     * and return empty array. Else make array of Human from file data.
     *
     * @return The array of Human.
     * @since 1.0
     */
    public static Human[] readHumans() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] strings = new String[0];
        try {
            strings = Files.readAllLines(Paths.get(pathHumanData)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Human[] humans = new Human[strings.length];

        for (int i = 0; i < strings.length; i++) {
            String[] splittedArr = strings[i].split(", ");
            humans[i] = new Human(Integer.parseInt(splittedArr[0]), splittedArr[1], splittedArr[2], LocalDate.parse(splittedArr[3], formatter));
        }
        return humans;
    }

    /**
     * This method check, if humans data file is empty then write human data in the file.
     * If humans data file is not empty write newline and human data.
     *
     * @param human the Human object to be write.
     * @return True value for successfully written, false value otherwise.
     * @since 1.0
     */
    public static boolean writeHuman(Human human) {
        try {
            FileWriter myWriter = new FileWriter(pathHumanData, true);
            if (allHumans.length == 0) {
                myWriter.write(human.toString());
            } else {
                myWriter.write("\n" + human.toString());
            }
            myWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return false;
    }

    /**
     * This method get all humans, workers and drivers as array of Human.
     *
     * @return The array of Human.
     * @since 1.0
     */
    public static Human[] getHumansAndWorkersAndDrivers(){
        Human[] humans = new Human[getAllHumans().length + getAllWorkers().length + getAllDrivers().length];
        int index = 0;
        for (Human human : getAllHumans()) {
            humans[index++] = human;
        }
        for (Human human : getAllWorkers()) {
            humans[index++] = human;
        }
        for (Human human : getAllDrivers()) {
            humans[index++] = human;
        }
        return humans;
    }

    /**
     * This method get all workers and drivers as array of Worker.
     * @return The array of Worker.
     * @since 1.0
     */
    public static Worker[] getWorkersAndDrivers(){
        Worker[] workers = new Worker[getAllWorkers().length + getAllDrivers().length];
        int index = 0;
        for (Worker worker : getAllWorkers()) {
            workers[index++] = worker;
        }
        for (Worker worker : getAllDrivers()) {
            workers[index++] = worker;
        }
        return workers;
    }

    /**
     * This method update students data in application.
     * @since 1.0
     */
    public static void updateStudentData() {
        allStudents = readStudents();
    }

    /**
     * This method update planes data in application.
     * @since 1.0
     */
    public static void updatePlaneData() {
        allPlanes = readPlanes();
    }

    /**
     * This method add taken student.
     *
     * @param student the Student object to be add.
     * @since 1.0
     */
    public static void addStudent(Student student) {
        Student[] newAllStudents = new Student[allStudents.length + 1];
        for (int i = 0; i < allStudents.length; i++) {
            newAllStudents[i] = allStudents[i];
        }
        newAllStudents[newAllStudents.length - 1] = student;
        allStudents = newAllStudents;
    }

    /**
     * This method read all information in drivers data file.
     * If there are some problems linked the file then print stack trace of exception
     * and return empty array. Else make array of Driver from file data.
     *
     * @return The array of Driver.
     * @since 1.0
     */
    public static Driver[] readAllDrivers() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] strings = new String[0];
        try {
            strings = Files.readAllLines(Paths.get(pathDriverData)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Driver[] drivers = new Driver[strings.length];

        for (int i = 0; i < strings.length; i++) {
            String[] splittedArr = strings[i].split(", ");
            drivers[i] = new Driver(Integer.parseInt(splittedArr[0]), splittedArr[1], splittedArr[2], LocalDate.parse(splittedArr[3], formatter), Integer.parseInt(splittedArr[4]),
                    Integer.parseInt(splittedArr[5]), Integer.parseInt(splittedArr[6]), Integer.parseInt(splittedArr[7]),
                    splittedArr[8], splittedArr[9]);
        }
        return drivers;
    }

    /**
     * This method check, if driver data file is empty then write driver data in the file.
     * If drivers data file is not empty write newline and driver data.
     *
     * @param driver the Driver object to be write.
     * @return True value for successfully written, false value otherwise.
     * @since 1.0
     */
    public static boolean writeDriver(Driver driver) {
        try {
            FileWriter myWriter = new FileWriter(pathDriverData, true);
            if (allDrivers.length == 0) {
                myWriter.write(driver.toString());
            } else {
                myWriter.write("\n" + driver.toString());
            }
            myWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return false;
    }

    /**
     * This method update drivers data in application.
     * @since 1.0
     */
    public static void updateDriverData() {
        allDrivers = readAllDrivers();
    }


    /**
     * This method add taken driver.
     *
     * @param driver the Driver object to be add.
     * @since 1.0
     */
    public static void addDriver(Driver driver) {
        Driver[] newAllDrivers = new Driver[allDrivers.length + 1];
        for (int i = 0; i < allDrivers.length; i++) {
            newAllDrivers[i] = allDrivers[i];
        }
        newAllDrivers[newAllDrivers.length - 1] = driver;
        allDrivers = newAllDrivers;
    }

    /**
     * This method update workers data in application.
     * @since 1.0
     */
    public static void updateWorkerData() {
        allWorkers = readWorkers();
    }

    /**
     * This method add taken human.
     *
     * @param human the Human object to be add.
     * @since 1.0
     */
    public static void addHuman(Human human) {
        Human[] newAllHumans = new Human[allHumans.length + 1];
        for (int i = 0; i < allHumans.length; i++) {
            newAllHumans[i] = allHumans[i];
        }
        newAllHumans[newAllHumans.length - 1] = human;
        allHumans = newAllHumans;
    }

    /**
     * This method add taken plane.
     *
     * @param plane the Plane object to be add.
     * @since 1.0
     */
    public static void addPlane(Plane plane) {
        Plane[] newAllPlanes = new Plane[allPlanes.length + 1];
        for (int i = 0; i < allPlanes.length; i++) {
            newAllPlanes[i] = allPlanes[i];
        }
        newAllPlanes[newAllPlanes.length - 1] = plane;
        allPlanes = newAllPlanes;
    }

    /**
     * This method add taken worker.
     *
     * @param worker the Worker object to be add.
     * @since 1.0
     */
    public static void addWorker(Worker worker) {
        Worker[] newAllWorkers = new Worker[allWorkers.length + 1];
        for (int i = 0; i < allWorkers.length; i++) {
            newAllWorkers[i] = allWorkers[i];
        }
        newAllWorkers[newAllWorkers.length - 1] = worker;
        allWorkers = newAllWorkers;
    }

    /**
     * This method read all information in users data file.
     * If there are some problems linked the file then print stack trace of exception
     * and return empty List. Else make List of Users from file data.
     *
     * @return The List of Users.
     * @since 1.1
     */
    public static List<User> readUsers() {
        String[] strings = null;
        try {
            strings = Files.readAllLines(Paths.get(pathUserData)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<User> users = new ArrayList<>();

        for (int i = 0; i < strings.length; i++) {
            String[] splittedArr = strings[i].split(", ");
            users.add(new User(splittedArr[0], splittedArr[1], splittedArr[2], splittedArr[3], splittedArr[4]));
        }
        return users;
    }

    /**
     * This method check, if it is first user then write user data in the file.
     * If it is not first user, write newline and user data.
     *
     * @param user the User object to be write.
     * @param isFirst the boolean value is it first user or not.
     * @return True value for successfully written, false value otherwise.
     * @since 1.1
     */
    public static boolean writeUser(User user, boolean isFirst) {
        try {
            FileWriter myWriter = new FileWriter(pathUserData, true);
            if(isFirst){
                myWriter.write(user.toString());
            } else {
                myWriter.write("\n" + user.toString());
            }
            myWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return false;
    }

    //getters and setters

    public static Student[] getAllStudents() {
        return allStudents;
    }

    public static void setAllStudents(Student[] allStudents) {
        FileService.allStudents = allStudents;
    }

    public static Plane[] getAllPlanes() {
        return allPlanes;
    }

    public static void setAllPlanes(Plane[] allPlanes) {
        FileService.allPlanes = allPlanes;
    }

    public static Driver[] getAllDrivers() {
        return allDrivers;
    }

    public static void setAllDrivers(Driver[] allDrivers) {
        FileService.allDrivers = allDrivers;
    }

    public static Worker[] getAllWorkers() {
        return allWorkers;
    }

    public static void setAllWorkers(Worker[] allWorkers) {
        FileService.allWorkers = allWorkers;
    }

    public static Human[] getAllHumans() {
        return allHumans;
    }

    public static void setAllHumans(Human[] allHumans) {
        FileService.allHumans = allHumans;
    }

    public static void setPathWorkerData(String pathWorkerData) {
        FileService.pathWorkerData = pathWorkerData;
    }

    public static void setPathDriverData(String pathDriverData) {
        FileService.pathDriverData = pathDriverData;
    }

    public static void setPathPlaneData(String pathPlaneData) {
        FileService.pathPlaneData = pathPlaneData;
    }

    public static void setPathStudentData(String pathStudentData) {
        FileService.pathStudentData = pathStudentData;
    }

    public static void setPathHumanData(String pathHumanData) {
        FileService.pathHumanData = pathHumanData;
    }

    public static void setPathUserData(String pathUserData) {
        FileService.pathUserData = pathUserData;
    }
}
