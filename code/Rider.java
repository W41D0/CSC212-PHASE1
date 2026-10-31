//Walid Halabi 446109612
public class Rider extends Person implements IRider
{
    String email;
    String homeCity;
    public Rider(int id, String name, String email, String phoneNumber, String homeCity)//O(1)
    {
        super(id, name, phoneNumber);
        this.email = email;
        this.homeCity = homeCity;
    }
    
    public String getEmail()//O(1)
    {
        return email;
    }

    public void setEmail(String email)//O(1)
    {
        this.email = email;
    }


    public String getHomeCity()//O(1)
    {
        return homeCity;
    }

    public void setHomeCity(String homeCity)//O(1)
    {
        this.homeCity = homeCity;
    }

    @Override
    public int compareTo(IRider other)//O(1)
    {
        int compare = getId() - other.getId();
        
        return compare;
    }
}