import fileServices.FileService;
import registrationAndLogin.RegistrationAndLogin;

public class Main {

    public static void main(String[] args) {

        //config
        FileService.setPathPlaneData("./src//resources/PlaneData.txt");
        FileService.setPathStudentData("./src//resources/StudentData.txt");
        FileService.setPathHumanData("./src//resources/HumanData.txt");
        FileService.setPathWorkerData("./src//resources/WorkerData.txt");
        FileService.setPathDriverData("./src//resources/DriverData.txt");
        FileService.setPathUserData("./src//resources/UserData.txt");

        RegistrationAndLogin registrationAndLogin = new RegistrationAndLogin();
        registrationAndLogin.registrationAndLoginMenu();

    }
}
