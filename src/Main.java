
import java.util.Scanner;

public class Main {
    public static final int MENU_FIRST_OPTION = 1;
    public static final int MENU_SECOND_OPTION = 2;
    public static final int MENU_THIRD_OPTION = 3;

    public static int menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(MENU_FIRST_OPTION + ": Create a new account \n" +
                MENU_SECOND_OPTION + ": Login to an existing account \n" +
                MENU_THIRD_OPTION + ": End the program \n" + "Enter your answer: ");
        int userInput = scanner.nextInt();
        return userInput;
    }

    public static void main(String[] args) {
        int userInput;
        RealEstate account = new RealEstate();
        do {
            userInput = menu();
            switch (userInput) {
                case MENU_FIRST_OPTION: {
                    account.createUser();
                    break;
                }
                case MENU_SECOND_OPTION: {
                    account.login();
                    break;
                }
                case MENU_THIRD_OPTION:
                    break;
            }

        } while (userInput != MENU_THIRD_OPTION);
    }
}

