package machine;
import java.util.Scanner;

public class CoffeeMachine {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //message for user to choose
        System.out.println("Write action (buy, fill, take, remaining, exit):");

        //read user input
        String action;
        int act;

        CoffeeMachineImp coffeeMachine = new CoffeeMachineImp();

        //while user not choose exit, do read inputs from user
        do {
            act = coffeeMachine.pushCommand(scanner.nextLine());
        } while (act != 0);

    }


}

//enumeration for all statuses
enum MachineStatus {
    START, //when user need new action
    CHOOSING_AN_ACTION, //when user not choosing explicit action
    CHOOSING_A_VARIANT_OF_COFFEE, //when user choose to buy a coffee
    FILLING_WATER, //when user choose to fill consumptions
    FILLING_MILK,
    FILLING_BEANS,
    FILLING_CUPS
}

class CoffeeMachineImp {
    private int cash; //money amount inside machine
    private int water; //water amount inside machine
    private int milk; //milk amount inside machine
    private int beans; //beans amount inside machine
    private int cups; //cups amount inside machine
    private MachineStatus machineStatus;

    //init start amount for coffee machine consumables
    public CoffeeMachineImp() {
        this.cash = 550;
        this.water = 400;
        this.milk = 540;
        this.beans = 120;
        this.cups = 9;
        machineStatus = MachineStatus.CHOOSING_AN_ACTION;
    }

    public int pushCommand(String command) {

        //if user choose exit, return 0
        if (command.equals("exit")) {
            return 0;
        }

        //choose what to do with user input in dependence status of machine
        switch (machineStatus) {
            case START:
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                machineStatus = MachineStatus.CHOOSING_AN_ACTION;
                break;
            case CHOOSING_AN_ACTION:
                chooseAction(command);
                return 1;
            case CHOOSING_A_VARIANT_OF_COFFEE:
                buyCoffee(command);
                return 1;
            case FILLING_WATER:
                this.water += Integer.parseInt(command);
                machineStatus = MachineStatus.FILLING_MILK;
                System.out.println(this.water);
                System.out.println("Write how many ml of milk do you want to add:");
                return 1;
            case FILLING_MILK:
                this.milk += Integer.parseInt(command);
                machineStatus = MachineStatus.FILLING_BEANS;
                System.out.println("Write how many grams of coffee do you want to add:");
                return 1;
            case FILLING_BEANS:
                this.beans += Integer.parseInt(command);
                machineStatus = MachineStatus.FILLING_CUPS;
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                return 1;
            case FILLING_CUPS:
                this.cups += Integer.parseInt(command);
                machineStatus = MachineStatus.START;
                pushCommand("1");
                return 1;
            default:
                return 0;
        }

        return 0;
    }

    public void chooseAction(String action) {

            //choosing case which depends on user choice
            switch (action) {
                case "buy":
                    machineStatus = MachineStatus.CHOOSING_A_VARIANT_OF_COFFEE;

                    //start buying message
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    break;
                case "fill":
                    machineStatus = MachineStatus.FILLING_WATER;
                    System.out.println("Write how many ml of water do you want to add:");
                    break;
                case "take":
                    takeMoney();
                    break;
                case "remaining":
                    coffeeMachineStatus();
                    break;
            }

        //pushCommand(action);

    }

    public void buyCoffee(String coffeeType) {

        switch (coffeeType) {
            case "1":
                canBuy(250, 0, 16, 4); //call canBuy with actual parameters for espresso
                break;

            case "2":
                canBuy(350, 75, 20, 7); //call canBuy with actual parameters for latte
                break;

            case "3":
                canBuy(200, 100, 12 , 6); //call canBuy with actual parameters for cappuccino
                break;
            case "back":
                machineStatus = MachineStatus.CHOOSING_AN_ACTION;
                break;
        }

        //change status and reset machine
        machineStatus = MachineStatus.START;
        pushCommand("1");
    }

    public void takeMoney() {
        //message to user
        System.out.println("I gave you " + cash);
        System.out.println();

        //reset money to 0
        cash = 0;
    }

    public void canBuy(int w, int m, int b, int c) {

        //if not enough at least 1 ingredient, you can't buy a coffee
        if (w > water) {
            System.out.println("Sorry, not enough water!");
        } else if (m > milk) {
            System.out.println("Sorry, not enough milk!");
        } else if (b > beans) {
            System.out.println("Sorry, not enough beans!");
        } else if (cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            //buy!
            System.out.println("I have enough resources, making you a coffee!");
            water -= w;
            milk -= m;
            beans -= b;
            cups--;
            cash += c;
        }

        System.out.println();
    }

    public void fillConsumables(String command) {

        /*
        //sequence of messages
        System.out.println("Write how many ml of water do you want to add:");
        int newWater = scanner.nextInt(); //scan input
        water += newWater; //add water

        System.out.println("Write how many ml of milk do you want to add:");
        int newMilk = scanner.nextInt(); //scan input
        milk += newMilk; //add milk

        System.out.println("Write how many grams of coffee beans do you want to add:");
        int newBeans = scanner.nextInt(); //scan input
        beans += newBeans; //add beans

        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int newCups = scanner.nextInt();
        cups += newCups;

         */
    }

    public void coffeeMachineStatus() {
        //just output all consumables machine have at this moment
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(cash + " of money");
        System.out.println();

        machineStatus = MachineStatus.START;
        pushCommand("1");
    }
}
