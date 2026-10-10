public class RiderList extends LinkedList<IRider> implements IRiderList
{
    public boolean add(IRider rider)
    {
        //empty logic
        if(empty())
        {
            insert(rider);
            size++;
            return true;
        }

        //head logic
        if (head.data.getId() == rider.getId())
            return false;
        if(head.data.getId() > rider.getId())
        {
            Node<IRider> tmp = new Node<IRider>(rider);
            tmp.next = head;
            head = tmp;
            size++;
            return true;
        }

        //logic
        Node<IRider> currNode = head;
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
        size++;
        return true;
    }

    // IRider findById(int riderId);
    // LinkedList<IRider> findByName(String fullName);
    // IRider findByEmail(String email);
    // LinkedList<IRider> findByHomeCity(String homeCity);
    // LinkedList<IRider> getAll();
    // boolean removeById(int riderId);
    // boolean removeByEmail(String email);
    // int removeByName(String fullName);
    // int removeByHomeCity(String homeCity);
    public int size()
    {
        return size;
    }
}