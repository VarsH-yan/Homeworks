package plane;

import fileServices.FileService;
import menu.Menu;


public class PlaneService {
    public Plane getPlaneByModel(String model){
        Plane[] planes = FileService.getAllPlanes();
        for (Plane plane: planes) {
            if(plane.getModel().equals(model)){
                return plane;
            }
        }
        return null;
    }

    public void printPlaneInfoByModel(String model){
        if(getPlaneByModel(model) != null){
            printPlaneInfo(getPlaneByModel(model));
            return;
        }
        System.out.println("There is no plane with that model");
    }


    public void printPlaneInfo(Plane plane){
        System.out.println("Model is: " + plane.getModel());
        System.out.println("Country is: " + plane.getCountry());
        System.out.println("Year is: " + plane.getYear());
        System.out.println("Hours is: " + plane.getHours());
        System.out.println("Weight is: " + plane.getWeight());
        System.out.println("Wingspan is: " + plane.getWingspan());
        System.out.println("TopSpeed is: " + plane.getTopSpeed());
        System.out.println("Seats is: " + plane.getSeats());
        System.out.println("Cost is: " + plane.getCost());
        System.out.println("Is military: " + (plane.isMilitary() ? "Yes" : "No") + "\n");
    }

    public void printAllPlaneInfo(){
        Plane[] planes = FileService.getAllPlanes();
        if(planes.length == 0){
            System.out.println("There is no plane");
        } else {
            for (Plane element: planes) {
                printPlaneInfo(element);
            }
        }
    }

    public boolean addPlane(){
        boolean isAdded = false;
        Plane plane = new Plane();
        try{
            System.out.println("Please insert plane model. Model should be unique");
            plane.setModel(Menu.inputInConsoleForString());
            if(getPlaneByModel(plane.getModel()) == null){
                System.out.println("Please insert plane country(Any not empty string)");
                plane.setCountry(Menu.inputInConsoleForString());
                System.out.println("Please insert plane year(from 1903 to 2020)");
                plane.setYear(Menu.inputInConsoleForInt());
                System.out.println("Please insert plane hours(from 0 to 10000)");
                plane.setHours(Menu.inputInConsoleForInt());
                System.out.println("Please insert plane weight(from 1000 KG to 160000 KG)");
                plane.setWeight(Menu.inputInConsoleForInt());
                System.out.println("Please insert plane wingspan(from 10 - 45)");
                plane.setWingspan(Menu.inputInConsoleForInt());
                System.out.println("Please insert plane topSpeed(1000 km/h)");
                plane.setTopSpeed(Menu.inputInConsoleForInt());
                System.out.println("Please insert plane seats count(any not negative value)");
                plane.setSeats(Menu.inputInConsoleForInt());
                System.out.println("Please insert plane cost(any not negative value)");
                plane.setCost(Menu.inputInConsoleForDouble());
                System.out.println("Please insert plane is military: (if military press 'y')" );
                plane.setMilitary(Menu.inputInConsoleForString().charAt(0) == 'y' ? true : false);
                isAdded = FileService.writePlane(plane, FileService.getAllPlanes().length == 0);
                FileService.addPlane(plane);
            } else {
                System.out.println("There is already exist plane with that model");
            }
        } catch (Exception e){

            System.out.print("\033[0;91m" + "You insert wrong data.");
            System.out.println(" " + e.getMessage());
        }
        return isAdded;
    }

    public boolean removePlaneByModel(String model){
        Plane[] planes =  FileService.getAllPlanes();
        boolean isRemoved = false;
        for (int i = 0; i < planes.length; i++) {
            if(planes[i].getModel().equals(model)){
                planes[i] = null;
                isRemoved = true;
                break;
            }
        }

        if(isRemoved){
            FileService.setAllPlanes(getArrayWithoutNullElements(planes));
            FileService.rewritePlanes(FileService.getAllPlanes());
        }
        return isRemoved;
    }

    public Plane[] getArrayWithoutNullElements(Plane[] planes) {
        Plane[] array = new Plane[planes.length];
        int index = 0;
        for (int i = 0; i < planes.length; i++) {
            if (planes[i] != null) {
                array[index++] = planes[i];
            }
        }

        planes = array;
        return correctArray(planes, index);
    }

    public Plane[] correctArray(Plane[] planes, int size) {
        Plane[] result = new Plane[size];
        for (int i = 0; i < size; i++) {
            result[i] = planes[i];
        }
        return result;
    }


    public void printCostAndTopSpeedOrModelAndCountryByModel(String model){
        if(getPlaneByModel(model) != null){
            printCostAndTopSpeedOrModelAndCountry(getPlaneByModel(model));
            return;
        }
        System.out.println("There is no plane with that model");
    }

    public void printCostAndTopSpeedOrModelAndCountry(Plane plane){
        if (plane.isMilitary()){
            System.out.println("Cost is: " + plane.getCost() + ", topSpeed is: " + plane.getTopSpeed());
        }
        else {
            System.out.println("Model is: " + plane.getModel() + ", Country is: " + plane.getCountry());
        }
    }



    public Plane getNewPlaneBetweenTwoPlanes(Plane plane1, Plane plane2){
        if (plane1.getYear() >= plane2.getYear()){
            return plane1;
        }
        else {
            return plane2;
        }
    }

    public String getModelWhichHasBiggerWingspan(Plane plane1, Plane plane2){
        if (plane1.getWingspan() > plane2.getWingspan()){
            return plane1.getModel();
        }
        else{
            return plane2.getModel();
        }
    }

    public void printCountryOfThePlaneWithSmallestSeatsCount(Plane plane1, Plane plane2, Plane plane3){
        if (plane1.getSeats() <= plane2.getSeats()){
            if (plane1.getSeats() > plane3.getSeats()){
                System.out.println(plane3.getCountry());
            }
            else {
                System.out.println(plane1.getCountry());
            }
        }
        else {
            if (plane2.getSeats() > plane3.getSeats()){
                System.out.println(plane3.getCountry());
            }
            else {
                System.out.println(plane2.getCountry());
            }
        }
    }


        public void printAllNotMilitaryPlanes(Plane[] planes){
            boolean thereIsNotMilitaryPlane = false;
            for (Plane element : planes) {
                if (!element.isMilitary()){
                    thereIsNotMilitaryPlane = true;
                    printPlaneInfo(element);
                }
            }
            if(!thereIsNotMilitaryPlane){
                System.out.println("There is no not military plane");
            }
        }

    public void printAllMilitaryPlanesWhichWereInAirMoreThan100Hours(){
        Plane[] planes = FileService.getAllPlanes();
        for (Plane element : planes) {
            if (element.isMilitary() && element.getHours() > 100){
                printPlaneInfo(element);
            }
        }
    }

    public void printPlaneWithMinimalWeight(){
        Plane[] planes = FileService.getAllPlanes();
        printPlaneInfo(getPlaneWithMinimalWeight(planes));
    }

    public Plane getPlaneWithMinimalWeight(Plane[] planes){
        Plane minWeightPlane = null;
        for (Plane element : planes) {
            if (minWeightPlane == null){
                minWeightPlane = element;
            }
            else if (element.getWeight() < minWeightPlane.getWeight()){
                minWeightPlane = element;
            }
        }
        return minWeightPlane;
    }

    public void printPlaneWithMinimalCostFromAllMilitaryPlanes(){
        printPlaneInfo(getPlaneWithMinimalCostFromAllMilitaryPlanes());
    }

    public Plane getPlaneWithMinimalCostFromAllMilitaryPlanes(){
        Plane[] planes = FileService.getAllPlanes();
        Plane minCostAndMilitary = null;
        for (Plane element : planes) {
            if(element.isMilitary()){
                if (minCostAndMilitary == null){
                    minCostAndMilitary = element;
                }
                else if (element.isMilitary() && element.getCost() < minCostAndMilitary.getCost()){
                    minCostAndMilitary = element;
                }
            }
        }
        return minCostAndMilitary;
    }

    public void printPlanesInAscendingFormOrderByYear(){
        Plane[] planes = FileService.getAllPlanes();
        Plane[] planesAscendingFormOrderByYear = new Plane[planes.length];
        for (int i = 0; i < planes.length; i++) {
            planesAscendingFormOrderByYear[i] = planes[i];
        }
        Plane tempPlane = null;
        for (int i = 0; i < planesAscendingFormOrderByYear.length; i++) {
            for (int j = 0; j < planesAscendingFormOrderByYear.length  - 1; j++) {
                if (planesAscendingFormOrderByYear[j].getYear() > planesAscendingFormOrderByYear[j + 1].getYear()){
                    tempPlane = planesAscendingFormOrderByYear[j];
                    planesAscendingFormOrderByYear[j] = planesAscendingFormOrderByYear[j + 1];
                    planesAscendingFormOrderByYear[j + 1] = tempPlane;
                }
            }
        }
        for (Plane element : planesAscendingFormOrderByYear) {
            printPlaneInfo(element);
        }
    }
}
