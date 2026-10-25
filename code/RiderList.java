//Walid Halabi 446109612
public class RiderList implements IRiderList
{
    private LinkedList<IRider> riderList = new LinkedList<>();

    public boolean add(IRider rider)
    {
        //empty logic
        if(riderList.empty())
        {
            riderList.insert(rider);
            return true;
        }

        //existance check
        if (findByEmail(rider.getEmail()) != null)
            return false;
        if (findById(rider.getId()) != null)
            return false;

        riderList.findFirst();

        //head logic
        if(rider.compareTo(riderList.retrieve()) < 0)
        {
            riderList.insertHead(rider);
            return true;
        }

        //normal logic
        while (!riderList.last()) 
        {
            riderList.findNext();

            if (rider.compareTo(riderList.retrieve()) < 0)
            {
                riderList.findPrevious();
                riderList.insert(rider);
                return true;
            }
        }

        //if its bigger than everything
        riderList.insertTail(rider);
        return true;
    }

    public IRider findById(int riderId)
    {
        //empty logic
        if (riderList.empty())
            return null;
        
        riderList.findFirst();

        //head logic
        if (riderList.retrieve().getId() == riderId)
            return riderList.retrieve();

        //normal logic
        while (!riderList.last()) 
        {
            riderList.findNext();
            if (riderList.retrieve().getId() == riderId)
                return riderList.retrieve();
        }

        //found none
        return null;
    }

    public LinkedList<IRider> findByName(String fullName)
    {
        LinkedList<IRider> list = new LinkedList<IRider>();

        //empty logic
        if (riderList.empty())
            return list;
        
        riderList.findFirst();

        //head logic
        if (riderList.retrieve().getName().equals(fullName))
            list.insert(riderList.retrieve());

        //normal logic
        while (!riderList.last()) 
        {
            riderList.findNext();
            if (riderList.retrieve().getName().equals(fullName))
                list.insert(riderList.retrieve());
        }

        return list;
    }


    public IRider findByEmail(String email)
    {
        //empty logic
        if (riderList.empty())
            return null;
        
        riderList.findFirst();

        //head logic
        if (riderList.retrieve().getEmail().equals(email))
            return riderList.retrieve();

        //normal logic
        while (!riderList.last()) 
        {
            riderList.findNext();
            if (riderList.retrieve().getEmail().equals(email))
                return riderList.retrieve();
        }

        //found none
        return null;
    }

    public LinkedList<IRider> findByHomeCity(String homeCity)
    {
        LinkedList<IRider> list = new LinkedList<IRider>();

        //empty logic
        if (riderList.empty())
            return list;
        
        riderList.findFirst();

        //head logic
        if (riderList.retrieve().getHomeCity().equals(homeCity))
            list.insert(riderList.retrieve());

        //normal logic
        while (!riderList.last()) 
        {
            riderList.findNext();
            if (riderList.retrieve().getHomeCity().equals(homeCity))
                list.insert(riderList.retrieve());
        }

        return list;
    }

    public LinkedList<IRider> getAll()
    {
        LinkedList<IRider> list = new LinkedList<IRider>();
        
        //empty logic
        if (riderList.empty())
            return list;

        riderList.findFirst();

        //head logic
            list.insert(riderList.retrieve());

        //normal logic
        while (!riderList.last())
        {
            riderList.findNext();
            list.insert(riderList.retrieve());
        }

        return list;
    }

    public boolean removeById(int riderId)
    {
        //empty logic
        if (riderList.empty())
            return false;
        
        riderList.findFirst();

        //head logic
        if (riderList.retrieve().getId() == riderId)
        {
            riderList.removeHead();
            return true;
        } 

        //normal logic
        while (!riderList.last()) 
        {
            riderList.findNext();
            if (riderList.retrieve().getId() == riderId)
            {   
                riderList.remove();
                return true;
            }
        }

        //found none
        return false;
    }
    
    public boolean removeByEmail(String email)
    {
        //empty logic
        if (riderList.empty())
            return false;
        
        riderList.findFirst();

        //head logic
        if (riderList.retrieve().getEmail().equals(email))
        {
            riderList.removeHead();
            return true;
        } 

        //normal logic
        while (!riderList.last()) 
        {
            riderList.findNext();
            if (riderList.retrieve().getEmail().equals(email))
            {   
                riderList.remove();
                return true;
            }
        }

        //found none
        return false;
    }

    public int removeByName(String fullName)
    {
        //empty logic
        if (riderList.empty())
            return 0;
        
        int count = 0;
        riderList.findFirst();

        //normal logic
        while (!riderList.last()) 
        {
            if (riderList.retrieve().getName().equals(fullName))
            {   
                riderList.remove();
                count++;
            }
            else
                riderList.findNext();
        }

        //tail logic
        if (riderList.retrieve().getName().equals(fullName))
        {
            riderList.removeTail();
            count++;
        } 

        return count;
    }
    
    public int removeByHomeCity(String homeCity)
    {
        //empty logic
        if (riderList.empty())
            return 0;
        
        int count = 0;
        riderList.findFirst();

        //normal logic
        while (!riderList.last()) 
        {
            if (riderList.retrieve().getHomeCity().equals(homeCity))
            {   
                riderList.remove();
                count++;
            }
            else
                riderList.findNext();
        }

        //tail logic
        if (riderList.retrieve().getHomeCity().equals(homeCity))
        {
            riderList.removeTail();
            count++;
        } 

        return count;
    }

    public int size()
    {
        return riderList.size();
    }
}
