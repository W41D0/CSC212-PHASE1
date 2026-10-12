public class RiderList implements IRiderList
{
    private LinkedList<IRider> riderList = new LinkedList<>();

    public boolean add(IRider rider)
    {
        //existance check
        if (findById(rider.getId()) != null)
        {
            return false;
        }
        
        //empty logic
        if(riderList.empty())
        {
            riderList.insert(rider);
            return true;
        }

        riderList.findFirst();

        //head logic
        if(riderList.retrieve().getId() > rider.getId())
        {
            riderList.insertHead(rider);
            return true;
        }

        //normal logic
        while (!riderList.last()) 
        {
            riderList.findNext();

            if (riderList.retrieve().getId() > rider.getId())
            {
                riderList.findPrevious();
                riderList.insert(rider);
                return true;
            }
        }

        //if its bigger than everything
        riderList.insertTail(rider);
        return true;

        //head logic
        // if (riderList.getHead().data.getId() == rider.getId())
        //     return false;
        // if(riderList.getHead().data.getId() > rider.getId())
        // {
        //     Node<IRider> tmp = new Node<IRider>(rider);
        //     tmp.next = riderList.getHead();
        //     riderList.setHead(tmp);
        //     riderList.size++;
        //     return true;
        // }

        // //logic
        // Node<IRider> currNode = riderList.getHead();
        // while (currNode.next != null)
        // {
        //     if (currNode.next.data.getId() == rider.getId())
        //         return false;

        //     if (currNode.next.data.getId() > rider.getId())
        //         break;
        //     currNode = currNode.next;
        // }

        // Node<IRider> tmp = new Node<>(rider);
        // tmp.next = currNode.next;
        // currNode.next = tmp;
        // riderList.size++;
        // return true;
        
    }

    public IRider findById(int riderId)
    {
        Node<IRider> currNode = riderList.getHead();

        while (currNode != null) 
        {
            if (currNode.data.getId() > riderId) //list is sorted, so if its bigger, it means we wont find it
                return null;

            if (currNode.data.getId() == riderId)//if found
                return currNode.data;

            currNode = currNode.next;
        }

        return null;
    }

    public LinkedList<IRider> findByName(String fullName)
    {
        LinkedList<IRider> list = new LinkedList<IRider>();
        Node<IRider> currNode = riderList.getHead();

        while (currNode != null) 
        {
            if (currNode.data.getName().equalsIgnoreCase(fullName))//if found
                list.insert(currNode.data);
    
            currNode = currNode.next;
        }

        return list;
    }


    public IRider findByEmail(String email)
    {
        Node<IRider> currNode = riderList.getHead();

        while (currNode != null) 
        {
            if (currNode.data.getEmail().equalsIgnoreCase(email))//if found
                return currNode.data;

            currNode = currNode.next;
        }

        return null;
    }

    public LinkedList<IRider> findByHomeCity(String homeCity)
    {
        LinkedList<IRider> list = new LinkedList<IRider>();
        Node<IRider> currNode = riderList.getHead();

        while (currNode != null) 
        {
            if (currNode.data.getHomeCity().equalsIgnoreCase(homeCity))//if found
                list.insert(currNode.data);
    
            currNode = currNode.next;
        }

        return list;
    }

    public LinkedList<IRider> getAll()
    {
        LinkedList<IRider> list = new LinkedList<IRider>();
        Node<IRider> currNode = riderList.getHead();

        while (currNode != null) 
        {
            list.insert(currNode.data);
            currNode = currNode.next;
        }
        return list;
    }

    boolean removeById(int riderId)
    {
        Node<IRider> currNode = riderList.getHead();

        while (currNode != null) 
        {
            if (currNode.data.getId() == riderId)//if found
            {
                
                return currNode.data;
            }

            if (currNode.data.getId() > riderId) //list is sorted, so if its bigger, it means we wont find it
                return false;


            currNode = currNode.next;
        }

        return null;
    }
    
    // boolean removeByEmail(String email);
    // int removeByName(String fullName);
    // int removeByHomeCity(String homeCity);

    public int size()
    {
        return riderList.size;
    }
}
