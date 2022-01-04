import java.util.Objects;

public class Property {

    public static final int REGULAR_APARTMENT = 1;
    public static final int PENTHOUSE = 2;
    public static final int PRIVATE_HOUSE = 3;

    private Address address;
    private int rooms;
    private int price;
    private int type;
    private boolean privateOrApartment;
    private boolean forRent;
    private int houseNumber;
    private int floor;
    private User user;
//    private boolean deleted;

    public Property(Address address, int rooms, int price, int type, boolean privateOrApartment, boolean forRent, int houseNumber, int floor, User user) {
        this.address = address;
        this.rooms = rooms;
        this.price = price;
        this.type = type;
        this.privateOrApartment = privateOrApartment;
        this.forRent = forRent;
        this.houseNumber = houseNumber;
        this.floor = floor;
        this.user = user;
        // this.deleted = false;
    }

    public Property(Property property) {
        this.address = property.getAddress();
        this.rooms = property.getRooms();
        this.price = property.getPrice();
        this.type = property.getType();
        this.privateOrApartment = property.isPrivateOrApartment();
        this.forRent = property.isForRent();
        this.houseNumber = property.getHouseNumber();
        this.floor = property.getFloor();
        this.user = property.getUser();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isPrivateOrApartment() {
        return privateOrApartment;
    }

    public void setPrivateOrApartment(boolean privateOrApartment) {
        this.privateOrApartment = privateOrApartment;
    }

    public boolean isForRent() {
        return forRent;
    }

    public void setForRent(boolean forRent) {
        this.forRent = forRent;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        String str = "";
        switch (this.type) {
            case REGULAR_APARTMENT: {
                str = "Regular apartment";
                break;
            }
            case PENTHOUSE: {
                str = "Penthouse";
                break;
            }
            case PRIVATE_HOUSE: {
                str = "Private house";
                break;
            }
        }
        return (str + "-" + (this.forRent ? "For rent: " : "For sale: ") +
                this.rooms + " rooms" + (this.privateOrApartment ? (" ,floor " + this.floor) : ".") +
                "\nPrice: " + this.price + "$" +
                "\n(Contact info: " + this.user.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Property)) return false;
        Property property = (Property) o;
        return rooms == property.rooms && price == property.price && type == property.type && privateOrApartment == property.privateOrApartment && forRent == property.forRent && houseNumber == property.houseNumber && floor == property.floor && address.equals(property.address) && user.equals(property.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, rooms, price, type, privateOrApartment, forRent, houseNumber, floor, user);
    }
    //    public boolean isDeleted() {
//        return deleted;
//    }
//
//    public void setDeleted(boolean deleted) {
//        this.deleted = deleted;
//    }
}