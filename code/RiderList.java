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

        //head logic
        if (riderList.getHead().data.getId() == rider.getId())
            return false;
        if(riderList.getHead().data.getId() > rider.getId())
        {
            Node<IRider> tmp = new Node<IRider>(rider);
            tmp.next = riderList.getHead();
            riderList.setHead(tmp);
            riderList.size++;
            return true;
        }

        //logic
        Node<IRider> currNode = riderList.getHead();
        while (currNode.next != null)
        {
            if (currNode.next.data.getId() == rider.getId())
                return false;

            if (currNode.next.data.getId() > rider.getId())
                break;
            currNode = currNode.next;
        }

        Node<IRider> tmp = new Node<>(rider);
        tmp.next = currNode.next;
        currNode.next = tmp;
        riderList.size++;
        return true;
    }

    public IRider findById(int riderId)
    {
        //empty logic
        if (riderList.empty())
            return null;

        //normal logic
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
        //empty logic
        if (riderList.empty())
            return list;

        //normal logic
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
        //empty logic
        if (riderList.empty())
            return null;

        //normal logic
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
        //empty logic
        if (riderList.empty())
            return list;

        //normal logic
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
        return riderList;
    }
    // boolean removeById(int riderId);
    // boolean removeByEmail(String email);
    // int removeByName(String fullName);
    // int removeByHomeCity(String homeCity);

    public int size()
    {
        return riderList.size;
    }
}