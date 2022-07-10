package company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final GsonBuilder BUILDER = new GsonBuilder();
    public static final Gson GSON = BUILDER.setPrettyPrinting().create();
    public static final Path WRITE_PATH = Paths.get("./trucks.json");
    public static final Path WRITE_PATH_DRIVERS = Paths.get("./drivers.json");

    public static void main(String[] args) {
        System.out.println("Hello world!");

        Truck[] trucks = {
                Truck.makeTruck(1,"Renault Magnum", "", State.BASE),
                Truck.makeTruck(2,"Volvo FH12", "",State.BASE),
                Truck.makeTruck(3,"DAF XT", "",State.BASE),
        };

        Driver[] drivers = {
                Driver.makeDriver(1,"Petr","",null),
                Driver.makeDriver(2,"Askar","",null),
                Driver.makeDriver(3,"Uson","",null),
        };

        String json = GSON.toJson(trucks);
        write(json);
//        System.out.println(readFile());

        String son = GSON.toJson(drivers);
        writeDrivers(son);
//        System.out.println(readFileDrivers());

        System.out.println("------------------------TRUCKS-------------------------");
        System.out.println(" # |  Bus               |  Drivers      |  State       ");
        System.out.println("---+--------------------+---------------+--------------");

        for (Object truck: trucks) {
            System.out.println(truck);
        }
        System.out.println();


        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Chose one of the Truck: ");
            int a = scanner.nextInt();

            for (Truck t : trucks) {
                if (a == t.getIdOfTruck()) {
                    System.out.println("-----------------Trucks----------------");
                    System.out.println(" # |  Trucks            |  Driver      |      State      |");
                    System.out.println("---+--------------------+--------------");
                    System.out.print( t.getIdOfTruck());
                    System.out.print("  | "+ t.getNameOfTruck()+"\t\t|");
                    System.out.print("\t\t\t"+ t.getDriverOfTruck());
                    System.out.print("\t|\t"+ t.getState()+"\n");

                    System.out.println("\nPress 1 to change Driver");
                    System.out.println("Press 2 to send to the Route");
                    System.out.println("Press 3 ot send to the Repairing");

                    int input = scanner.nextInt();
                    try {
                        if (input == 1) {
                            Truck.changeDriver(t, drivers);
                            System.out.println("-----------------------------");
                            System.out.println("Driver changed successfully");
                            printAllInformation(trucks, drivers);
                        } else if (input == 2) {
                            Truck.startDriving(t);
                            System.out.println("-----------------------------");
                            System.out.println("You have successfully entered the route");
                            printAllInformation(trucks, drivers);
                        } else if (input == 3) {
                            Truck.startRepair(t);

                            System.out.println("-----------------------------");
                            System.out.println("you have successfully sent for repair");
                            printAllInformation(trucks, drivers);
                        } else {
                            throw new InvalidException();
                        }
                    } catch (InvalidException e) {
                        System.err.println("WE COULD NOT FIND THIS NUMBER");
                    }
                }
            }
        }
    }

    private static void write(String obj) {
        Path write = Paths.get(String.valueOf(WRITE_PATH));
        try {
            Files.writeString(write,obj, StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile() {
        String json = "";
        try {
            FileReader reader = new FileReader(String.valueOf(WRITE_PATH));
            int a;
            while ((a = reader.read()) != -1) {
                json +=(char)a;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static void writeDrivers(String o) {
        Path writeDrivers = Paths.get(String.valueOf(WRITE_PATH_DRIVERS));
        try {
            Files.writeString(writeDrivers,o, StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFileDrivers() {
        String jsonDrivers = "";
        try {
            FileReader readerDrivers = new FileReader(String.valueOf(WRITE_PATH_DRIVERS));
            int b;
            while ((b = readerDrivers.read()) != -1) {
                jsonDrivers += (char) b;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return jsonDrivers;
    }

    public static void printAllInformation(Truck[] trucks, Driver[] drivers) {
        System.out.println("-----------------------------");
        Arrays.stream(trucks).forEach(System.out::println);
        System.out.println("---------------------------------------");
        Arrays.stream(drivers).forEach(System.out::println);
    }
}