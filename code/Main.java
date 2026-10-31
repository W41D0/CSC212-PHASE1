//Bassam Faisal alzahrani 446106881
import java.util.Scanner;

public class Main {
    public static void main(String[] args){//O(n^2)
        Scanner input = new Scanner(System.in);
        RideSharingSystem system = new RideSharingSystem();

        // Load the CSV files
        boolean ridersLoaded = system.loadRidersFromCSV("riders_100.csv");
        boolean driversLoaded = system.loadDriversFromCSV("drivers_30.csv");
        boolean ridesLoaded = system.loadRidesFromCSV("rides_40.csv");

        // Check loading results
        if (ridersLoaded && driversLoaded && ridesLoaded)
            System.out.println("All files loaded successfully.");
        else
            System.out.println("Some files failed to load. Data may be incomplete.");

        int choice = -1;

        while (choice != 0) {

            // Show the menu
            printMenu();

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(input.nextLine());

                switch (choice) {

                    case 1:
                        // List all riders
                        printList(system.getAllRiders());
                        break;

                    case 2:
                        // Search riders by city
                        System.out.print("Enter home city: ");
                        String city = input.nextLine();

                        printList(system.searchRidersByHomeCity(city));
                        break;

                    case 3:
                        // Search riders by name
                        System.out.print("Enter rider name: ");
                        String name = input.nextLine();

                        printList(system.searchRidersByName(name));
                        break;

                    case 4:
                        // Add a rider
                        System.out.print("Enter rider ID: ");
                        int riderId = Integer.parseInt(input.nextLine());

                        System.out.print("Enter name: ");
                        String riderName = input.nextLine();

                        System.out.print("Enter email: ");
                        String email = input.nextLine();

                        System.out.print("Enter phone number: ");
                        String riderPhone = input.nextLine();

                        System.out.print("Enter home city: ");
                        String homeCity = input.nextLine();

                        Rider rider = new Rider(riderId, riderName,
                                email, riderPhone, homeCity);

                        if (system.addRider(rider))
                            System.out.println("Rider added successfully.");
                        else
                            System.out.println("Failed to add rider.");

                        break;

                    case 5:
                        // List all drivers
                        printList(system.getAllDrivers());
                        break;

                    case 6:
                        // Search drivers by vehicle type
                        System.out.println("SEDAN, LUXURY_SEDAN, SUV, VAN");
                        System.out.print("Enter vehicle type: ");

                        VehicleType type = VehicleType.valueOf(input.nextLine());

                        printList(system.searchDriversByVehicleType(type));
                        break;

                    case 7:
                        // Add a driver
                        System.out.print("Enter driver ID: ");
                        int driverId = Integer.parseInt(input.nextLine());

                        System.out.print("Enter name: ");
                        String driverName = input.nextLine();

                        System.out.print("Enter phone number: ");
                        String driverPhone = input.nextLine();

                        System.out.print("Enter vehicle plate: ");
                        String plate = input.nextLine();

                        System.out.println("SEDAN, LUXURY_SEDAN, SUV, VAN");
                        System.out.print("Enter vehicle type: ");

                        VehicleType driverType =
                                VehicleType.valueOf(input.nextLine());

                        Driver driver = new Driver(driverId, driverName, driverPhone, plate, driverType);

                        if (system.addDriver(driver))
                            System.out.println("Driver added successfully.");
                        else
                            System.out.println("Failed to add driver.");

                        break;

                    case 8:
                        // List all rides
                        printList(system.getAllRidesAlphabetically());
                        break;

                    case 9:
                        // Search rides by pickup location
                        System.out.print("Enter pickup location: ");
                        String location = input.nextLine();

                        printList(system.searchRidesByPickupLocation(location));
                        break;

                    case 10:
                        // Search rides by rider name
                        System.out.print("Enter rider name: ");
                        String searchName = input.nextLine();

                        printList(system.searchRidesByRiderName(searchName));
                        break;

                    case 11:
                        // List shared ride participants
                        System.out.print("Enter pickup location: ");
                        String pickup = input.nextLine();

                        printList(system.getSharedRideParticipants(pickup));
                        break;

                    case 12:
                        // Add a ride
                        System.out.print("Enter ride type (PRIVATE/SHARED): ");
                        String rideType = input.nextLine();

                        if (!rideType.equals("PRIVATE") && !rideType.equals("SHARED")) {
                            System.out.println("Invalid ride type.");
                            break;
                        }

                        System.out.print("Enter pickup location: ");
                        String pickupLocation = input.nextLine();

                        System.out.print("Enter pickup time (MM/DD/YYYY HH:MM): ");
                        IDateTime pickupTime = readDateTime(input.nextLine());

                        System.out.print("Enter dropoff time (MM/DD/YYYY HH:MM): ");
                        IDateTime dropoffTime = readDateTime(input.nextLine());

                        System.out.print("Enter dropoff location: ");
                        String dropoffLocation = input.nextLine();

                        System.out.print("Enter driver ID: ");
                        int rideDriverId = Integer.parseInt(input.nextLine());

                        boolean added = false;

                        if (rideType.equals("PRIVATE")) {

                            System.out.print("Enter rider ID: ");
                            int privateRiderId = Integer.parseInt(input.nextLine());

                            added = system.schedulePrivateRide(pickupLocation, pickupTime, dropoffTime, dropoffLocation, privateRiderId, rideDriverId);
                        }
                        else {

                            System.out.print("Enter rider IDs separated by ; : ");
                            String[] ids = input.nextLine().split(";", -1);

                            if (ids.length < 2) {
                                System.out.println("Shared ride needs multiple riders.");
                                break;
                            }

                            int[] riderIds = new int[ids.length];

                            for (int i = 0; i < ids.length; i++) {
                                riderIds[i] = Integer.parseInt(ids[i]);
                            }

                            added = system.scheduleSharedRide(pickupLocation, pickupTime, dropoffTime, dropoffLocation,  riderIds, rideDriverId);
                        }

                        if (added)
                            System.out.println("Ride added successfully.");
                        else
                            System.out.println("Failed to add ride.");

                        break;

                    case 0:
                        // Exit
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }

            } catch (IllegalArgumentException e) {
                // Handle invalid input
                System.out.println("Invalid input. Try again.");
            }
        }

        input.close();
    }

    // Print the menu
    public static void printMenu() {//O(1)

        System.out.println("\n===== Ride Sharing System =====");

        System.out.println("1. List all riders.");
        System.out.println("2. Search for riders by home city.");
        System.out.println("3. Search for riders by name.");
        System.out.println("4. Add a rider.");
        System.out.println("5. List all drivers.");
        System.out.println("6. Search for drivers by vehicle type.");
        System.out.println("7. Add a driver.");
        System.out.println("8. List all rides alphabetically by pickup location.");
        System.out.println("9. Search for rides by pickup location.");
        System.out.println("10. Search for rides by rider name.");
        System.out.println("11. List the participants of a shared ride by pickup location.");
        System.out.println("12. Add a ride.");
        System.out.println("0. Exit the program.");
    }

    // Print the elements in a list
    public static <T> void printList(LinkedList<T> list) {//O(n)

        if (list == null || list.empty()) {
            System.out.println("No results found.");
            return;
        }

        list.findFirst();

        while (!list.last()) {
            System.out.println(list.retrieve().toString());
            list.findNext();
        }

        // Print the last element
        System.out.println(list.retrieve().toString());
    }

    // Convert user input to DateTime
    public static IDateTime readDateTime(String text) {//O(1)

        String[] parts = text.split(" ", -1);

        if (parts.length != 2)
            throw new IllegalArgumentException();

        String[] date = parts[0].split("/", -1);
        String[] time = parts[1].split(":", -1);

        if (date.length != 3 || time.length != 2)
            throw new IllegalArgumentException();

        int month = Integer.parseInt(date[0]);
        int day = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);

        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);

        return new DateTime(year, month, day, hour, minute);

    }

}
