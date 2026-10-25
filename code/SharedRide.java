//Walid Halabi 44609612
public class SharedRide extends Ride implements ISharedRide
{
    private RiderList participantList;

    public SharedRide(int rideId, String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime, String dropoffLocation, RiderList participantList)
    {
        super(rideId, pickupLocation, pickupTime, dropoffTime, dropoffLocation);
        this.participantList = participantList;
    }

    public SharedRide(int rideId, String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime, String dropoffLocation)
    {
        super(rideId, pickupLocation, pickupTime, dropoffTime, dropoffLocation);
        participantList = new RiderList();
    }

    public boolean hasRider(int riderId)
    {
        return participantList.findById(riderId) != null;
    }

    public LinkedList<IRider> getParticipants()
    {
        return participantList.getAll();
    }

    public boolean addParticipant(IRider rider)
    {
        return participantList.add(rider);
    }

    public boolean removeParticipantById(int riderId)
    {
        return participantList.removeById(riderId);
    }

    public boolean isEmpty()
    {
        return participantList.size() <= 0;
    }

    //HELPER
    public boolean hasRider(String name)
    {
        LinkedList<IRider> nameList = participantList.findByName(name);
        
        return !nameList.empty();
    }
}
