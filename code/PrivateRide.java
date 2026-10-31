//Walid Halabi 44609612
public class PrivateRide extends Ride implements IPrivateRide
{
    private IRider rider;

    public PrivateRide(int rideId, String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime, String dropoffLocation, IRider rider)//O(1)
    {
        super(rideId, pickupLocation, pickupTime, dropoffTime, dropoffLocation);
        this.rider = rider;
    }

    public boolean hasRider(int riderId)//O(1)
    {
        return rider.getId() == riderId;
    }

    public IRider getRider()//O(1)
    {
        return rider;
    }

    public void setRider(IRider rider)//O(1)
    {
        this.rider = rider;
    }

    //HELPER
    public boolean hasRider(String name)//O(1)
    {
        return rider.getName().equals(name);
    }
}
