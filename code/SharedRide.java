//Walid Halabi 44609612
public class SharedRide extends Ride implements ISharedRide
{
    private RiderList participantList;

    public SharedRide(int rideId, String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime, String dropoffLocation, RiderList participantList)//O(1)
    {
        super(rideId, pickupLocation, pickupTime, dropoffTime, dropoffLocation);
        this.participantList = participantList;
    }

    public SharedRide(int rideId, String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime, String dropoffLocation)//O(1)
    {
        super(rideId, pickupLocation, pickupTime, dropoffTime, dropoffLocation);
        participantList = new RiderList();
    }

    public boolean hasRider(int riderId)//O(p)
    {
        return participantList.findById(riderId) != null;
    }

    public LinkedList<IRider> getParticipants()//O(p)
    {
        return participantList.getAll();
    }

    public boolean addParticipant(IRider rider)//O(p)
    {
        return participantList.add(rider);
    }

    public boolean removeParticipantById(int riderId)//O(p)
    {
        return participantList.removeById(riderId);
    }

    public boolean isEmpty()//O(1)
    {
        return participantList.size() <= 0;
    }

    //HELPER
    public boolean hasRider(String name)//O(p)
    {
        LinkedList<IRider> nameList = participantList.findByName(name);
        
        return !nameList.empty();
    }
}
