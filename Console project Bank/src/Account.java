import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    String name;
    String number;
    long aadharNumber;
    Branch branch;
    String password;
    String address;
    double balance;
    String history = "";
    static int accountCount;

    Account(String Name , long AadharNumber , Branch _branch , String Password , String Address) {
        name = Name;
        aadharNumber = AadharNumber;
        branch = _branch;
        password = Password;
        address = Address;
        Bank.aaharNumberList.add(aadharNumber);
        history += getCurrentDateAndTime() +  " : Account created" + "\n";
    }

    public void setName(String Name) {
        name = Name;
    }

    public void setPassword(String Password) {
        password = Password;
    }

    public static boolean validateAadharNumber(long AadharNumber) {
        return (!Bank.aaharNumberList.contains(AadharNumber));
    }

    public void setNumber(Branch branch) {
        ++accountCount;
        number = "SAD" + branch.branchCode + accountCount;
        Bank.accountNumberList.add(number);
        Bank.accountList.add(this);
    }

    public String toString() {
        return "Name : " + name +"\nAccount number : "+ number + "\nBalance : " + balance + "\nAadhar number : " + aadharNumber + "\nBranch : " + branch + "\nPassword : " + password + "\nAddress : " + address;
    }

    public void setAddress(String Address) {
        address = Address;
    }

    public static boolean validateAccountNumber(String accountNumber) {
        return (Bank.accountNumberList.contains(accountNumber));
    }

    public static Account findUserAccountObject(String accountNumber) {
        Account account = null;
        for(Account accountObj : Bank.accountList) {
            if(accountObj.number.equals(accountNumber)) {
                account = accountObj;
                break;
            }
        }
        return account;
    }

    public void accountDetails() {
        System.out.println(this);
    }

    public void deposite(double amount) {
        balance += amount;
        history += getCurrentDateAndTime() +  " : Rs." + amount + " credited" + "\n";
        System.out.println("Successfully deposited");
    }

    public void viewBalance() {
        System.out.println("Your current balance is : " + balance);
    }

    public void withdraw(double amount) {
        balance -= amount;
        history += getCurrentDateAndTime() +  " : Rs." + amount + " debited" + "\n";
        System.out.println("Successfully withdrew");
    }

    public static String getCurrentDateAndTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();
        return dtf.format(currentTime);
    }

    public void getHistory() {
        System.out.println("Your history : \n" + history);
    }
}
