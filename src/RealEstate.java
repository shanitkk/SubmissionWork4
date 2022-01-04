import java.util.Locale;
import java.util.Scanner;

public class RealEstate {
    public static final String CHARS_IN_PASSWORD = "$%_";
    public static final int LENGTH_PHONE_NUMBER = 10;
    public static final String AREA_CODE = "05";
    public static final String IS_MEDIATOR = "yes";
    public static final String REGULAR_USER = "no";
    public static final int ADDRESSES_LENGTH = 10;
    public static final int CREATE_NEW_PROPERTY = 1;
    public static final int REMOVE_PROPERTY = 2;
    public static final int SHOW_ALL_PROPERTIES = 3;
    public static final int SHOW_USER_PROPERTIES = 4;
    public static final int SEARCH_BY_PARAMETER = 5;
    public static final int LOG_OFF = 6;
    public static final int MAX_POST_REGULAR_USER = 3;
    public static final int MAX_POST_MEDIATOR = 10;
    public static final int REGULAR_APARTMENT = 1;
    public static final int PENTHOUSE = 2;
    public static final int PRIVATE_HOUSE = 3;
    public static final String FOR_RENT = "yes";
    public static final String NOT_FOR_RENT = "no";
    public static final int CHOOSE_FOR_RENT = 1;
    public static final int CHOOSE_FOR_SALE = 2;
    public static final int IGNORE = -999;
    public static final int MIN_NUMBER_OF_ROOMS = 0;
    public static final int PRICE_RANGE = 1;
    public static final int THE_LOWEST_PRICE = 0;


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
            System.out.println("Enter strong password:  \n" +
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
                System.out.println("That user name is exist \n");
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
        System.out.println("Enter your user name: ");
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
                    case CREATE_NEW_PROPERTY: {
                        postNewProperty(userToCheck);
                        userChoice = PrintMenuExistingUser();
                        break;
                    }
                    case REMOVE_PROPERTY: {
                        removeProperty(userToCheck);
                        userChoice = PrintMenuExistingUser();
                        break;
                    }
                    case SHOW_ALL_PROPERTIES: {
                        printAllProperties();
                        userChoice = PrintMenuExistingUser();
                        break;
                    }
                    case SHOW_USER_PROPERTIES: {
                        printUserProperties(userToCheck);
                        userChoice = PrintMenuExistingUser();
                        break;
                    }
                    case SEARCH_BY_PARAMETER: {
                        search();
                        userChoice = PrintMenuExistingUser();
                        break;
                    }
                    case LOG_OFF: {
                        break;
                    }
                }
            } while (userChoice != LOG_OFF);
        }
        return userToCheck;
    }

    public int PrintMenuExistingUser() {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        do {
            System.out.println("\n" + CREATE_NEW_PROPERTY + ": Post new property. \n" +
                    REMOVE_PROPERTY + ": Remove property posting. \n" +
                    SHOW_ALL_PROPERTIES + ": View all properties that post \n" +
                    SHOW_USER_PROPERTIES + ": View all properties that the user has post \n" +
                    SEARCH_BY_PARAMETER + ": Property search according to parameters \n" +
                    LOG_OFF + ": Logout -> return to the main menu \n" + "Enter your answer: ");
            userChoice = scanner.nextInt();
        } while (userChoice < CREATE_NEW_PROPERTY || userChoice > LOG_OFF);
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
            System.out.print("Enter city name from the list: ");
            cityChoice = scanner.nextLine();
            hasCity = isExist(addressesArray, cityChoice);
            if (hasCity) {
                System.out.println("\nThese streets are in " + cityChoice);
                String[] streetNameChoice = showStreetNameList(cityChoice);
                printStr(streetNameChoice);
                System.out.print("Enter street name from the list: ");
                streetChoice = scanner.nextLine();
                hasStreet = isExist(streetNameChoice, streetChoice);
                if (hasStreet) {
                    propertyDetails(cityChoice, streetChoice, user);
                    System.out.println("Property created successfully :)");
                } else {
                    System.out.println("\n Invalid value");
                    toPost = false;
                }
            } else {
                System.out.println("\n Invalid value");
                toPost = false;
            }
        } else {
            System.out.println("You can't post more than " + maxOfPost);
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
            System.out.print("\nEnter house number: ");
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
        Property[] propertiesUser;
        int index;
        int indexPropertyToRemove;
        int indexAll;
        Property[] allProperties = new Property[this.properties.length - 1];
        if (counterProperties(user) == 0) {
            System.out.println("Don't have properties to delete");
        } else {
            propertiesUser = new Property[counterProperties(user)];
            index = 0;
            indexAll = 0;
            printUserProperties(user);
            for (int i = 0; i < this.properties.length; i++) {
                if (this.properties[i].getUser().getUsername().equals(user.getUsername())) {
                    propertiesUser[index] = new Property(this.properties[i]);
                    index++;
                }
            }
            do {
                System.out.println("Enter property number to remove: ");
                indexPropertyToRemove = scanner.nextInt() - 1;
            } while (indexPropertyToRemove >= 0 && indexPropertyToRemove <= counterProperties(user));

            propertiesUser = removePropertyFromArray(propertiesUser[indexPropertyToRemove], propertiesUser);
            for (int i = 0; i < this.properties.length; i++) {
                if (!(this.properties[i].equals(propertiesUser[indexPropertyToRemove]))) {
                    allProperties[indexAll] = this.properties[i];
                    indexAll++;
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
        int userChoose;
        Property[] searchArray = new Property[this.properties.length];
        for (int i = 0; i < this.properties.length; i++) {
            searchArray[i] = this.properties[i];
        }
        int minPrice;
        int maxPrice;

        do {
            System.out.println("Press:\n1- For Rent\n2- For Sale\n -999 - Ignore");
            userChoose = scanner.nextInt();
        } while ((userChoose < CHOOSE_FOR_RENT || userChoose > CHOOSE_FOR_SALE) && userChoose != IGNORE);
        if (userChoose == CHOOSE_FOR_RENT) {
            for (int i = 0; i < searchArray.length; i++) {
                if (searchArray[i].isForRent()) {
                    searchArray = removePropertyFromArray(searchArray[i], searchArray);
                    i--;
                }
            }
        } else if (userChoose == CHOOSE_FOR_SALE) {
            for (int i = 0; i < searchArray.length; i++) {
                if (!(searchArray[i].isForRent())) {
                    searchArray = removePropertyFromArray(searchArray[i], searchArray);
                    i--;
                }
            }
        }
        do {
            System.out.println("1- Regular apartment:\n 2- Penthouse:\n 3- Private house\n -999 - Ignore");
            userChoose = scanner.nextInt();
        } while ((userChoose < REGULAR_APARTMENT || userChoose > PRIVATE_HOUSE) && userChoose != IGNORE);
        if (userChoose == REGULAR_APARTMENT) {
            for (int i = 0; i < searchArray.length; i++) {
                if (!(searchArray[i].getType() == REGULAR_APARTMENT)) {
                    searchArray = removePropertyFromArray(searchArray[i], searchArray);
                    i--;
                }
            }
        } else if (userChoose == PENTHOUSE) {
            for (int i = 0; i < searchArray.length; i++) {
                if (!(searchArray[i].getType() == PENTHOUSE)) {
                    searchArray = removePropertyFromArray(searchArray[i], searchArray);
                    i--;
                }
            }
        } else if (userChoose == PRIVATE_HOUSE) {
            for (int i = 0; i < searchArray.length; i++) {
                if (!(searchArray[i].getType() == PRIVATE_HOUSE)) {
                    searchArray = removePropertyFromArray(searchArray[i], searchArray);
                    i--;
                }
            }
        }
        do {
            System.out.println("Enter number of rooms: \n To ignore choose -999");
            userChoose = scanner.nextInt();
        } while (userChoose < MIN_NUMBER_OF_ROOMS && userChoose != IGNORE);
        for (int i = 0; i < searchArray.length; i++) {
            if (!(searchArray[i].getRooms() == userChoose)) {
                searchArray = removePropertyFromArray(searchArray[i], searchArray);
                i--;
            }
        }
        do {
            System.out.println("1- To choose the price range\n -999 - Ignore");
            userChoose = scanner.nextInt();
        } while (userChoose != PRICE_RANGE && userChoose != IGNORE);
        if (userChoose == PRICE_RANGE) {
            do {
                System.out.println("Enter min price: ");
                minPrice = scanner.nextInt();
                System.out.println("Enter max price: ");
                maxPrice = scanner.nextInt();
            } while ((minPrice > maxPrice) || (maxPrice < THE_LOWEST_PRICE || minPrice < THE_LOWEST_PRICE));
            for (int i = 0; i < searchArray.length; i++) {
                if (!(searchArray[i].getPrice() >= minPrice && searchArray[i].getPrice() <= maxPrice)) {
                    searchArray = removePropertyFromArray(searchArray[i], searchArray);
                    i--;
                }
            }
        }
        return searchArray;
    }
}
