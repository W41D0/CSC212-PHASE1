//Walid Halabi 446109612
public abstract class Ride implements IRide
{
    protected int rideId;
    protected String pickupLocation;
    protected String dropoffLocation;
    protected final IDateTime pickupTime;
    protected final IDateTime dropoffTime;
    protected IDriver driver;

    public Ride(int rideId, String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime, String dropoffLocation) //O(1)
    {
        this.rideId = rideId;
        this.pickupLocation = pickupLocation;
        this.pickupTime = pickupTime; 
        this.dropoffTime = dropoffTime; 
        this.dropoffLocation = dropoffLocation;
        this.driver = null; 
    }

	public int getRideId()//O(1)
    {
        return rideId;
    }

	public String getPickupLocation()//O(1)
    {
        return pickupLocation;
    }

	public void setPickupLocation(String pickupLocation)//O(1)
    {
        this.pickupLocation = pickupLocation;
    }

	public IDateTime getPickupTime()//O(1)
    {
        return pickupTime;
    }

	public IDateTime getDropoffTime()//O(1)
    {
        return dropoffTime;
    }

	public String getDropoffLocation()//O(1)
    {
        return dropoffLocation;
    }

	public void setDropoffLocation(String dropoffLocation)//O(1)
    {
        this.dropoffLocation = dropoffLocation;
    }

	public IDriver getDriver()//O(1)
    {
        return driver;
    }

	public void setDriver(IDriver driver)//O(1)
    {
        this.driver = driver;
    }

    public abstract boolean hasRider(int riderId);

	@Override
	public String toString()//O(1)
    {
        return "Ride Id: " + rideId + "\nPickup Location: " + pickupLocation  + "\nDropOff Location: " + dropoffLocation + "\nPickup Time: " + pickupTime + "\nDropOff Time: " + dropoffTime + "\nDriver: " + driver;
    }

	@Override
	public int compareTo(IRide other)//O(1)
    {
        return pickupLocation.compareTo(other.getPickupLocation());
    }
}
