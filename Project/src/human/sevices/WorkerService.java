package human.sevices;


import fileServices.FileService;
import human.Driver;
import human.Human;
import human.Worker;
import menu.Menu;

import java.util.Arrays;

public class WorkerService extends HumanService {

    public void printWorkerInfo(Worker worker) {
        if(worker != null){
            printHuman(worker);
            System.out.println("Worker id is: " + worker.getWorkerId());
            System.out.println("Salary is: " + worker.getSalary());
            System.out.println("Start work year is: " + worker.getStartWorkYear());
        }
    }

    public void printWorkers(Worker[] workers) {
        for (Worker worker : workers) {
            System.out.println();
            printWorkerInfo(worker);
        }
    }

    public void printAllWorkers() {
        Worker[] workers = FileService.getAllWorkers();
        Worker[] drivers = FileService.getAllDrivers();
        if(workers.length == 0 && drivers.length == 0){
            System.out.println("There is no worker");
        } else {
            for (Worker worker : workers) {
                System.out.println();
                printWorkerInfo(worker);
            }
            for (Worker worker : drivers) {
                System.out.println();
                printWorkerInfo(worker);
            }
        }
    }


    public Worker getWorkerByWorkerId(int workerId, Worker[] workers){
        for (Worker worker : workers) {
            if(worker.getWorkerId() == workerId){
                return worker;
            }
        }
        return null;
    }

    public Worker getWorkerByPassportId(int passportId, Worker[] workers){
        for (Worker worker : workers) {
            if(worker.getPassportId() == passportId){
                return worker;
            }
        }
        return null;
    }


    public Worker getWorkerWithLongestName(Worker[] workers){
        return (Worker)getHumanWithLongestName(workers);
    }

    public Worker getWorkerWithShortestName(Worker[] workers){
        return (Worker) getHumanWithShortestName(workers);
    }

    public Worker getWorkerWithShortestSurname(Worker[] workers){
        return (Worker) getHumanWithShortestSurname(workers);
    }

    public Worker getWorkerWithLongestSurname(Worker[] workers){
        return (Worker) getHumanWithLongestSurname(workers);
    }

    public Worker getBiggestWorker(Worker[] workers){
        return (Worker) getBiggestHuman(workers);
    }

    public Worker getSmallestWorker(Worker[] workers){
        return (Worker) getSmallestHuman(workers);
    }
    public Worker getMaxSalaryWorker(Worker[] workers) {
        Worker maxSalaryWorker = null;
        for (Worker worker : workers) {
            if (maxSalaryWorker == null) {
                maxSalaryWorker = worker;
            } else {
                if (worker.getSalary() > maxSalaryWorker.getSalary()) {
                    maxSalaryWorker = worker;
                }
            }
        }
        return maxSalaryWorker;
    }

    public Worker getMinSalaryWorker(Worker[] workers) {
        Worker minSalaryWorker = null;
        for (Worker worker : workers) {
            if (minSalaryWorker == null) {
                minSalaryWorker = worker;
            } else {
                if (worker.getSalary() < minSalaryWorker.getSalary()) {
                    minSalaryWorker = worker;
                }
            }
        }
        return minSalaryWorker;
    }

    public Worker getLatestStartWorker(Worker[] workers) {
        Worker latestStartWorker = null;
        for (Worker worker : workers) {
            if (latestStartWorker == null) {
                latestStartWorker = worker;
            } else {
                if (worker.getStartWorkYear() > latestStartWorker.getSalary()) {
                    latestStartWorker = worker;
                }
            }
        }
        return latestStartWorker;
    }

    public Worker getSoonestStartWorker(Worker[] workers) {
        Worker soonestStartWorker = null;
        for (Worker worker : workers) {
            if (soonestStartWorker == null) {
                soonestStartWorker = worker;
            } else {
                if (worker.getStartWorkYear() < soonestStartWorker.getSalary()) {
                    soonestStartWorker = worker;
                }
            }
        }
        return soonestStartWorker;
    }

    public Worker[] getWorkerWhosWorkerIdContainsThisNumber(int number, Worker[] workers) {
        Worker[] resultWorkers = new Worker[workers.length];
        int index = 0;
        if (number >= 0 && number < 10) {
            for (Worker worker : workers) {
                if(containsNumberInID(number, worker.getWorkerId())){
                    resultWorkers[index++] = worker;
                }
            }
        }
        return correctArray(resultWorkers, index);
    }

    public boolean containsNumberInID(int number, int id) {
        while (id > 0) {
            if (id % 10 == number) {
                return true;
            }
            id /= 10;
        }
        return false;
    }

    private Worker[] correctArray(Worker[] workers, int size) {
        Worker[] result = new Worker[size];
        for (int i = 0; i < size; i++) {
            result[i] = workers[i];
        }
        return result;
    }

    public boolean addWorker(){
        boolean isAdded = false;
        Worker worker = new Worker();
        try{
            System.out.println("Please insert passport Id. Passport id should be unique");
            worker.setPassportId(Menu.inputInConsoleForInt());
            if(getWorkerByPassportId(worker.getPassportId(), FileService.getAllWorkers()) == null &&
                getWorkerByPassportId(worker.getPassportId(), FileService.getAllDrivers()) == null){
                if(getHumanByPassportId(worker.getPassportId(), FileService.getAllHumans()) == null){
                    System.out.println("Please insert worker name");
                    worker.setName(Menu.inputInConsoleForString());
                    System.out.println("Please insert worker surname");
                    worker.setSurName(Menu.inputInConsoleForString());
                    System.out.println("Please insert worker birthdate. Like   yyyy-MM-dd");
                    worker.setBirthDate(Menu.inputInConsoleForString());
                    System.out.println("Please insert worker Id. Worker id Should be unique.");
                    worker.setWorkerId(Menu.inputInConsoleForInt());
                    if(getWorkerByWorkerId(worker.getWorkerId(), FileService.getAllWorkers()) == null &&
                        getWorkerByWorkerId(worker.getWorkerId(), FileService.getAllDrivers()) == null){
                        System.out.println("Please insert worker salary");
                        worker.setSalary(Menu.inputInConsoleForInt());
                        System.out.println("Please insert worker start work year");
                        worker.setStartWorkYear(Menu.inputInConsoleForInt());
                        isAdded = FileService.writeWorker(worker);
                        FileService.addWorker(worker);
                    } else {
                        System.out.println("There is already exist worker with that worker id. Worker id should be unique");
                    }
                } else {
                    System.out.println("Please insert worker name");
                    worker.setName(Menu.inputInConsoleForString());
                    if(!getHumanByPassportId(worker.getPassportId(), FileService.getAllHumans()).getName().equals(worker.getName())){
                        System.out.println("There is exist human with that passport id, but with another name");
                    } else {
                        System.out.println("Please insert worker surname");
                        worker.setSurName(Menu.inputInConsoleForString());
                        if(!getHumanByPassportId(worker.getPassportId(), FileService.getAllHumans()).getSurName().equals(worker.getSurName())){
                            System.out.println("There is exist human with that passport id and name, but with another surname");
                        } else {
                            System.out.println("Please insert worker birthdate. Like   yyyy-MM-dd");
                            worker.setBirthDate(Menu.inputInConsoleForString());
                            if(!getHumanByPassportId(worker.getPassportId(), FileService.getAllHumans()).getBirthDate().equals(worker.getBirthDate())){
                                System.out.println("There is exist human with that passport id, name and surname, but with another birth date");
                            } else {
                                System.out.println("Please insert worker Id. Worker id Should be unique.");
                                worker.setWorkerId(Menu.inputInConsoleForInt());
                                System.out.println("Please insert worker salary");
                                worker.setSalary(Menu.inputInConsoleForInt());
                                System.out.println("Please insert worker start work year");
                                worker.setStartWorkYear(Menu.inputInConsoleForInt());
                                isAdded = FileService.writeWorker(worker);
                                FileService.addWorker(worker);
                                removeHuman(worker.getPassportId());
                            }
                        }
                    }
                }
            } else {
                System.out.println("There is already exist worker with that passport id");
            }

        } catch (Exception e){
            System.out.print("\033[0;91m" + "You insert wrong data.");
            System.out.println(" " + e.getMessage());
        }
        return isAdded;
    }

    public boolean removeWorker(int passportID){
        boolean isRemovedInWorkers = false;
        boolean isRemovedInDrivers = false;
        Worker deletedWorker = null;
        Worker[] workers =  FileService.getAllWorkers();
        Worker[] drivers =  FileService.getAllDrivers();
        for (int i = 0; i < workers.length; i++) {
            if(workers[i].getPassportId() == passportID){
                deletedWorker = workers[i];
                workers[i] = null;
                isRemovedInWorkers = true;
                break;
            }
        }
        if(isRemovedInWorkers){
            FileService.setAllWorkers( Arrays.stream(getArrayWithoutNullElements(workers)).toArray(Worker[]::new));
            FileService.rewriteWorkers(FileService.getAllWorkers());
            if(getWorkerByPassportId(deletedWorker.getPassportId(), FileService.getAllDrivers()) == null){
                Human human = new Human(deletedWorker.getPassportId(), deletedWorker.getName(), deletedWorker.getSurName(), deletedWorker.getBirthDate());
                FileService.writeHuman(human);
                FileService.addHuman(human);
            }
        } else {
            for (int i = 0; i < drivers.length; i++) {
                if(drivers[i].getPassportId() == passportID){
                    deletedWorker = drivers[i];
                    drivers[i] = null;
                    isRemovedInDrivers = true;
                    break;
                }
            }
            if(isRemovedInDrivers) {
                FileService.setAllDrivers( Arrays.stream(getArrayWithoutNullElements(drivers)).toArray(Driver[]::new));
                FileService.rewriteDrivers(FileService.getAllDrivers());
                Human human = new Human(deletedWorker.getPassportId(), deletedWorker.getName(), deletedWorker.getSurName(), deletedWorker.getBirthDate());
                FileService.writeHuman(human);
                FileService.addHuman(human);
            }
        }
        return  isRemovedInWorkers || isRemovedInDrivers;
    }

}
