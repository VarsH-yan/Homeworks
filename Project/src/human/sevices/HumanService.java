package human.sevices;


import fileServices.FileService;
import human.Driver;
import human.Human;
import human.Worker;
import menu.Menu;

import java.util.Arrays;

public class HumanService {

    public void printHuman(Human human) {
        if(human != null){
            System.out.println("Passport ID is: " + human.getPassportId());
            System.out.println("Name is: " + human.getName());
            System.out.println("Surname is: " + human.getSurName());
            System.out.println("Birth date is: " + human.getBirthDateString());
        }
    }

    public void printAllHumans(Human[] humans) {
        if(humans.length == 0){
            System.out.println("There is no human");
        } else {
            for (Human human : humans) {
                System.out.println();
                printHuman(human);
            }
        }
    }

    public Human getHumanByPassportId(int passportId, Human[] humans){
        for (Human human : humans) {
            if(human.getPassportId() == passportId){
                return human;
            }
        }
        return null;
    }

    public Human getHumanWithLongestName(Human[] humans){
        Human humanWithLongestName = null;
        for (Human human : humans) {
            if(humanWithLongestName == null){
                humanWithLongestName = human;
            } else {
                if(human.getName().length() > humanWithLongestName.getName().length()){
                    humanWithLongestName = human;
                }
            }
        }
        return humanWithLongestName;
    }

    public Human getHumanWithShortestName(Human[] humans){
        Human humanWithShortestName = null;
        for (Human human : humans) {
            if(humanWithShortestName == null){
                humanWithShortestName = human;
            } else {
                if(human.getName().length() < humanWithShortestName.getName().length()){
                    humanWithShortestName = human;
                }
            }
        }
        return humanWithShortestName;
    }

    public Human getHumanWithShortestSurname(Human[] humans){
        Human humanWithShortestSurname = null;
        for (Human human : humans) {
            if(humanWithShortestSurname == null){
                humanWithShortestSurname = human;
            } else {
                if(human.getName().length() < humanWithShortestSurname.getName().length()){
                    humanWithShortestSurname = human;
                }
            }
        }
        return humanWithShortestSurname;
    }

    public Human getHumanWithLongestSurname(Human[] humans){
        Human humanWithLongestSurname = null;
        for (Human human : humans) {
            if(humanWithLongestSurname == null){
                humanWithLongestSurname = human;
            } else {
                if(human.getSurName().length() > humanWithLongestSurname.getSurName().length()){
                    humanWithLongestSurname = human;
                }
            }
        }
        return humanWithLongestSurname;
    }

    public Human getBiggestHuman(Human[] humans){
        Human biggestHuman = null;
        for (Human human : humans) {
            if(biggestHuman == null){
                biggestHuman = human;
            } else {
                if(human.getBirthDate().isBefore(biggestHuman.getBirthDate())){
                    biggestHuman = human;
                }
            }
        }
        return biggestHuman;
    }

    public Human getSmallestHuman(Human[] humans){
        Human smallestHuman = null;
        for (Human human : humans) {
            if(smallestHuman == null){
                smallestHuman = human;
            } else {
                if(human.getBirthDate().isAfter(smallestHuman.getBirthDate())){
                    smallestHuman = human;
                }
            }
        }
        return smallestHuman;
    }

    public boolean addHuman(){
        boolean isAdded = false;
        Human human = new Human();
        try{
            System.out.println("Please insert passport Id. Passport id should be unique.");
            human.setPassportId(Menu.inputInConsoleForInt());
            if(getHumanByPassportId(human.getPassportId(), FileService.getAllHumans()) == null &&
                    getHumanByPassportId(human.getPassportId(), FileService.getAllWorkers()) == null &&
                    getHumanByPassportId(human.getPassportId(), FileService.getAllDrivers()) == null){
                System.out.println("Please insert human name");
                human.setName(Menu.inputInConsoleForString());
                System.out.println("Please insert human surname");
                human.setSurName(Menu.inputInConsoleForString());
                System.out.println("Please insert human birthdate. Like   yyyy-MM-dd");
                human.setBirthDate(Menu.inputInConsoleForString());
                isAdded = FileService.writeHuman(human);
                FileService.addHuman(human);
            } else {
                System.out.println("There is already exist human with that passport ID");
            }

        } catch (Exception e){
            System.out.print("\033[0;91m" + "You insert wrong data.");
            System.out.println(" " + e.getMessage());
        }
        return isAdded;
    }

    public boolean removeHuman(int passportID){
        boolean isRemovedInHuman = false;
        boolean isRemovedInWorkers = false;
        boolean isRemovedInDrivers = false;
        Human[] humans =  FileService.getAllHumans();
        Worker[] workers =  FileService.getAllWorkers();
        Worker[] drivers =  FileService.getAllDrivers();

        for (int i = 0; i < humans.length; i++) {
            if(humans[i].getPassportId() == passportID){
                humans[i] = null;
                isRemovedInHuman = true;
                break;
            }
        }
        if(isRemovedInHuman){
            FileService.setAllHumans(getArrayWithoutNullElements(humans));
            FileService.rewriteHumans(FileService.getAllHumans());
        } else {
            for (int i = 0; i < workers.length; i++) {
                if(workers[i].getPassportId() == passportID){
                    workers[i] = null;
                    isRemovedInWorkers = true;
                    break;
                }
            }
            if(isRemovedInWorkers){
                FileService.setAllWorkers( Arrays.stream(getArrayWithoutNullElements(workers)).toArray(Worker[]::new));
                FileService.rewriteWorkers(FileService.getAllWorkers());
            } else {
                for (int i = 0; i < drivers.length; i++) {
                    if(drivers[i].getPassportId() == passportID){
                        drivers[i] = null;
                        isRemovedInDrivers = true;
                        break;
                    }
                }
                if(isRemovedInDrivers) {
                    FileService.setAllDrivers( Arrays.stream(getArrayWithoutNullElements(drivers)).toArray(Driver[]::new));
                    FileService.rewriteDrivers(FileService.getAllDrivers());
                }
            }
        }
        return  isRemovedInHuman || isRemovedInWorkers || isRemovedInDrivers;
    }


    public Human[] getArrayWithoutNullElements(Human[] humans) {
        Human[] array = new Human[humans.length];
        int index = 0;
        for (int i = 0; i < humans.length; i++) {
            if (humans[i] != null) {
                array[index++] = humans[i];
            }
        }

        humans = array;
        return correctArray(humans, index);
    }

    public Human[] correctArray(Human[] humans, int size) {
        Human[] result = new Human[size];
        for (int i = 0; i < size; i++) {
            result[i] = humans[i];
        }
        return result;
    }
}


