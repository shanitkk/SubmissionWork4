
import java.util.Scanner;

public class Main {
    public static final int CREATE_ACCOUNT = 1;
    public static final int LOG_IN_TO_EXISTS_ACCOUNT = 2;
    public static final int END_THE_PROGRAM = 3;

    public static int menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(CREATE_ACCOUNT + ": Create a new account \n" +
                LOG_IN_TO_EXISTS_ACCOUNT + ": Login to an existing account \n" +
                END_THE_PROGRAM + ": End the program \n" + "Enter your answer: ");
        int userInput = scanner.nextInt();
        return userInput;
    }

    public static void main(String[] args) {
        int userInput;
        RealEstate account = new RealEstate();
        do {
            userInput = menu();
            switch (userInput) {
                case CREATE_ACCOUNT: {
                    account.createUser();
                    break;
                }
                case LOG_IN_TO_EXISTS_ACCOUNT: {
                    account.login();
                    break;
                }
                case END_THE_PROGRAM:
                    break;
            }

        } while (userInput != END_THE_PROGRAM);
    }
}

