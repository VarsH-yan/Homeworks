package human.sevices;

import fileServices.FileService;
import human.Driver;
import human.Worker;
import menu.Menu;

import java.util.Arrays;

public class DriverService extends WorkerService {

    public void printDriverInfo(Driver driver) {
        if (driver != null) {
            printWorkerInfo(driver);
            System.out.println("Driver Licence Id is: " + driver.getDriverLicenceId());
            System.out.println("Car model is: " + driver.getCarModel());
            System.out.println("Car number is: " + driver.getCarNumber());
        } else {
            System.out.println("There is no driver like that");
        }
    }

    public void printAllDriverInfo(Driver[] drivers) {
        if(drivers.length == 0){
            System.out.println("There is no driver");
        } else {
            for (Driver driver : drivers) {
                System.out.println();
                printDriverInfo(driver);
            }
        }
    }


    public Driver getDriverByDriverLicenseId(int driverLicenceId, Driver[] drivers) {
        for (Driver driver : drivers) {
            if (driver.getDriverLicenceId() == driverLicenceId) {
                return driver;
            }
        }
        return null;
    }

    public Driver getDriverByPassportId(int passportId, Driver[] drivers) {
        for (Driver driver : drivers) {
            if (driver.getPassportId() == passportId) {
                return driver;
            }
        }
        return null;
    }

    public Driver getLongestNameDriver(Driver[] drivers) {
        return (Driver) getHumanWithLongestName(drivers);
    }

    public Driver getShortestNameDriver(Driver[] drivers) {
        return (Driver) getHumanWithShortestName(drivers);
    }

    public Driver getLongestSurnameDriver(Driver[] drivers) {
        return (Driver) getHumanWithLongestSurname(drivers);
    }

    public Driver getShortestSurnameDriver(Driver[] drivers) {
        return (Driver) getHumanWithShortestSurname(drivers);
    }

    public Driver getBiggestDriver(Driver[] drivers) {
        return (Driver) getBiggestHuman(drivers);
    }

    public Driver getSmallestDriver(Driver[] drivers) {
        return (Driver) getSmallestHuman(drivers);
    }

    public Driver getMaxSalaryDriver(Driver[] drivers) {
        return (Driver) getMaxSalaryWorker(drivers);
    }

    public Driver getMinSalaryDriver(Driver[] drivers) {
        return (Driver) getMinSalaryWorker(drivers);
    }

    public Driver getLatestStartDriver(Driver[] drivers) {
        return (Driver) getLatestStartWorker(drivers);
    }

    public Driver getSoonestStartDriver(Driver[] drivers) {
        return (Driver) getSoonestStartWorker(drivers);
    }


    public Driver[] getDriverWhosWorkerIdContainsThisNumber(int number, Driver[] drivers) {
        Worker[] workers = getWorkerWhosWorkerIdContainsThisNumber(number, drivers);
        Driver[] resultDrivers = new Driver[workers.length];
        for (int i = 0; i < workers.length; i++) {
            resultDrivers[i] = (Driver) workers[i];
        }
        return resultDrivers;
    }

    public Driver[] getDriverWhosDriverLicenceIdContainsThisNumber(int number, Driver[] drivers) {
        Driver[] resultDrivers = new Driver[drivers.length];
        int index = 0;
        if (number >= 0 && number < 10) {
            for (Driver driver : drivers) {
                if (containsNumberInID(number, driver.getDriverLicenceId())) {
                    resultDrivers[index++] = driver;
                }
            }
        }
        return correctArray(resultDrivers, index);
    }

    public Driver[] correctArray(Driver[] workers, int size) {
        Driver[] result = new Driver[size];
        for (int i = 0; i < size; i++) {
            result[i] = workers[i];
        }
        return result;
    }

    public boolean addDriver() {
        boolean isAdded = false;
        Driver driver = new Driver();
        try {
            System.out.println("Please insert passport id. Passport id should be unique. ");
            driver.setPassportId(Menu.inputInConsoleForInt());
            if (getDriverByPassportId(driver.getPassportId(), FileService.getAllDrivers()) == null) {
                if (getWorkerByPassportId(driver.getPassportId(), FileService.getAllWorkers()) == null) {
                    // no in driver data
                    // no in worker Data
                    if (getHumanByPassportId(driver.getPassportId(), FileService.getAllHumans()) == null){
                        //no in human data
                        System.out.println("Please insert driver name");
                        driver.setName(Menu.inputInConsoleForString());
                        System.out.println("Please insert driver surname");
                        driver.setSurName(Menu.inputInConsoleForString());
                        System.out.println("Please insert driver birthdate. Like   yyyy-MM-dd");
                        driver.setBirthDate(Menu.inputInConsoleForString());
                        System.out.println("Please insert driver worker Id. Worker id should be unique. ");
                        driver.setWorkerId(Menu.inputInConsoleForInt());
                        if(getWorkerByWorkerId(driver.getWorkerId(), FileService.getAllWorkers()) == null &&
                            getWorkerByWorkerId(driver.getWorkerId(), FileService.getAllDrivers()) == null){
                            System.out.println("Please insert driver salary");
                            driver.setSalary(Menu.inputInConsoleForInt());
                            System.out.println("Please insert driver start work year");
                            driver.setStartWorkYear(Menu.inputInConsoleForInt());
                            System.out.println("Please insert driver licence id. Licence id should be unique. ");
                            driver.setDriverLicenceId(Menu.inputInConsoleForInt());
                            if(getDriverByDriverLicenseId(driver.getDriverLicenceId(), FileService.getAllDrivers()) == null){
                                System.out.println("Please insert driver car model");
                                driver.setCarModel(Menu.inputInConsoleForString());
                                System.out.println("Please insert driver car number");
                                driver.setCarNumber(Menu.inputInConsoleForString());
                                isAdded = FileService.writeDriver(driver);
                                FileService.addDriver(driver);
                            } else {
                                System.out.println("There is already exist driver in that driver license id");
                            }
                        } else {
                            System.out.println("There is already exist worker in that worker id");
                        }
                    } else {
                        //In Human Data
                        System.out.println("Please insert driver name");
                        driver.setName(Menu.inputInConsoleForString());
                        if(!getHumanByPassportId(driver.getPassportId(), FileService.getAllHumans()).getName().equals(driver.getName())){
                            System.out.println("There is exist human with that passport id, but with another name");
                        } else {
                            System.out.println("Please insert driver surname");
                            driver.setSurName(Menu.inputInConsoleForString());
                            if(!getHumanByPassportId(driver.getPassportId(), FileService.getAllHumans()).getSurName().equals(driver.getSurName())){
                                System.out.println("There is exist human with that passport id and name, but with another surname");
                            } else {
                                System.out.println("Please insert driver birthdate. Like   yyyy-MM-dd");
                                driver.setBirthDate(Menu.inputInConsoleForString());
                                if(!getHumanByPassportId(driver.getPassportId(), FileService.getAllHumans()).getBirthDate().equals(driver.getBirthDate())){
                                    System.out.println("There is exist human with that passport id, name and surname, but with another birth date");
                                } else {
                                    System.out.println("Please insert driver worker Id. Worker id should be unique. ");
                                    driver.setWorkerId(Menu.inputInConsoleForInt());
                                    if(getWorkerByWorkerId(driver.getWorkerId(), FileService.getAllWorkers()) == null &&
                                            getWorkerByWorkerId(driver.getWorkerId(), FileService.getAllDrivers()) == null){
                                        System.out.println("Please insert driver licence id. Licence id should be unique.");
                                        driver.setDriverLicenceId(Menu.inputInConsoleForInt());
                                        if(getDriverByDriverLicenseId(driver.getDriverLicenceId(), FileService.getAllDrivers()) == null){
                                            System.out.println("Please insert driver car model");
                                            driver.setCarModel(Menu.inputInConsoleForString());
                                            System.out.println("Please insert driver car number");
                                            driver.setCarNumber(Menu.inputInConsoleForString());
                                            System.out.println("Please insert driver salary");
                                            driver.setSalary(Menu.inputInConsoleForInt());
                                            System.out.println("Please insert driver start work year");
                                            driver.setStartWorkYear(Menu.inputInConsoleForInt());
                                            isAdded = FileService.writeDriver(driver);
                                            FileService.addDriver(driver);
                                            removeHuman(driver.getPassportId());
                                        } else {
                                            System.out.println("There is already exist driver in that driver license id");
                                        }
                                    } else {
                                        System.out.println("There is already exist worker in that worker id");
                                    }
                                }
                            }
                        }
                    }
                } else {
                    //In Worker Data
                    System.out.println("Please insert driver name");
                    driver.setName(Menu.inputInConsoleForString());
                    if(!getWorkerByPassportId(driver.getPassportId(), FileService.getAllWorkers()).getName().equals(driver.getName())){
                        System.out.println("There is exist worker with that passport id, but with another name");
                    } else {
                        System.out.println("Please insert driver surname");
                        driver.setSurName(Menu.inputInConsoleForString());
                        if(!getWorkerByPassportId(driver.getPassportId(), FileService.getAllWorkers()).getSurName().equals(driver.getSurName())){
                            System.out.println("There is exist worker with that passport id and name, but with another surname");
                        } else {
                            System.out.println("Please insert driver birthdate. Like   yyyy-MM-dd");
                            driver.setBirthDate(Menu.inputInConsoleForString());
                            if(!getWorkerByPassportId(driver.getPassportId(), FileService.getAllWorkers()).getBirthDate().equals(driver.getBirthDate())){
                                System.out.println("There is exist worker with that passport id, name and surname, but with another birth date");
                            } else {
                                System.out.println("Please insert driver worker Id. Worker id should be unique. ");
                                driver.setWorkerId(Menu.inputInConsoleForInt());
                                if(getWorkerByPassportId(driver.getPassportId(), FileService.getAllWorkers()).getWorkerId() != driver.getWorkerId()){
                                    System.out.println("There is exist worker with that passport id, name, surname and birth date, but with another worker id");
                                } else {
                                    System.out.println("Please insert driver salary");
                                    driver.setSalary(Menu.inputInConsoleForInt());
                                    if(getWorkerByPassportId(driver.getPassportId(), FileService.getAllWorkers()).getSalary() != driver.getSalary()){
                                        System.out.println("There is exist worker with that passport id, name, surname, birth date, and worker id, but with another salary");
                                    } else {
                                        System.out.println("Please insert driver start work year");
                                        driver.setStartWorkYear(Menu.inputInConsoleForInt());
                                        if(getWorkerByPassportId(driver.getPassportId(), FileService.getAllWorkers()).getStartWorkYear() != driver.getStartWorkYear()){
                                            System.out.println("There is exist worker with that passport id, name, surname, birth date, worker id and salary, but with another start work year");
                                        } else {
                                            System.out.println("Please insert driver licence id. Licence id should be unique. ");
                                            driver.setDriverLicenceId(Menu.inputInConsoleForInt());
                                            if(getDriverByDriverLicenseId(driver.getDriverLicenceId(), FileService.getAllDrivers()) == null){
                                                System.out.println("Please insert driver car model");
                                                driver.setCarModel(Menu.inputInConsoleForString());
                                                System.out.println("Please insert driver car number");
                                                driver.setCarNumber(Menu.inputInConsoleForString());
                                                isAdded = FileService.writeDriver(driver);
                                                FileService.addDriver(driver);
                                                removeWorker(driver.getPassportId());
                                            } else {
                                                System.out.println("There is already exist driver in that driver license id");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                //In Driver Data
                System.out.println("There is already exist driver with that passport id");
            }

        } catch (Exception e) {
            System.out.print("\033[0;91m" + "You insert wrong data.");
            System.out.println(" " + e.getMessage());
        }
        return isAdded;
    }


    public boolean removeDriver(int passportID){
        boolean isRemovedInDrivers = false;
        Worker deletedDriver = null;
        Worker[] drivers =  FileService.getAllDrivers();

        for (int i = 0; i < drivers.length; i++) {
            if(drivers[i].getPassportId() == passportID){
                deletedDriver = drivers[i];
                drivers[i] = null;
                isRemovedInDrivers = true;
                break;
            }
        }
        if(isRemovedInDrivers) {
            FileService.setAllDrivers( Arrays.stream(getArrayWithoutNullElements(drivers)).toArray(Driver[]::new));
            FileService.rewriteDrivers(FileService.getAllDrivers());
            Worker worker = new Worker(deletedDriver.getPassportId(), deletedDriver.getName(), deletedDriver.getSurName(), deletedDriver.getBirthDate(), deletedDriver.getWorkerId(), deletedDriver.getSalary(), deletedDriver.getStartWorkYear());
            FileService.writeWorker(worker);
            FileService.addWorker(worker);
        }
        return  isRemovedInDrivers;
    }
}
