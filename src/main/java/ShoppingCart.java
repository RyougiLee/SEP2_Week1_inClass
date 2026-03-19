import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ShoppingCart {

    protected Locale locale;
    protected ResourceBundle messages;
    Scanner scanner = new Scanner(System.in);

    public void selectLanguage() {
        System.out.println("Select a language: ");
        System.out.println("1. English");
        System.out.println("2. Finnish");
        System.out.println("3. Swedish");

        int choice = scanner.nextInt();

        // Set the locale based on user's choice
        switch (choice) {
            case 1:
                locale = new Locale("en", "US");
                break;
            case 2:
                locale = new Locale("fi", "FI");
                break;
            case 3:
                locale = new Locale("sv", "SE");
                break;
            default:
                System.out.println("Invalid choice. Defaulting to English.");
                locale = new Locale("en", "US");
                break;
        }

        // Load the resource bundle for the selected locale
        messages = ResourceBundle.getBundle("MessagesBundle", locale);
    }

    public double run() {
        selectLanguage();
        double totalValue = 0;

        System.out.println(messages.getString("askNumber"));
        int num = scanner.nextInt();
        for(int i=0; i<num; i++){
            System.out.println(messages.getString("askPrice") + (i+1));
            double price = scanner.nextDouble();
            System.out.println(messages.getString("askQuantity") + (i+1));
            int quantity = scanner.nextInt();
            totalValue += price * quantity;
        }
        System.out.println(messages.getString("totalCost") + totalValue);
        return totalValue;
    }
}
