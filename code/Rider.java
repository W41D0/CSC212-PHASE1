public class Rider /*extends person*/ implements IRider
{
    String email;
    String homeCity;
    public Rider(int id, String name, String email, String phoneNumber, String homeCity)
    {
        //super(id, name, phoneNumber);
        this.email = email;
        this.homeCity = homeCity;
    }
    
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }


    public String getHomeCity()
    {
        return homeCity;
    }

    public void setHomeCity(String homeCity)
    {
        this.homeCity = homeCity;
    }

    @Override
    public int compareTo(IRider other)
    {
        int compare = getId() - other.getId();
        
        return compare;
    }
}