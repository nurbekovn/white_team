package company;

public class Driver {

    private int idOfDriver;
    private String nameOfDriver;
    private String busOfDriver;
    private State stateOfDriver;


    public Driver() {
    }

    public int getIdOfDriver() {
        return idOfDriver;
    }

    public void setIdOfDriver(int idOfDriver) {
        this.idOfDriver = idOfDriver;
    }

    public String getNameOfDriver() {
        return nameOfDriver;
    }

    public void setNameOfDriver(String nameOfDriver) {
        this.nameOfDriver = nameOfDriver;
    }

    public String getBusOfDriver() {
        return busOfDriver;
    }

    public void setBusOfDriver(String busOfDriver) {
        this.busOfDriver = busOfDriver;
    }

    public State getStateOfDriver() {
        return stateOfDriver;
    }

    public void setStateOfDriver(State stateOfDriver) {
        this.stateOfDriver = stateOfDriver;
    }

    public static Driver makeDriver(int idOfDriver, String nameOfDriver, String busOfDriver, State stateOfDriver) {
        Driver driver = new Driver();
        driver.idOfDriver = idOfDriver;
        driver.nameOfDriver = nameOfDriver;
        driver.busOfDriver = busOfDriver;
        driver.stateOfDriver = stateOfDriver;
        return driver;
    }

    @Override
    public String toString() {
        return idOfDriver + "  |  " + nameOfDriver + "            | " + busOfDriver +   "    | " + stateOfDriver;

    }
}
