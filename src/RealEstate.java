import java.util.Locale;
import java.util.Scanner;

public class RealEstate {
    public static final String CHARS_IN_PASSWORD = "$%_";
    public static final int LENGTH_PHONE_NUMBER = 10;
    public static final String AREA_CODE = "05";
    public static final String IS_MEDIATOR = "yes";
    public static final String REGULAR_USER = "no";
    public static final int ADDRESSES_LENGTH = 10;
    public static final int FIRST_OPTION_FOR_USER = 1;
    public static final int SECOND_OPTION_FOR_USER = 2;
    public static final int THIRD_OPTION_FOR_USER = 3;
    public static final int FOURTH_OPTION_FOR_USER = 4;
    public static final int FIFTH_OPTION_FOR_USER = 5;
    public static final int SIXTH_OPTION_FOR_USER = 6;
    public static final int MAX_POST_REGULAR_USER = 3;
    public static final int MAX_POST_MEDIATOR = 10;
    public static final int REGULAR_APARTMENT = 1;
    public static final int PENTHOUSE = 2;
    public static final int PRIVATE_HOUSE = 3;
    public static final String FOR_RENT = "yes";
    public static final String NOT_FOR_RENT = "no";


    private User[] users;
    private Property[] properties;
    private Address[] addresses;

    public RealEstate() {
        this.users = new User[0];
        this.properties = new Property[0];
        this.addresses = new Address[ADDRESSES_LENGTH];
        this.addresses[0] = new Address("Ashdod", "Cinor");
        this.addresses[1] = new Address("Ashdod", "jo amar");
        this.addresses[2] = new Address("Ashdod", "Ben David");
        this.addresses[3] = new Address("Ashdod", "Arik Einstein");
        this.addresses[4] = new Address("Ashkelon", "Avraham Stern");
        this.addresses[5] = new Address("Ashkelon", "Eli Cohen");
        this.addresses[6] = new Address("Ashkelon", "Nurit");
        this.addresses[7] = new Address("Tel-Aviv", "Ben Gurion");
        this.addresses[8] = new Address("Tel-Aviv", "Weizmann");
        this.addresses[9] = new Address("Tel-Aviv", "bar-Lev");
    }

    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        String username;
        boolean isUsernameExist;
        String userPassword;
        boolean isStrongPassword;
        String userPhoneNumber;
        boolean isValidPhoneNumber;
        boolean isMediator;
        do {
            System.out.println("Enter new username: ");
            username = scanner.nextLine();
            isUsernameExist = usernameExist(username);
        } while (isUsernameExist);
        do {
            System.out.println("Enter a strong password:  \n" +
                    "The password must contain one digit and must contain one of that 3 chars: $,%,_ ");
            userPassword = scanner.nextLine();
            isStrongPassword = strongPassword(userPassword);
        } while (!isStrongPassword);
        do {
            System.out.println("Enter israeli number: ");
            userPhoneNumber = scanner.nextLine();
            isValidPhoneNumber = phoneNumber(userPhoneNumber);
        } while (!isValidPhoneNumber);

        isMediator = isMediator();
        addNewUser(username, userPassword, userPhoneNumber, isMediator);
        System.out.println("User created successfully :)");
    }

    public boolean usernameExist(String username) {
        boolean hasThisUsername = false;
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].getUsername().equals(username)) {
                hasThisUsername = true;
                System.out.println("That username is exist \n");
                break;
            }
        }
        return hasThisUsername;
    }

    public boolean strongPassword(String userPassword) {
        boolean hasCharInPassword = false;
        boolean hasDigitInPassword = false;
        boolean isStrongPassword = false;
        for (int i = 0; i < userPassword.length(); i++) {
            if (Character.isDigit(userPassword.charAt(i))) {
                hasDigitInPassword = true;
                break;
            }
        }
        for (int j = 0; j < CHARS_IN_PASSWORD.length(); j++) {
            for (int i = 0; i < userPassword.length(); i++) {
                if (CHARS_IN_PASSWORD.charAt(j) == userPassword.charAt(i)) {
                    hasCharInPassword = true;
                    break;
                }
            }
        }
        if (hasCharInPassword && hasDigitInPassword) {
            isStrongPassword = true;
        } else
            System.out.println("The password is not strong");
        return isStrongPassword;
    }

    public boolean phoneNumber(String phoneNumber) {
        int counterValidNumber = 0;
        int counterAreaCodeNum = 0;
        boolean validLength = false;
        boolean validAreaCode = false;
        boolean validPhoneNumber = false;
        if (phoneNumber.length() != LENGTH_PHONE_NUMBER) {
            System.out.println("Invalid value");
        } else {
            for (int i = 0; i < phoneNumber.length(); i++) {
                if (Character.isDigit(phoneNumber.charAt(i)))
                    counterValidNumber++;
            }
            if (counterValidNumber == LENGTH_PHONE_NUMBER)
                validLength = true;
            for (int i = 0; i < AREA_CODE.length(); i++) {
                if (phoneNumber.charAt(i) == AREA_CODE.charAt(i))
                    counterAreaCodeNum++;
            }
            if (counterAreaCodeNum == AREA_CODE.length()) {
                validAreaCode = true;
            }
            if (validLength && validAreaCode)
                validPhoneNumber = true;
            else System.out.println("Invalid value");
        }
        return validPhoneNumber;
    }

    public boolean isMediator() {
        Scanner scanner = new Scanner(System.in);
        boolean mediatorOrRegular = false;
        String mediator;
        do {
            System.out.println("Enter yes or no \n" + "Are you are mediator?");
            mediator = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (mediator.equals(IS_MEDIATOR))
                mediatorOrRegular = true;
            else if (mediator.equals(REGULAR_USER))
                mediatorOrRegular = false;
            else
                System.out.println("Invalid value");
        } while (!mediator.equals(IS_MEDIATOR) && !mediator.equals(REGULAR_USER));
        return mediatorOrRegular;
    }

    private void addNewUser(String username, String password, String phoneNumber, boolean isMediator) {
        User[] copyUsers = new User[this.users.length + 1];
        for (int i = 0; i < this.users.length; i++) {
            copyUsers[i] = this.users[i];
        }
        User userToAdd = new User(username, password, phoneNumber, isMediator);
        copyUsers[this.users.length] = userToAdd;
        this.users = copyUsers;
    }

    public User login() {
        Scanner scanner = new Scanner(System.in);
        User userToCheck = null;
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].getUsername().equals(username) && this.users[i].getPassword().equals(password)) {
                userToCheck = this.users[i];
                break;
            }
        }
        if (userToCheck == null) {
            System.out.println("That user is not exist");
        } else {
            int userChoice;
            userChoice = PrintMenuExistingUser();
            do {
                switch (userChoice) {
                    case FIRST_OPTION_FOR_USER: {
                        postNewProperty(userToCheck);
                        userChoice = PrintMenuExistingUser();
                        break;
                    }
                    case SECOND_OPTION_FOR_USER: {
                        removeProperty(userToCheck);
                        userChoice = PrintMenuExistingUser();
                        break;
                    }
                    case THIRD_OPTION_FOR_USER: {
                        printAllProperties();
                        userChoice = PrintMenuExistingUser();
                        break;
                    }
                    case FOURTH_OPTION_FOR_USER: {
                        printUserProperties(userToCheck);
                        userChoice = PrintMenuExistingUser();
                        break;
                    }
                    case SIXTH_OPTION_FOR_USER: {
                        break;
                    }
                }
            } while (userChoice != SIXTH_OPTION_FOR_USER);
        }
        return userToCheck;
    }

    public int PrintMenuExistingUser() {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        do {
            System.out.println("\n" + FIRST_OPTION_FOR_USER + ": Post a new property. \n" +
                    SECOND_OPTION_FOR_USER + ": Remove property posting. \n" +
                    THIRD_OPTION_FOR_USER + ": View all properties that post \n" +
                    FOURTH_OPTION_FOR_USER + ": View all properties that the user has post \n" +
                    FIFTH_OPTION_FOR_USER + ": Property search according to parameters \n" +
                    SIXTH_OPTION_FOR_USER + ": Logout -> return to the main menu \n" + "Enter your answer: ");
            userChoice = scanner.nextInt();
        } while (userChoice < FIRST_OPTION_FOR_USER || userChoice > SIXTH_OPTION_FOR_USER);
        return userChoice;
    }

    public boolean postNewProperty(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean toPost = true;
        boolean hasCity;
        boolean hasStreet;
        boolean isMediator = user.isMediator();
        String cityChoice;
        String streetChoice;
        int maxOfPost = MAX_POST_REGULAR_USER;
        if (isMediator)
            maxOfPost = MAX_POST_MEDIATOR;
        if (counterProperties(user) < maxOfPost) {
            String[] addressesArray = showCitiesList();
            System.out.println("The cities are: ");
            printStr(addressesArray);
            System.out.print("Enter city from the list: ");
            cityChoice = scanner.nextLine();
            hasCity = isExist(addressesArray, cityChoice);
            if (hasCity) {
                System.out.println("\nThese streets are in " + cityChoice);
                String[] streetNameChoice = showStreetNameList(cityChoice);
                printStr(streetNameChoice);
                System.out.print("Enter street from the list: ");
                streetChoice = scanner.nextLine();
                hasStreet = isExist(streetNameChoice, streetChoice);
                if (hasStreet) {
                    propertyDetails(cityChoice, streetChoice, user);
                    System.out.println("property created successfully :)");
                } else {
                    System.out.println("\n Invalid value");
                    toPost = false;
                }
            } else {
                System.out.println("\n Invalid value");
                toPost = false;
            }
        } else {
            System.out.println("you can't post more than " + maxOfPost);
            toPost = false;
        }
        return toPost;
    }

    private int counterProperties(User user) {
        int counter = 0;
        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i].getUser().equals(user))
                counter++;
        }
        return counter;
    }

    public boolean isExist(String[] str, String value) {
        boolean exist = false;
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals(value)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    public void printStr(String[] str) {
        for (int i = 0; i < str.length; i++)
            System.out.println((i + 1) + ". " + str[i]);
    }

    public String[] showCitiesList() {
        String[] strArray = new String[this.addresses.length];
        int indexCityArray = 0;
        boolean exist = false;
        for (int j = 0; j < this.addresses.length; j++) {
            for (int i = 0; i < j; i++) {
                if ((this.addresses[j].getCity().equals(strArray[i]))) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                strArray[indexCityArray] = this.addresses[j].getCity();
                indexCityArray++;
            } else
                exist = false;
        }
        String[] strArrayWithoutNull = new String[indexCityArray];
        for (int i = 0; i < strArrayWithoutNull.length; i++) {
            strArrayWithoutNull[i] = strArray[i];
        }
        return strArrayWithoutNull;
    }

    public String[] showStreetNameList(String city) {
        String[] address = new String[this.addresses.length];
        int indexAddressList = 0;
        for (int i = 0; i < this.addresses.length; i++) {
            if (this.addresses[i].getCity().equals(city)) {
                address[indexAddressList] = this.addresses[i].getStreetName();
                indexAddressList++;
            }
        }
        String[] addressList = new String[indexAddressList];
        for (int i = 0; i < addressList.length; i++) {
            addressList[i] = address[i];
        }
        return addressList;
    }

    public void propertyDetails(String cityChoice, String streetChoice, User user) {
        Scanner scanner = new Scanner(System.in);
        Address address = new Address(cityChoice, streetChoice);
        int propertyType;
        boolean privateHouse = false;
        int floor = 0;
        int rooms;
        int propertyNumber;
        boolean forRent = false;
        String rent;
        int price;
        do {
            System.out.println("\nPress for: \n" + REGULAR_APARTMENT + ": Regular apartment \n" + PENTHOUSE + ": Penthouse \n" + PRIVATE_HOUSE + ": Private house ");
            propertyType = scanner.nextInt();
        } while (propertyType < REGULAR_APARTMENT || propertyType > PRIVATE_HOUSE);
        if (propertyType != PRIVATE_HOUSE) {
            do {
                System.out.print("\nEnter the floor of the apartment: ");
                floor = scanner.nextInt();
                privateHouse = true;
            } while (!isPositiveNumber(floor));
        }
        do {
            System.out.print("\nEnter the amount of rooms: ");
            rooms = scanner.nextInt();
        } while (!isPositiveNumber(rooms));
        do {
            System.out.print("\nEnter number of house: ");
            propertyNumber = scanner.nextInt();
        } while (!isPositiveNumber(propertyNumber));

        do {
            System.out.println("\nEnter yes or no, \n is for rent? ");
            rent = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (rent.equals(FOR_RENT))
                forRent = true;
            else if (rent.equals(NOT_FOR_RENT))
                forRent = false;
            else
                System.out.println("Invalid value");
        } while (!rent.equals(FOR_RENT) && !rent.equals(NOT_FOR_RENT));

        do {
            System.out.print("\nEnter price: ");
            price = scanner.nextInt();
        } while (!isPositiveNumber(price));
        addProperty(address, rooms, price, propertyType, privateHouse, forRent, propertyNumber, floor, user);
    }

    public boolean isPositiveNumber(int number) {
        boolean positive = false;
        if (number > 0)
            positive = true;
        return positive;
    }

    private void addProperty(Address address, int rooms, int price, int type, boolean privateOrApartment, boolean forRent, int houseNumber, int floor, User user) {
        Property[] copyProperty = new Property[this.properties.length + 1];
        for (int i = 0; i < this.properties.length; i++) {
            copyProperty[i] = this.properties[i];
        }
        Property propertyToAdd = new Property(address, rooms, price, type, privateOrApartment, forRent, houseNumber, floor, user);
        copyProperty[this.properties.length] = propertyToAdd;
        this.properties = copyProperty;
    }

    public void removeProperty(User user) {
        Scanner scanner = new Scanner(System.in);
        Property[] allProperties = new Property[this.properties.length - 1];
        if (counterProperties(user) == 0) {
            System.out.println("Don't have properties to delete");
        } else {
            Property[] propertiesUser = new Property[counterProperties(user)];
            int index = 0;
            int indexPropertyToRemove;
            int indexAll = 0;
            printUserProperties(user);
            for (int i = 0; i < this.properties.length; i++) {
                if (this.properties[i].getUser().getUsername().equals(user.getUsername())) {
                    propertiesUser[index] = new Property(this.properties[i]);
                    index++;
                }
            }
            System.out.println("Enter property number to remove: ");
            indexPropertyToRemove = scanner.nextInt();
            if (indexPropertyToRemove > 1 && indexPropertyToRemove < counterProperties(user) + 1) {
                propertiesUser = removePropertyFromArray(propertiesUser[indexPropertyToRemove - 1], propertiesUser);
                for (int i = 0; i < this.properties.length; i++) {
                    if (!(this.properties[i].equals(propertiesUser[indexPropertyToRemove - 1]))) {
                        allProperties[indexAll] = this.properties[i];
                        indexAll++;
                    }
                }
            }
            this.properties = allProperties;
            System.out.println("The property removed successfully :)");
        }
    }

    private Property[] removePropertyFromArray(Property property, Property[] properties) {
        Property[] newArray = new Property[properties.length - 1];
        int newArrayIndex = 0;
        for (int i = 0; i < properties.length; i++) {
            if (properties[i] != property) {
                newArray[newArrayIndex] = properties[i];
                newArrayIndex++;
            }
        }
        return newArray;
    }

    public void printAllProperties() {
        int index = 1;
        for (int i = 0; i < this.properties.length; i++) {
            System.out.println(index + ". " + this.properties[i].toString());
            index++;
        }
    }

    public void printUserProperties(User user) {
        int index = 1;
        if (counterProperties(user) > 0) {
            System.out.println("The properties of " + user.getUsername() + " are: ");
            for (int i = 0; i < this.properties.length; i++) {
                if (this.properties[i].getUser().getUsername().equals(user.getUsername())) {
                    System.out.println(index + ". " + this.properties[i] + "\n");
                    index++;
                }
            }
        } else
            System.out.println("The user" + user.getUsername() + " don't have properties");
    }

    public Property[] search() {
        Scanner scanner = new Scanner(System.in);
        Property[] propertySearch = new Property[this.properties.length];
        String forRent;
        String type;
        int rooms;
        int minPrice;
        int maxPrice;
        return null;
    }
}
