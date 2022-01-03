
public class User {

    private String username;
    private String password;
    private String phoneNumber;
    private boolean isMediator;

    public User(String username, String password, String phoneNumber, boolean isMediator) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isMediator = isMediator;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isMediator() {
        return isMediator;
    }

    public void setMediator(boolean mediator) {
        isMediator = mediator;
    }

    public String toString() {
        return this.username + " "+ this.phoneNumber + (this.isMediator ? " real estate broker" : ".") + ")";
    }
}
