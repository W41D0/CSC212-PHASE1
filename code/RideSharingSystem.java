//Bassam Faisal Alzahrani 446106881
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class RideSharingSystem implements IRideSharingSystem {
    private DriverList drivers;
    private RiderList riders;
    private RideList rides;
    public RideSharingSystem(){
        drivers = new DriverList();
        riders = new RiderList();
        rides = new RideList();
        
    }

     // looks for driver by id
    public IDriver searchDriverById(int driverId){
       return drivers.findById(driverId);
    }

     //Searches for a driver by vehicle plate.
    public IDriver searchDriverByVehiclePlate(String vehiclePlate){
       return drivers.findByVehiclePlate(vehiclePlate);
    }
     
    //Returns all drivers stored in the system.
    public LinkedList<IDriver> getAllDrivers(){
        return drivers.getAll();
    }

    //Searches for all drivers with the specified vehicle type.
    public LinkedList<IDriver> searchDriversByVehicleType(VehicleType vehicleType){
        return drivers.findByVehicleType(vehicleType);
    }

    /**
     * Adds a driver to the system after enforcing uniqueness by ID and by
     * vehicle plate number (no two drivers may share the same plate).
     * Returns true if added; false if duplicate ID, duplicate plate, or invalid
     */
     public boolean addDriver(IDriver driver){
        return drivers.add(driver);
     }

     //Searches for a rider by ID.
    public IRider searchRiderById(int riderId){
        return riders.findById(riderId);
    }

     //Searches for a rider by email.
    public IRider searchRiderByEmail(String email){
        return riders.findByEmail(email);
    }
    //Searches for all riders whose full name exactly matches the given name.
    public LinkedList<IRider> searchRidersByName(String fullName){
        return riders.findByName(fullName);
    }

    //Searches for all riders from the specified home city.
    public LinkedList<IRider> searchRidersByHomeCity(String homeCity){
        return riders.findByHomeCity(homeCity);
    }

     //Returns all riders stored in the system.
    public LinkedList<IRider> getAllRiders(){
        return riders.getAll();
    }

    //Adds a rider to the system after enforcing uniqueness by ID. Returns true if added; false if duplicate ID or invalid
    public boolean addRider(IRider rider){
        if (rider == null)
            return false;
        else return riders.add(rider);
    }

    //Searches for all rides whose pickup location matches the given location (may return multiple if pickup locations repeat).
    public LinkedList<IRide> searchRidesByPickupLocation(String pickupLocation){
        return rides.findByPickupLocation(pickupLocation);
    }

    //Searches for all rides that involve a rider with the given name.
    public LinkedList<IRide> searchRidesByRiderName(String riderName){
        return rides.findByRiderName(riderName);
    }

    //Returns all rides (private and shared) alphabetically ordered by pickup location.
    public LinkedList<IRide> getAllRidesAlphabetically(){
        return rides.getAllAlphabetically();
    }

    //Returns all riders registered on a shared ride identified by its pickup location.
    public LinkedList<IRider> getSharedRideParticipants(String pickupLocation){
        LinkedList<IRide> sameRide = rides.findByPickupLocation(pickupLocation);//create a list with rides already stored with the pickuplocation
        LinkedList<IRider> tmp = new LinkedList<>();
        if (sameRide.empty())
            return tmp;

        sameRide.findFirst();//current = head
        while(!sameRide.last()){

            if(sameRide.retrieve() instanceof SharedRide sharedRide){//checks if the current is a sharedRide or privateRide
                
                LinkedList<IRider> a = sharedRide.getParticipants();

                if (!a.empty()){
                    a.findFirst();

                    while (!a.last()) {
                        tmp.insert(a.retrieve());
                        a.findNext();
                    }
                    tmp.insert(a.retrieve());
                }
            }

            sameRide.findNext();
        }//last check for sameRide
        if(sameRide.retrieve() instanceof SharedRide sharedRide){
                LinkedList<IRider> a = sharedRide.getParticipants();

                if (!a.empty()){
                    a.findFirst();

                    while (!a.last()) {
                        tmp.insert(a.retrieve());
                        a.findNext();
                    }
                    tmp.insert(a.retrieve());
                }
        }
        return tmp;
    }

    // Loads drivers from a CSV file.
	// Returns true if loading succeeds; false otherwise.
	public boolean loadDriversFromCSV(String driversFilePath){
        Scanner input = null;//so i can use it outside of try block
        try {
            File file = new File(driversFilePath);//open the drivers file
            input = new Scanner(file);
            while(input.hasNextLine()){//read the file lines

                String Line = input.nextLine();
                String[] data = Line.split(",", -1);

                if (data.length != 5){//check its field
                    return false;
                }
                //get driver info
                int id = Integer.parseInt(data[0].trim());//change string to int 
                String name = data[1];
                String phone = data[2];
                String plate = data[3];
                VehicleType type = VehicleType.valueOf(data[4]);

                Driver driver = new Driver(id, name, phone, plate, type);

                if (!addDriver(driver))
                    return false;
            }

            if (input.ioException() != null)//check read errors
                return false;

            return true;

        } catch (FileNotFoundException e) {
            return false;
        } catch (IllegalArgumentException e){
            return false;
        } finally {//close file 
            if (input !=null)
                input.close();
        }
    }

    // Loads riders from a CSV file.
	// Returns true if loading succeeds; false otherwise.
	public boolean loadRidersFromCSV(String ridersFilePath){
        if(ridersFilePath == null)
            return false;

         Scanner input = null;//so i can use it outside of try block
        try {
            File file = new File(ridersFilePath);//open the riders file
            input = new Scanner(file);
            while(input.hasNextLine()){//read the file lines

                String Line = input.nextLine();
                String[] data = Line.split(",", -1);

                if (data.length != 5){//check its field
                    return false;
                }
                //get rider info
                int id = Integer.parseInt(data[0]);//change string to int 
                String name = data[1];
                String email = data[2];
                String phone = data[3];
                String city = data[4];

                Rider rider = new Rider(id, name, email, phone, city);
                if (!addRider(rider))
                    return false;
            }

            if (input.ioException() != null)//check read errors
                return false;

            return true;

        } catch (FileNotFoundException e) {
            return false;
        } catch (IllegalArgumentException e){
            return false;
        } finally {//close file 
            if (input !=null)
                input.close();
        }

    }

    /**
     * Removes a driver and performs cascade deletion of their assigned rides:
     * - All private rides assigned to the driver are deleted.
     * - All shared rides assigned to the driver are deleted.
     * It returns true if the driver was found and removed; false otherwise
     */
    public boolean removeDriver(int driverId){
        if (drivers.findById(driverId) == null){//check if theres driver
            return false;
        }
        LinkedList<IRide> allRides = rides.getAllAlphabetically();
        if (!allRides.empty()){
            allRides.findFirst();
            while(!allRides.last()){

                if(allRides.retrieve().getDriver() != null && allRides.retrieve().getDriver().getId() == driverId){
                    rides.removeRideById(allRides.retrieve().getRideId());//remove driver ride
                }

                allRides.findNext();
            }
            if(allRides.retrieve().getDriver() != null && allRides.retrieve().getDriver().getId() == driverId){
                    rides.removeRideById(allRides.retrieve().getRideId());
                }
        }
        //remove driver
        return drivers.removeById(driverId);
    }

    

    public boolean removeRider(int riderId) {

         // Check rider exists
        if (riders.findById(riderId) == null)
            return false;

        LinkedList<IRide> allRides = rides.getAllAlphabetically();

        if (!allRides.empty()) {
           allRides.findFirst();

            while (!allRides.last()) {

               IRide ride = allRides.retrieve();

               // Check shared rides
               if (ride instanceof SharedRide sharedRide) {
                   if (sharedRide.hasRider(riderId)) {
                       sharedRide.removeParticipantById(riderId);

                        if (sharedRide.isEmpty())
                          rides.removeRideById(ride.getRideId());
                    }
                }

                // Check private rides
                else if (ride instanceof PrivateRide privateRide) {
                if (privateRide.hasRider(riderId))
                    rides.removeRideById(ride.getRideId());
                }

                allRides.findNext();
            }

           // Check the last ride
           IRide ride = allRides.retrieve();

           if (ride instanceof SharedRide sharedRide) {
              if (sharedRide.hasRider(riderId)) {
                  sharedRide.removeParticipantById(riderId);

                   if (sharedRide.isEmpty())
                       rides.removeRideById(ride.getRideId());
                }
            }
            else if (ride instanceof PrivateRide privateRide) {
                    if (privateRide.hasRider(riderId))
                       rides.removeRideById(ride.getRideId());
                }
        }

        // Remove the rider
        return riders.removeById(riderId);
    }

    
@Override
public boolean schedulePrivateRide(String pickupLocation,
        IDateTime pickupTime, IDateTime dropoffTime,
        String dropoffLocation, int riderId, int driverId) {

    // Check if rider exists
    IRider rider = riders.findById(riderId);
    if (rider == null)
        return false;

    // Check if driver exists
    IDriver driver = drivers.findById(driverId);
    if (driver == null)
        return false;

    // Check the time
    if (pickupTime == null || dropoffTime == null)
        return false;

    if (pickupLocation == null || dropoffLocation == null)
        return false;

    if (pickupTime.compareTo(dropoffTime) >= 0)
        return false;

    // Get all rides
    LinkedList<IRide> allRides = rides.getAllAlphabetically();
    int newId = 1;

    if (!allRides.empty()) {
        allRides.findFirst();

        while (!allRides.last()) {

            IRide ride = allRides.retrieve();

            // Find a new ride ID
            if (ride.getRideId() >= newId)
                newId = ride.getRideId() + 1;

            // Check rider and driver
            boolean sameRider = ride.hasRider(riderId);
            boolean sameDriver = ride.getDriver() != null &&
            ride.getDriver().getId() == driverId;

            // Check time conflict
            if (sameRider || sameDriver) {
                if (pickupTime.compareTo(ride.getDropoffTime()) < 0 && dropoffTime.compareTo(ride.getPickupTime()) > 0)
                    return false;
            }

            allRides.findNext();
        }

        // Check the last ride
        IRide ride = allRides.retrieve();

        if (ride.getRideId() >= newId)
            newId = ride.getRideId() + 1;

        boolean sameRider = ride.hasRider(riderId);
        boolean sameDriver = ride.getDriver() != null &&
                ride.getDriver().getId() == driverId;

        if (sameRider || sameDriver) {
            if (pickupTime.compareTo(ride.getDropoffTime()) < 0 &&
                dropoffTime.compareTo(ride.getPickupTime()) > 0)
                return false;
        }
    }

    // Create the private ride
    PrivateRide newRide = new PrivateRide(newId,
            pickupLocation, pickupTime, dropoffTime,
            dropoffLocation, rider);

    // Assign the driver
    newRide.setDriver(driver);

    // Add the ride
    return rides.addRide(newRide);
}


@Override
public boolean scheduleSharedRide(String pickupLocation,
        IDateTime pickupTime, IDateTime dropoffTime,
        String dropoffLocation, int[] riderIds, int driverId) {

    // Check if driver exists
    IDriver driver = drivers.findById(driverId);
    if (driver == null)
        return false;

    // Check the riders
    if (riderIds == null || riderIds.length == 0)
        return false;

    IRider[] rideRiders = new IRider[riderIds.length];

    for (int i = 0; i < riderIds.length; i++) {

        rideRiders[i] = riders.findById(riderIds[i]);

        if (rideRiders[i] == null)
            return false;

        // Check duplicate riders
        for (int j = 0; j < i; j++) {
            if (riderIds[i] == riderIds[j])
                return false;
        }
    }

    // Check the time
    if (pickupTime == null || dropoffTime == null)
        return false;

    if (pickupLocation == null || dropoffLocation == null)
        return false;

    if (pickupTime.compareTo(dropoffTime) >= 0)
        return false;

    // Get all rides
    LinkedList<IRide> allRides = rides.getAllAlphabetically();
    int newId = 1;

    if (!allRides.empty()) {
        allRides.findFirst();

        while (!allRides.last()) {

            IRide ride = allRides.retrieve();

            // Find a new ride ID
            if (ride.getRideId() >= newId)
                newId = ride.getRideId() + 1;

            // Check the driver
            boolean sameDriver = ride.getDriver() != null &&
                    ride.getDriver().getId() == driverId;

            // Check all riders
            boolean sameRider = false;

            for (int i = 0; i < riderIds.length; i++) {
                if (ride.hasRider(riderIds[i]))
                    sameRider = true;
            }

            // Check time conflict
            if (sameDriver || sameRider) {
                if (pickupTime.compareTo(ride.getDropoffTime()) < 0 &&
                    dropoffTime.compareTo(ride.getPickupTime()) > 0)
                    return false;
            }

            allRides.findNext();
        }

        // Check the last ride
        IRide ride = allRides.retrieve();

        if (ride.getRideId() >= newId)
            newId = ride.getRideId() + 1;

        boolean sameDriver = ride.getDriver() != null &&
                ride.getDriver().getId() == driverId;

        boolean sameRider = false;

        for (int i = 0; i < riderIds.length; i++) {
            if (ride.hasRider(riderIds[i]))
                sameRider = true;
        }

        if (sameDriver || sameRider) {
            if (pickupTime.compareTo(ride.getDropoffTime()) < 0 &&
                dropoffTime.compareTo(ride.getPickupTime()) > 0)
                return false;
        }
    }

    // Create the shared ride
    SharedRide newRide = new SharedRide(newId,
            pickupLocation, pickupTime, dropoffTime,
            dropoffLocation);

    // Add the riders
    for (int i = 0; i < rideRiders.length; i++) {
        if (!newRide.addParticipant(rideRiders[i]))
            return false;
    }

    // Assign the driver
    newRide.setDriver(driver);

    // Add the ride
    return rides.addRide(newRide);
}


@Override
public boolean loadRidesFromCSV(String ridesFilePath) {

    if (ridesFilePath == null)
        return false;

    Scanner input = null;

    try {
        // Open the rides file
        File file = new File(ridesFilePath);
        input = new Scanner(file);

        // Read each line
        while (input.hasNextLine()) {

            String line = input.nextLine();
            String[] data = line.split(",", -1);

            // Check the number of fields
            if (data.length != 7)
                return false;

            // Get ride information
            String type = data[0];
            String pickupLocation = data[1];
            String dropoffLocation = data[4];
            int driverId = Integer.parseInt(data[5]);

            // Get pickup date and time
            String[] pickup = data[2].split(" ", -1);
            if (pickup.length != 2)
                return false;

            String[] pickupDate = pickup[0].split("/", -1);
            String[] pickupClock = pickup[1].split(":", -1);

            if (pickupDate.length != 3 || pickupClock.length != 2)
                return false;

            IDateTime pickupTime = new DateTime(
                Integer.parseInt(pickupDate[2]),
                Integer.parseInt(pickupDate[0]),
                Integer.parseInt(pickupDate[1]),
                Integer.parseInt(pickupClock[0]),
                Integer.parseInt(pickupClock[1])
            );

            // Get dropoff date and time
            String[] dropoff = data[3].split(" ", -1);
            if (dropoff.length != 2)
                return false;

            String[] dropoffDate = dropoff[0].split("/", -1);
            String[] dropoffClock = dropoff[1].split(":", -1);

            if (dropoffDate.length != 3 || dropoffClock.length != 2)
                return false;

            IDateTime dropoffTime = new DateTime(
                Integer.parseInt(dropoffDate[2]),
                Integer.parseInt(dropoffDate[0]),
                Integer.parseInt(dropoffDate[1]),
                Integer.parseInt(dropoffClock[0]),
                Integer.parseInt(dropoffClock[1])
            );

            // Check private ride
            if (type.equals("PRIVATE")) {

                int riderId = Integer.parseInt(data[6]);

                if (!schedulePrivateRide(pickupLocation,
                        pickupTime, dropoffTime,
                        dropoffLocation, riderId, driverId))
                    return false;
            }

            // Check shared ride
            else if (type.equals("SHARED")) {

                String[] ids = data[6].split(";", -1);

                if (ids.length < 2)
                    return false;

                int[] riderIds = new int[ids.length];

                for (int i = 0; i < ids.length; i++) {
                    riderIds[i] = Integer.parseInt(ids[i]);
                }

                if (!scheduleSharedRide(pickupLocation,
                        pickupTime, dropoffTime,
                        dropoffLocation, riderIds, driverId))
                    return false;
            }

            else {
                return false;
            }
        }

        // Check errors
        if (input.ioException() != null)
            return false;

        return true;

    } catch (FileNotFoundException e) {
        return false;

    } catch (IllegalArgumentException e) {
        return false;

    } finally {
        // Close the file
        if (input != null)
            input.close();
    }
}



}
