//Walid Halabi 446109612
public class RideList implements IRideList
{
    LinkedList<IRide> rideList = new LinkedList<IRide>();

    public boolean addRide(IRide ride)//O(n)
    {
        //empty logic
        if(rideList.empty())
        {
            rideList.insert(ride);
            return true;
        }

        //existance check
        if (findRideById(ride.getRideId()) != null)
            return false;

        rideList.findFirst();

        //head logic
        if(ride.compareTo(rideList.retrieve()) < 0)
        {
            rideList.insertHead(ride);
            return true;
        }

        //normal logic
        while (!rideList.last()) 
        {
            rideList.findNext();

            if (ride.compareTo(rideList.retrieve()) < 0)
            {
                rideList.findPrevious();
                rideList.insert(ride);
                return true;
            }
        }

        //last alphabet
        rideList.insertTail(ride);
        return true;
    }

    public boolean removeRideById(int rideId)//O(n)
    {
        //empty logic
        if (rideList.empty())
            return false;
        
        rideList.findFirst();

        //head logic
        if (rideList.retrieve().getRideId() == rideId)
        {
            rideList.removeHead();
            return true;
        } 

        //normal logic
        while (!rideList.last()) 
        {
            rideList.findNext();
            if (rideList.retrieve().getRideId() == rideId)
            {   
                rideList.remove();
                return true;
            }
        }

        //found none
        return false;
    }

    public LinkedList<IRide> getAllAlphabetically()//O(n)
    {
        //its already alphabetical
        LinkedList<IRide> list = new LinkedList<IRide>();
        
        //empty logic
        if (rideList.empty())
            return list;

        rideList.findFirst();

        //head logic
            list.insert(rideList.retrieve());

        //normal logic
        while (!rideList.last())
        {
            rideList.findNext();
            list.insert(rideList.retrieve());
        }

        return list;
    }

    public LinkedList<IRide> findByPickupLocation(String pickupLocation)//O(n)
    {
        LinkedList<IRide> list = new LinkedList<IRide>();

        //empty logic
        if (rideList.empty())
            return list;
        
        rideList.findFirst();

        //head logic
        if (rideList.retrieve().getPickupLocation().equals(pickupLocation))
            list.insert(rideList.retrieve());

        //normal logic
        while (!rideList.last()) 
        {
            rideList.findNext();
            if (rideList.retrieve().getPickupLocation().equals(pickupLocation))
                list.insert(rideList.retrieve());
        }

        return list;
    }

    public LinkedList<IRide> findByRiderName(String riderFullName)//O(n * p)
    {
        LinkedList<IRide> list = new LinkedList<IRide>();

        //empty logic
        if (rideList.empty())
            return list;
        
        rideList.findFirst();

        //head logic
        if(rideList.retrieve() instanceof PrivateRide privateRide)//private ride
        {
            if(privateRide.hasRider(riderFullName))
                list.insert(privateRide);
        }
        else if(rideList.retrieve() instanceof SharedRide sharedRide)//shared ride
        {
            if(sharedRide.hasRider(riderFullName))
                list.insert(sharedRide);
        }

        //normal logic
        while (!rideList.last()) 
        {
            rideList.findNext();

            if(rideList.retrieve() instanceof PrivateRide privateRide)//private ride
            {
                if(privateRide.hasRider(riderFullName))
                    list.insert(privateRide);
            }
            else if(rideList.retrieve() instanceof SharedRide sharedRide)//shared ride
            {
                if(sharedRide.hasRider(riderFullName))
                    list.insert(sharedRide);
            }   
        }

        return list;
    }

    public int size()//O(1)
    {
        return rideList.size();
    }

    //HELPER METHOD
    private IRide findRideById(int rideId)//O(n)
    {
        //empty logic
        if (rideList.empty())
            return null;
        
        rideList.findFirst();

        //head logic
        if (rideList.retrieve().getRideId() == rideId)
            return rideList.retrieve();

        //normal logic
        while (!rideList.last()) 
        {
            rideList.findNext();
            if (rideList.retrieve().getRideId() == rideId)
                return rideList.retrieve();
        }

        //found none
        return null;
    }
}
