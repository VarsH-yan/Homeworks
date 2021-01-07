package menu;

import fileServices.FileService;
import human.sevices.DriverService;
import human.sevices.HumanService;
import human.sevices.StudentService;
import human.sevices.WorkerService;
import plane.Plane;
import plane.PlaneService;

import java.util.Scanner;

public class Menu {

    private boolean isStopProgram = false;
    private boolean isStopPlaneMenu = false;
    private boolean isStopStudentMenu = false;
    private PlaneService planeService = new PlaneService();
    private StudentService studentService = new StudentService();
    private DriverService driverService = new DriverService();
    private boolean isStopDriverMenu = false;
    private boolean isStopWorkerMenu = false;
    private WorkerService workerService = new WorkerService();
    private boolean isStopHumanMenu = false;
    private HumanService humanService = new HumanService();
    private static Scanner scanner = new Scanner(System.in);

    public void generalMenu() {
        while (!isStopProgram) {
            try {
                System.out.println("\n" + "\033[1;37m" + "PROJECT GENERAL MENU" + "\n");
                System.out.println("Enter command number or command and press enter" + "\n");
                System.out.println("\033[0;33m" + "1. Plane");
                System.out.println("\033[0;34m" + "2. Student");
                System.out.println("\033[0;35m" + "3. Driver");
                System.out.println("\033[0;36m" + "4. Worker");
                System.out.println("\033[0;30m" + "5. Human");
                System.out.println("\033[1;31m" + "*. Log out");
                System.out.println();

                String menuType = inputInConsoleForString();
                menuType = menuType.toLowerCase();

                switch (menuType) {
                    case "1":
                    case "plane":
                        planeMenu();
                        break;
                    case "2":
                    case "student":
                        studentMenu();
                        break;
                    case "3":
                    case "driver":
                        driverMenu();
                        break;
                    case "4":
                    case "worker":
                        workerMenu();
                        break;
                    case "5":
                    case "human":
                        humanMenu();
                        break;
                    case "*":
                    case "exit":
                    case "exit program":
                        isStopProgram = true;
                        break;
                    default:
                        errorMessage();
                }
            } catch (Exception e) {
                printRedMessage("You insert wrong data." + " " + e.getMessage());
//                System.out.print("You insert wrong data.");
//                System.out.println(" " + e.getMessage());
            }
        }
    }

    private void planeMenu() {
        FileService.updatePlaneData();
        isStopPlaneMenu = false;

        while (!isStopPlaneMenu) {
            try {
                System.out.println("\n" + "\033[1;33m" + "PLANE MENU" + "\n");
                System.out.println("Enter command number or command and press enter" + "\n");
                System.out.println("\033[0;33m" + "1.  Print all information of the planes");
                System.out.println("2.  Print plane information by model");
                System.out.println("3.  Print cost and topSpeed if the plane is military otherwise print model and country");
                System.out.println("4.  Print country of the plane with smallest seats count of three plane");
                System.out.println("5.  Print all not military planes");
                System.out.println("6.  Print all military planes which were in air more than 100 hours.");
                System.out.println("7.  Print planes in ascending form order by year");
                System.out.println("8.  Print newest plane between two planes");
                System.out.println("9.  Print model which has bigger wingspan");
                System.out.println("10. Print plane with minimal weight");
                System.out.println("11. Print the plane with minimal cost from all military planes (if there are some of them return first one).");
                System.out.println("12. Add plane");
                System.out.println("13. Remove plane");
                System.out.println("0.  Back to general menu");
                System.out.println("*.  Log out");
                System.out.println();

                String command = inputInConsoleForString();
                command = command.toLowerCase();

                switch (command) {
                    case "1":
                    case "print all information of the planes":
                        planeService.printAllPlaneInfo();
                        break;
                    case "2":
                    case "print plane information by model":
                        System.out.println("Please insert plane model");
                        planeService.printPlaneInfoByModel(inputInConsoleForString());
                        break;
                    case "3":
                    case "print cost and topSpeed if the plane is military otherwise print model and country":
                        System.out.println("Please insert plane model");
                        planeService.printCostAndTopSpeedOrModelAndCountryByModel(inputInConsoleForString());
                        break;
                    case "4":
                    case "print country of the plane with smallest seats count of three plane":
                        printCountryOfThePlaneWithSmallestSeatsCountByModels();
                        break;
                    case "5":
                    case "print all not military planes":
                        planeService.printAllNotMilitaryPlanes(FileService.getAllPlanes());
                        break;
                    case "6":
                    case "print all military planes which were in air more than 100 hours":
                        planeService.printAllMilitaryPlanesWhichWereInAirMoreThan100Hours();
                        break;
                    case "7":
                    case "print planes in ascending form order by year":
                        planeService.printPlanesInAscendingFormOrderByYear();
                        break;
                    case "8":
                    case "print newest plane between two planes":
                        printNewPlaneBetweenTwoPlanes();
                        break;
                    case "9":
                    case "print model which has bigger wingspan":
                        printModelWhichHasBiggerWingspan();
                        break;
                    case "10":
                    case "print plane with minimal weight":
                        planeService.printPlaneWithMinimalWeight();
                        break;
                    case "11":
                    case "print the plane with minimal cost from all military planes":
                        planeService.printPlaneWithMinimalCostFromAllMilitaryPlanes();
                        break;
                    case "12":
                    case "add plane":
                    case "add":
                        addPlane();
                        break;
                    case "13":
                    case "remove plane":
                    case "remove":
                        System.out.println("Please insert model");
                        removePlaneByModel(inputInConsoleForString());
                        break;
                    case "*":
                    case "exit":
                    case "exit program":
                        exitProgram();
                    case "0":
                    case "back":
                    case "back to general menu":
                        isStopPlaneMenu = true;
                        break;
                    default:
                        errorMessage();
                }
            } catch (Exception e) {
                printRedMessage("You insert wrong data." + " " + e.getMessage());
//                System.out.print("You insert wrong data.");
//                System.out.println(" " + e.getMessage());
            }
        }


    }

    public static String inputInConsoleForString() {
        String result = "";
        while (result.length() == 0) {
            try {
                result = scanner.nextLine();
            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        }
        return result;
    }

    public static int inputInConsoleForInt() {
        int result = 0;
        try {
            result = scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            throw new IllegalArgumentException("You should be insert Integer number");
        }
        return result;
    }

    public static int inputInConsoleForOneDigit(){
        boolean isDigit = true;
        try {
            int result = scanner.nextInt();
            if(result >= 0 && result < 10){
                return result;
            } else {
                isDigit = false;
                throw new IllegalArgumentException();
            }
        } catch (Exception e){
            if(isDigit){
                scanner.nextLine();
            }
            throw new IllegalArgumentException("You should be insert one digit");
        }
    }

    public static double inputInConsoleForDouble() {
        double result = 0;
        try {
            result = scanner.nextDouble();
        } catch (Exception e) {
            scanner.nextLine();
            throw new IllegalArgumentException("You should be insert Double number");
        }
        return result;
    }

    private void studentMenu() {
        FileService.updateStudentData();
        isStopStudentMenu = false;

        while (!isStopStudentMenu) {
            try {
                System.out.println("\n" + "\033[1;34m" + "STUDENT MENU" + "\n");
                System.out.println("Enter command number or command and press enter" + "\n");
                System.out.println("\033[0;34m" + "1.  Print all information of the students");
                System.out.println("2.  Print student information by id");
                System.out.println("3.  print students full name");
                System.out.println("4.  Print all male students");
                System.out.println("5.  Print all female students who has mark greater then fifty point four");
                System.out.println("6.  Print student information having the minimal mark");
                System.out.println("7.  Print biggest male student information");
                System.out.println("8.  Print students sorted by mark");
                System.out.println("9.  Print female students sorted by year");
                System.out.println("10. Add student");
                System.out.println("11. Remove student");
                System.out.println("0.  Back to general menu");
                System.out.println("*.  Log out");
                System.out.println();

                String command = inputInConsoleForString();
                command = command.toLowerCase();

                switch (command) {
                    case "1":
                    case "print all information of the students":
                        studentService.printStudents(FileService.getAllStudents());
                        break;
                    case "2":
                    case "print student information by id":
                        System.out.println("Please insert student id");
                        studentService.printStudentInfoById(inputInConsoleForInt(), FileService.getAllStudents());
                        break;
                    case "3":
                    case "print students full name":
                        studentService.printStudentsFullName(FileService.getAllStudents());
                        break;
                    case "4":
                    case "print all male students":
                        studentService.printAllMaleStudents(FileService.getAllStudents());
                        break;
                    case "5":
                    case "print all female students who has mark greater then fifty point four":
                        studentService.printAllFemaleStudentsWhoHasMarkGreaterThenFiftyPointFour(FileService.getAllStudents());
                        break;
                    case "6":
                    case "print student information having the minimal mark":
                        studentService.printStudentInformationHavingTheMinimalMark(FileService.getAllStudents());
                        break;
                    case "7":
                    case "print biggest male student information":
                        studentService.printBiggestMaleStudentInformation(FileService.getAllStudents());
                        break;
                    case "8":
                    case "print students sorted by mark":
                        studentService.printStudentsSortedByMark(FileService.getAllStudents());
                        break;
                    case "9":
                    case "print female students sorted by year":
                        studentService.printFemaleStudentsSortedByYear(FileService.getAllStudents());
                        break;
                    case "10":
                    case "add student":
                    case "add":
                        addStudent();
                        break;
                    case "11":
                    case "remove student":
                    case "remove":
                        System.out.println("Please insert student id");
                        removeStudentById(inputInConsoleForInt());
                        break;
                    case "*":
                    case "exit":
                    case "exit program":
                        exitProgram();
                    case "0":
                    case "back":
                    case "back to general menu":
                        isStopStudentMenu = true;
                        break;
                    default:
                        errorMessage();
                }
            } catch (Exception e) {
                printRedMessage("You insert wrong data." + " " + e.getMessage());
//                System.out.print("You insert wrong data.");
//                System.out.println(" " + e.getMessage());
            }
        }
    }

    private void driverMenu() {
        FileService.updateHumanData();
        FileService.updateWorkerData();
        FileService.updateDriverData();
        isStopDriverMenu = false;

        while (!isStopDriverMenu) {
            try {
                System.out.println("\n" + "\033[1;35m" + "DRIVER MENU" + "\n");
                System.out.println("Enter command number or command and press enter" + "\n");
                System.out.println("\033[0;35m" + "1.  Print all information of the drivers");
                System.out.println("2.  Print driver information by passport id");
                System.out.println("3.  Print driver information by driver license id");
                System.out.println("4.  Print longest name driver");
                System.out.println("5.  Print shortest name driver");
                System.out.println("6.  Print longest surname driver");
                System.out.println("7.  Print shortest surname driver");
                System.out.println("8.  Print biggest driver");
                System.out.println("9.  Print smallest driver");
                System.out.println("10. Print max salary driver");
                System.out.println("11. Print min salary driver");
                System.out.println("12. Print latest start driver");
                System.out.println("13. Print soonest start driver");
                System.out.println("14. Print drivers who's worker id contains this number");
                System.out.println("15. Print drivers who's driver license id contains this number");
                System.out.println("16. Add driver");
                System.out.println("17. Remove driver");
                System.out.println("0.  Back to general menu");
                System.out.println("*.  Log out");
                System.out.println();

                String command = inputInConsoleForString();
                command = command.toLowerCase();

                switch (command) {
                    case "1":
                    case "print all information of the drivers":
                        driverService.printAllDriverInfo(FileService.getAllDrivers());
                        break;
                    case "2":
                    case "print driver information by passport id":
                        System.out.println("Please insert driver passport id");
                        driverService.printDriverInfo(driverService.getDriverByPassportId(inputInConsoleForInt(), FileService.getAllDrivers()));
                        break;
                    case "3":
                    case "print driver information by driver license id":
                        System.out.println("Please, insert driver license id");
                        driverService.printDriverInfo(driverService.getDriverByDriverLicenseId(inputInConsoleForInt(), FileService.getAllDrivers()));
                        break;
                    case "4":
                    case "print longest name driver":
                        driverService.printDriverInfo(driverService.getLongestNameDriver(FileService.getAllDrivers()));
                        break;
                    case "5":
                    case "print shortest name driver":
                        driverService.printDriverInfo(driverService.getShortestNameDriver(FileService.getAllDrivers()));
                        break;
                    case "6":
                    case "print longest surname driver":
                        driverService.printDriverInfo(driverService.getLongestSurnameDriver(FileService.getAllDrivers()));
                        break;
                    case "7":
                    case "print shortest surname driver":
                        driverService.printDriverInfo(driverService.getShortestSurnameDriver(FileService.getAllDrivers()));
                        break;
                    case "8":
                    case "print biggest driver":
                        driverService.printDriverInfo(driverService.getBiggestDriver(FileService.getAllDrivers()));
                        break;
                    case "9":
                    case "print smallest driver":
                        driverService.printDriverInfo(driverService.getSmallestDriver(FileService.getAllDrivers()));
                        break;
                    case "10":
                    case "print max salary driver":
                        driverService.printDriverInfo(driverService.getMaxSalaryDriver(FileService.getAllDrivers()));
                        break;
                    case "11":
                    case "print min salary driver":
                        driverService.printDriverInfo(driverService.getMinSalaryDriver(FileService.getAllDrivers()));
                        break;
                    case "12":
                    case "Print latest start driver":
                        driverService.printDriverInfo(driverService.getLatestStartDriver(FileService.getAllDrivers()));
                        break;
                    case "13":
                    case "print soonest start driver":
                        driverService.printDriverInfo(driverService.getSoonestStartDriver(FileService.getAllDrivers()));
                        break;
                    case "14":
                    case "print drivers who's worker id contains this number":
                        System.out.println("please insert one digit");
                        driverService.printAllDriverInfo(driverService.getDriverWhosWorkerIdContainsThisNumber(inputInConsoleForOneDigit(), FileService.getAllDrivers()));
                        break;
                    case "15":
                    case "print drivers who's driver license id contains this number":
                        System.out.println("please insert one digit");
                        driverService.printAllDriverInfo(driverService.getDriverWhosDriverLicenceIdContainsThisNumber(inputInConsoleForOneDigit(), FileService.getAllDrivers()));
                        break;
                    case "16":
                    case "add driver":
                    case "add":
                        addDriver();
                        break;
                    case "17":
                    case "remove driver":
                    case "remove":
                        System.out.println("Please insert passport id");
                        removeDriver(inputInConsoleForInt());
                        break;
                    case "*":
                    case "exit":
                    case "exit program":
                        exitProgram();
                    case "0":
                    case "back":
                    case "back to general menu":
                        isStopDriverMenu = true;
                        break;
                    default:
                        errorMessage();
                }
            } catch (Exception e) {
                printRedMessage("You insert wrong data." + " " + e.getMessage());
//                System.out.print("You insert wrong data.");
//                System.out.println(" " + e.getMessage());
            }
        }


    }

    private void workerMenu() {
        FileService.updateHumanData();
        FileService.updateWorkerData();
        FileService.updateDriverData();
        isStopWorkerMenu = false;

        while (!isStopWorkerMenu) {
            try {
                System.out.println("\n" + "\033[1;36m" + "WORKER MENU" + "\n");
                System.out.println("Enter command number or command and press enter" + "\n");
                System.out.println("\033[0;36m" + "1.  Print all information of the workers");
                System.out.println("2.  Print worker information by passport id");
                System.out.println("3.  Print worker information by worker id");
                System.out.println("4.  Print worker with longest name");
                System.out.println("5.  Print worker with shortest name");
                System.out.println("6.  Print worker with shortest surname");
                System.out.println("7.  Print worker with longest surname");
                System.out.println("8.  Print biggest worker");
                System.out.println("9.  Print smallest worker");
                System.out.println("10. Print max salary worker");
                System.out.println("11. Print min salary worker");
                System.out.println("12. Print workers whos worker id contains this number");
                System.out.println("13. Print soonest start worker");
                System.out.println("14. Print latest start worker");
                System.out.println("15. Add Worker");
                System.out.println("16. Remove Worker");
                System.out.println("0.  Back to general menu");
                System.out.println("*.  Log out");
                System.out.println();

                String command = inputInConsoleForString();
                command = command.toLowerCase();

                switch (command) {
                    case "1":
                    case "print all information of the workers":
                        workerService.printAllWorkers();
                        break;
                    case "2":
                    case "print worker information by passport id":
                        System.out.println("Please insert worker passport id");
                        workerService.printWorkerInfo(workerService.getWorkerByPassportId(inputInConsoleForInt(), FileService.getWorkersAndDrivers()));
                        break;
                    case "3":
                    case "print worker information by worker id":
                        System.out.println("Please insert worker id");
                        workerService.printWorkerInfo(workerService.getWorkerByWorkerId(inputInConsoleForInt(), FileService.getWorkersAndDrivers()));
                        break;
                    case "4":
                    case "print worker with longest name":
                        workerService.printWorkerInfo(workerService.getWorkerWithLongestName(FileService.getWorkersAndDrivers()));
                        break;
                    case "5":
                    case "print worker with shortest name":
                        workerService.printWorkerInfo(workerService.getWorkerWithShortestName(FileService.getWorkersAndDrivers()));
                        break;
                    case "6":
                    case "print worker with shortest surname":
                        workerService.printWorkerInfo(workerService.getWorkerWithShortestSurname(FileService.getWorkersAndDrivers()));
                        break;
                    case "7":
                    case "print worker with longest surname":
                        workerService.printWorkerInfo(workerService.getWorkerWithLongestSurname(FileService.getWorkersAndDrivers()));
                        break;
                    case "8":
                    case "print biggest worker":
                        workerService.printWorkerInfo(workerService.getBiggestWorker(FileService.getWorkersAndDrivers()));
                        break;
                    case "9":
                    case "print smallest worker":
                        workerService.printWorkerInfo(workerService.getSmallestWorker(FileService.getWorkersAndDrivers()));
                        break;
                    case "10":
                    case "print max salary worker":
                        workerService.printWorkerInfo(workerService.getMaxSalaryWorker(FileService.getWorkersAndDrivers()));
                        break;
                    case "11":
                    case "print min salary worker":
                        workerService.printWorkerInfo(workerService.getMinSalaryWorker(FileService.getWorkersAndDrivers()));
                        break;
                    case "12":
                    case "print workers who's worker id contains this number":
                        System.out.println("please insert one digit");
                        workerService.printWorkers(workerService.getWorkerWhosWorkerIdContainsThisNumber(inputInConsoleForOneDigit(), FileService.getWorkersAndDrivers()));
                        break;
                    case "13":
                    case "print soonest start worker":
                        workerService.printWorkerInfo(workerService.getSoonestStartWorker(FileService.getWorkersAndDrivers()));
                        break;
                    case "14":
                    case "print latest start worker":
                        workerService.printWorkerInfo(workerService.getLatestStartWorker(FileService.getWorkersAndDrivers()));
                        break;
                    case "15":
                    case "add worker":
                    case "add":
                        addWorker();
                        break;
                    case "16":
                    case "remove worker":
                    case "remove":
                        System.out.println("Please insert passport id");
                        removeWorker(inputInConsoleForInt());
                        break;
                    case "*":
                    case "exit":
                    case "exit program":
                        exitProgram();
                    case "0":
                    case "back":
                    case "back to general menu":
                        isStopWorkerMenu = true;
                        break;
                    default:
                        errorMessage();
                }
            } catch (Exception e) {
                printRedMessage("You insert wrong data." + " " + e.getMessage());
//                System.out.print("You insert wrong data.");
//                System.out.println(" " + e.getMessage());
            }
        }

    }

    private void humanMenu() {
        FileService.updateHumanData();
        FileService.updateWorkerData();
        FileService.updateDriverData();
        isStopHumanMenu = false;

        while (!isStopHumanMenu) {
            try {
                System.out.println("\n" + "\033[1;30m" + "HUMAN MENU" + "\n");
                System.out.println("Enter command number or command and press enter" + "\n");
                System.out.println("\033[0;30m" + "1.  Print all information of the humans");
                System.out.println("2.  Print human by passport id");
                System.out.println("3.  Print human with shortest name");
                System.out.println("4.  Print human with shortest surname");
                System.out.println("5.  Print human with longest surname");
                System.out.println("6.  Print biggest human");
                System.out.println("7.  Print smallest human");
                System.out.println("8.  Add Human");
                System.out.println("9.  Remove Human");
                System.out.println("0.  Back to general menu");
                System.out.println("*.  Log out");
                System.out.println();

                String command = inputInConsoleForString();
                command = command.toLowerCase();

                switch (command) {
                    case "1":
                    case "print all information of the humans":
                        humanService.printAllHumans(FileService.getHumansAndWorkersAndDrivers());
                        break;
                    case "2":
                    case "print human by passport id":
                        System.out.println("Please insert passport id");
                        humanService.printHuman(humanService.getHumanByPassportId(inputInConsoleForInt(), FileService.getHumansAndWorkersAndDrivers()));
                        break;
                    case "3":
                    case "print human with shortest name":
                        humanService.printHuman(humanService.getHumanWithShortestName(FileService.getHumansAndWorkersAndDrivers()));
                        break;
                    case "4":
                    case "print human with shortest surname":
                        humanService.printHuman(humanService.getHumanWithShortestSurname(FileService.getHumansAndWorkersAndDrivers()));
                        break;
                    case "5":
                    case "print human with longest surname":
                        humanService.printHuman(humanService.getHumanWithLongestSurname(FileService.getHumansAndWorkersAndDrivers()));
                        break;
                    case "6":
                    case "print biggest human":
                        humanService.printHuman(humanService.getBiggestHuman(FileService.getHumansAndWorkersAndDrivers()));
                        break;
                    case "7":
                    case "print smallest human":
                        humanService.printHuman(humanService.getSmallestHuman(FileService.getHumansAndWorkersAndDrivers()));
                        break;
                    case "8":
                    case "add human":
                    case "add":
                        addHuman();
                        break;
                    case "9":
                    case "remove human":
                    case "remove":
                        System.out.println("Please, enter humane passport ID");
                        removeHuman(inputInConsoleForInt());
                        break;
                    case "*":
                    case "exit":
                    case "exit program":
                        exitProgram();
                    case "0":
                    case "back":
                    case "back to general menu":
                        isStopHumanMenu = true;
                        break;
                    default:
                        errorMessage();
                }
            } catch (Exception e) {
                printRedMessage("You insert wrong data." + " " + e.getMessage());
//                System.out.print("You insert wrong data.");
//                System.out.println(" " + e.getMessage());
            }
        }

    }

    public void exitProgram() {
        isStopProgram = true;
        isStopPlaneMenu = true;
        isStopStudentMenu = true;
        isStopDriverMenu = true;
        isStopWorkerMenu = true;
        isStopHumanMenu = true;
    }

    public void errorMessage() {
        System.out.println("\033[0;91m" + "This command is not recognized. Please insert valid command");
    }

    public void printRedMessage(String message){
        System.out.println("\033[0;91m" + message);
    }

    public void printCountryOfThePlaneWithSmallestSeatsCountByModels() {
        System.out.println("Please insert first plane model");
        String firstModel = inputInConsoleForString();
        Plane firstPlane = planeService.getPlaneByModel(firstModel);
        if (firstPlane == null) {
            System.out.println("There is no plane with that model");
            return;
        }

        System.out.println("Please insert second plane model");
        String secondModel = inputInConsoleForString();
        Plane secondPlane = planeService.getPlaneByModel(secondModel);
        if (secondPlane == null) {
            System.out.println("There is no plane with that model");
            return;
        }

        System.out.println("Please insert third plane model");
        String thirdModel = inputInConsoleForString();
        Plane thirdPlane = planeService.getPlaneByModel(thirdModel);
        if (thirdPlane == null) {
            System.out.println("There is no plane with that model");
            return;
        }
        planeService.printCountryOfThePlaneWithSmallestSeatsCount(firstPlane, secondPlane, thirdPlane);
    }

    public void addPlane() {
        if (planeService.addPlane()) {
            System.out.println("Your plane successfully added");
        } else {
            System.out.println("Your plane is not add");
        }
    }

    public void removePlaneByModel(String model) {
        if (planeService.removePlaneByModel(model)) {
            System.out.println("Your plane successfully removed");
        } else {
            System.out.println("There is no plane with that model. Remove action is canceled");
        }
    }

    public void printNewPlaneBetweenTwoPlanes() {
        System.out.println("Please insert first plane model");
        String firstModel = inputInConsoleForString();
        Plane firstPlane = planeService.getPlaneByModel(firstModel);
        if (firstPlane == null) {
            System.out.println("There is no plane with that model");
            return;
        }
        System.out.println("Please insert second plane model");
        String secondModel = inputInConsoleForString();
        Plane secondPlane = planeService.getPlaneByModel(secondModel);
        if (secondPlane == null) {
            System.out.println("There is no plane with that model");
            return;
        }
        planeService.printPlaneInfo(planeService.getNewPlaneBetweenTwoPlanes(firstPlane, secondPlane));

    }

    public void printModelWhichHasBiggerWingspan() {
        System.out.println("Please insert first plane model");
        String firstModel = inputInConsoleForString();
        Plane firstPlane = planeService.getPlaneByModel(firstModel);
        if (firstPlane == null) {
            System.out.println("There is no plane with that model");
            return;
        }
        System.out.println("Please insert second plane model");
        String secondModel = inputInConsoleForString();
        Plane secondPlane = planeService.getPlaneByModel(secondModel);
        if (secondPlane == null) {
            System.out.println("There is no plane with that model");
            return;
        }
        System.out.println(planeService.getModelWhichHasBiggerWingspan(firstPlane, secondPlane));
    }

    public void addStudent() {
        if (studentService.addStudent()) {
            System.out.println("Your student successfully added");
        } else {
            System.out.println("Your student is not add");
        }
    }

    public void removeStudentById(int id) {
        if (studentService.removeStudentById(id)) {
            System.out.println("Your student successfully removed");
        } else {
            System.out.println("There is not student with that id. Remove action is canceled");
        }
    }

    public void addDriver() {
        if (driverService.addDriver()) {
            System.out.println("Your driver successfully added");
        } else {
            System.out.println("Your driver is not add");
        }
    }

    public void removeDriver(int passportId) {
        if (driverService.removeDriver(passportId)) {
            System.out.println("Your driver successfully removed");
        } else {
            System.out.println("Your driver is not remove");
        }
    }

    public void addWorker() {
        if (workerService.addWorker()) {
            System.out.println("Your worker successfully added");
        } else {
            System.out.println("Your worker is not add");
        }
    }

    public void removeWorker(int passportId) {
        if (workerService.removeWorker(passportId)) {
            System.out.println("Your worker successfully removed");
        } else {
            System.out.println("Your worker is not remove");
        }
    }

    public void addHuman() {
        if (humanService.addHuman()) {
            System.out.println("Your human successfully added");
        } else {
            System.out.println("Your human is not add");
        }
    }

    public void removeHuman(int passportID) {
        if (humanService.removeHuman(passportID)) {
            System.out.println("Your human successfully removed");
        } else {
            System.out.println("Your human is not remove");
        }
    }


}
