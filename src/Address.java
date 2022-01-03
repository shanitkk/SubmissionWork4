public class Address {

    private String city;
    private String streetName;

    public Address(String city, String streetName) {
        this.city = city;
        this.streetName = streetName;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String newCity) {
        this.city = newCity;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String newStreetName) {
        this.streetName = newStreetName;
    }

    public String toString() {
        return ( "City: " + this.city + ", address: " + this.streetName);
    }
}
