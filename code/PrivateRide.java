//Walid Halabi 44609612
public class PrivateRide extends Ride implements IPrivateRide
{
    private IRider rider;

    public PrivateRide(int rideId, String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime, String dropoffLocation, IRider rider)
    {
        super(rideId, pickupLocation, pickupTime, dropoffTime, dropoffLocation);
        this.rider = rider;
    }

    public boolean hasRider(int riderId)
    {
        return rider.getId() == riderId;
    }

    public IRider getRider()
    {
        return rider;
    }

    public void setRider(IRider rider)
    {
        this.rider = rider;
    }
}
