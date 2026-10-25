//Walid Halabi 446109612
public abstract class Ride implements IRide
{
    protected int rideId;
    protected String pickupLocation;
    protected String dropoffLocation;
    protected final IDateTime pickupTime;
    protected final IDateTime dropoffTime;
    protected IDriver driver;

    public Ride(int rideId, String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime, String dropoffLocation) 
    {
        this.rideId = rideId;
        this.pickupLocation = pickupLocation;
        this.pickupTime = pickupTime; 
        this.dropoffTime = dropoffTime; 
        this.dropoffLocation = dropoffLocation;
        this.driver = null; 
    }

	public int getRideId()
    {
        return rideId;
    }

	public String getPickupLocation()
    {
        return pickupLocation;
    }

	public void setPickupLocation(String pickupLocation)
    {
        this.pickupLocation = pickupLocation;
    }

	public IDateTime getPickupTime()
    {
        return pickupTime;
    }

	public IDateTime getDropoffTime()
    {
        return dropoffTime;
    }

	public String getDropoffLocation()
    {
        return dropoffLocation;
    }

	public void setDropoffLocation(String dropoffLocation)
    {
        this.dropoffLocation = dropoffLocation;
    }

	public IDriver getDriver()
    {
        return driver;
    }

	public void setDriver(IDriver driver)
    {
        this.driver = driver;
    }

    public abstract boolean hasRider(int riderId);

	@Override
	public String toString()
    {
        return "Ride Id: " + rideId + "\nPickup Location: " + pickupLocation  + "\nDropOff Location: " + dropoffLocation + "\nPickup Time: " + pickupTime + "\nDropOff Time: " + dropoffTime + "\nDriver: " + driver;
    }

	@Override
	public int compareTo(IRide other)
    {
        return pickupLocation.compareTo(other.getPickupLocation());
    }
}
