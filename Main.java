package project5;

import java.util.Scanner;

//Class to represent the user's bank account
class BankAccount {
 private double balance;

 public BankAccount(double initialBalance) {
     this.balance = initialBalance;
 }

 public double getBalance() {
     return balance;
 }

 public void deposit(double amount) {
     balance += amount;
 }

 public boolean withdraw(double amount) {
     if (amount <= balance) {
         balance -= amount;
         return true;
     } else {
         System.out.println("Insufficient funds. Withdrawal failed.");
         return false;
     }
 }
}

//Class to represent the ATM machine
class ATM {
 private BankAccount userAccount;

 public ATM(BankAccount userAccount) {
     this.userAccount = userAccount;
 }

 public void displayMenu() {
     System.out.println("1. Withdraw");
     System.out.println("2. Deposit");
     System.out.println("3. Check Balance");
     System.out.println("4. Exit");
 }

 public void processTransaction() {
     try(Scanner scanner = new Scanner(System.in)) {
     int choice;

     do {
         displayMenu();
         System.out.print("Choose an option (1-4): ");
         choice = scanner.nextInt();

         switch (choice) {
             case 1:
                 System.out.print("Enter withdrawal amount: ");
                 double withdrawAmount = scanner.nextDouble();
                 if (withdrawAmount > 0) {
                     userAccount.withdraw(withdrawAmount);
                 } else {
                     System.out.println("Invalid amount. Please enter a positive value.");
                 }
                 break;

             case 2:
                 System.out.print("Enter deposit amount: ");
                 double depositAmount = scanner.nextDouble();
                 if (depositAmount > 0) {
                     userAccount.deposit(depositAmount);
                 } else {
                     System.out.println("Invalid amount. Please enter a positive value.");
                 }
                 break;

             case 3:
                 System.out.println("Current Balance: $" + userAccount.getBalance());
                 break;

             case 4:
                 System.out.println("Exiting. Thank you!");
                 break;

             default:
                 System.out.println("Invalid option. Please choose a valid option (1-4).");
         }

     } while (choice != 4);
 }
}
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankAccount userAccount = new BankAccount(1000);

        // Create an ATM instance with the user's bank account
        ATM atmMachine = new ATM(userAccount);

        // Process transactions using the ATM
        atmMachine.processTransaction();
    }


	

}
