package company;


public class Truck {

    private int idOfTruck;
    private String nameOfTruck;
    private String driverOfTruck;
    private State state;

    public Truck() {
    }

    public int getIdOfTruck() {
        return idOfTruck;
    }

    public void setIdOfTruck(int idOfTruck) {
        this.idOfTruck = idOfTruck;
    }

    public String getNameOfTruck() {
        return nameOfTruck;
    }

    public void setNameOfTruck(String nameOfTruck) {
        this.nameOfTruck = nameOfTruck;
    }

    public String getDriverOfTruck() {
        return driverOfTruck;
    }

    public void setDriverOfTruck(String driverOfTruck) {
        this.driverOfTruck = driverOfTruck;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public static Truck makeTruck(int idOfTruck, String nameOfTruck,String driverOfTruck,State state) {
        Truck truck = new Truck();
        truck.idOfTruck = idOfTruck;
        truck.nameOfTruck = nameOfTruck;
        truck.driverOfTruck = driverOfTruck;
        truck.state = state;
        return truck;
    }

    public static void changeDriver(Truck truck, Driver[] drivers) {

        if(truck.getState().equals(State.BASE)) {
            for (Driver driver : drivers) {
                if(driver.getBusOfDriver().matches("[^a-zA-Z]*")){
                    truck.setDriverOfTruck(driver.getNameOfDriver());
                    driver.setBusOfDriver(truck.getNameOfTruck());
                    break;
                }
            }
            try {
                if (truck.state.equals(State.ROUTE)) {
                    throw new InvalidException ();
                }
            } catch (InvalidException e) {
                System.err.printf("YOU CAN'T CHANGE DRIVER, BECAUSE THE TRUCK[%S] ON THE ROUTE\n", truck.getNameOfTruck());
            }
            try {
                if (truck.state.equals(State.REPAIR)) {
                    throw new InvalidException();
                }
            } catch (InvalidException e) {
                System.err.printf("YOU CAN'T CHANGE DRIVER, BECAUSE THE TRUCK[%S] ON THE REPAIR\n", truck.getNameOfTruck());
            }

        }
    }

    public static void startDriving(Truck truck) {
        if (truck.state.equals(State.BASE)) {
            truck.setState(State.ROUTE);
        }
        try {
            if (truck.getDriverOfTruck().matches("[^a-zA-Z]*")) {
                throw new InvalidException();
            }

        } catch (InvalidException e) {
            System.err.printf("THE TRUCK[%S] HAVE NOT A DRIVER\n", truck.getNameOfTruck());
        }
        try {
            if (truck.state.equals(State.REPAIR)) {
                throw new InvalidException();
            }
        } catch (InvalidException e) {
            System.err.printf("YOU CAN'T COMMENCE TO DRIVE , BECAUSE THE TRUCK[%S] ON THE REPAIR\n", truck.getNameOfTruck());
        }
    }

    public static void startRepair(Truck truck) {
        try {
            if (truck.state.equals(State.REPAIR)) {
                throw new InvalidException();
            }
        } catch (InvalidException e) {
            System.err.printf("THIS TRUCK[%S] ALREADY ON THE REPAIR\n", truck.getNameOfTruck());
        }
        try {
            if (truck.state.equals(State.ROUTE)) {
                throw new InvalidException();
            }
        } catch (InvalidException e) {
            System.err.printf("YOU CAN'T START REPAIRING , BECAUSE TRUCK[%S] ON THE ROUTE \n", truck.getNameOfTruck());
        }
        if (truck.state.equals(State.BASE)) {
            truck.state = State.REPAIR;
        }
    }

    @Override
    public String toString() {
        return idOfTruck + "  |  " + nameOfTruck + "    |  " + driverOfTruck + "         | " + state;
    }
}
