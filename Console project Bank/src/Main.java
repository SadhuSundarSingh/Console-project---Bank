import java.util.Scanner;

public class Main {

    static {
        Admin admin = new Admin("Sadhu", 17, 10000, Branch.KALAKAD, "sad17", 1234567890);
        admin.setId(Branch.KALAKAD);
    }

    public static void main(String[] args) {
        int homePageChoice = homePage();
        switch (homePageChoice) {
            case 1:
                //For log in with admin
                Admin admin;
                String adminPassword;
                String adminId = getAdminId();
                if (Admin.adminValidation(adminId)) {
                    admin = Admin.findAdminObject(adminId);
                    adminPassword = getLoginPassword();
                    if (adminPassword.equals(admin.password)) {
                        int adminChoice = getAdminChoice();
                        switch(adminChoice) {
                            case 1:
                                System.out.println(admin);
                                break;
                            case 2:
                                //For creating admin account
                                String adminName = getName();
                                double salary = getAdminSalary();
                                int age = getAdminAge();
                                Branch adminBranch = getBranch();
                                String adminNewPassword = getPassword();
                                long mobileNumber = getAdminMobileNumber();
                                Admin newAdmin = new Admin(adminName,age,salary,adminBranch,adminNewPassword,mobileNumber);
                                newAdmin.setId(adminBranch);
                                System.out.println(newAdmin);
                                break;
                            case 3:
                                Account userAccount;
                                String accountNumber = getAccountNumber();
                                if (Account.validateAccountNumber(accountNumber)) {
                                    userAccount = Account.findUserAccountObject(accountNumber);
                                    System.out.println(userAccount);
                                }
                                else {
                                        System.out.println("Account not found");
                                }
                                break;
                            case 4:
                                main(new String[]{});
                        }
                    }
                    else {
                        System.out.println("You are not admin");
                    }
                }
                String lastchoice = getLastChoice();
                if (lastchoice.equals("1")) {
                    main(new String[]{});
                }
                break;
            case 2:
                //For log in with user
                Account userAccount;
                String accountNumber = getAccountNumber();
                if (Account.validateAccountNumber(accountNumber)) {
                    userAccount = Account.findUserAccountObject(accountNumber);
                    int userOption = getUserOption();
                    switch (userOption) {
                        case 1:
                            userAccount.accountDetails();
                            break;
                        case 2:
                            double depositeAmount = getDepositeAmount();
                            userAccount.deposite(depositeAmount);
                            userAccount.viewBalance();
                            break;
                        case 3:
                            double withdrawAmount = getWithdrawAmount(userAccount);
                            userAccount.withdraw(withdrawAmount);
                            userAccount.viewBalance();
                            break;
                        case 4:
                            if (validateUserPassword(userAccount)) {
                                userAccount.getHistory();
                            }
                            else {
                                System.out.println("Incorrect password");
                                String lastChoice = getLastChoice();
                                if (lastChoice.equals("1")) {
                                    main(new String[]{});
                                }
                            }
                            String lastChoice = getLastChoice();
                            if (lastChoice.equals("1")) {
                                main(new String[]{});
                            }
                            break;
                        case 5:
                            int settingsChoice = getSettingOption();
                            switch (settingsChoice) {
                                case 1:
                                    //For name change
                                    if (validateUserPassword(userAccount)) {
                                        String userNewName = getName();
                                        userAccount.setName(userNewName);
                                        System.out.println("Your name changed successfully......");
                                    } else {
                                        System.out.println("Wrong password");
                                        String exitChoice = getLastChoice();
                                        if (exitChoice.equals("1")) {
                                            main(new String[]{});
                                        }
                                    }
                                    break;
                                case 2:
                                    //For password change
                                    if (validateUserPassword(userAccount)) {
                                        String newPassword = getPassword();
                                        userAccount.setPassword(newPassword);
                                        System.out.println("Your password changed successfully......");
                                    } else {
                                        System.out.println("Wrong password");
                                        String ExitChoice = getLastChoice();
                                        if (ExitChoice.equals("1")) {
                                            main(new String[]{});
                                        }
                                    }
                                    break;
                                case 3:
                                    //For address change
                                    if (validateUserPassword(userAccount)) {
                                        String newAddress = getAddress();
                                        userAccount.setAddress(newAddress);
                                        System.out.println("Your address changed successfully......");
                                    } else {
                                        System.out.println("Wrong password");
                                        String exitchoice = getLastChoice();
                                        if (exitchoice.equals("1")) {
                                            main(new String[]{});
                                        }
                                    }
                                    break;
                                case 4:
                                    main(new String[]{});
                                    break;
                            }
                    }
                }
                else {
                    System.out.println("Account not found");
                }
                String LastChoice = getLastChoice();
                if (LastChoice.equals("1")) {
                    main(new String[]{});
                }
                break;
            case 3:
                //For creating user account
                String userName = getName();
                long aadharNumber = getAadharNumber();
                Branch branch = getBranch();
                String password = getPassword();
                String address = getAddress();
                Account account = new Account(userName, aadharNumber, branch, password, address);
                account.setNumber(branch);
                System.out.println(account);
                String lastChoice = getLastChoice();
                if (lastChoice.equals("1")) {
                    main(new String[]{});
                }
        }
    }

    public static double getAdminSalary() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter your salary : ");
        } catch (Exception ex) {
            System.out.println("Invalid input");
            System.out.println();
            return getAdminSalary();
        }
        double salary = scanner.nextDouble();
        scanner.nextLine();
        return salary;
    }

    public static int getAdminAge() {
        Scanner scanner = new Scanner(System.in);
        int age = 0;
        try {
            System.out.println("Enter your age :");
            age = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception ex) {
            System.out.println("Invalid input");
            return getAdminAge();
        }
        if (age < 18) {
            System.out.println("Your age is too low ");
            return getAdminAge();
        }
        if (age > 58) {
            System.out.println("Your age is too large");
            return getAdminAge();
        }
        return age;
    }

    public static String getName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name : ");
        String name = scanner.next();
        scanner.nextLine();
        if(name.matches("[a-zA-z]+")) {
            return name;
        }
        else {
            System.out.println("Enter valid input");
            return getName();
        }
    }

    public static long getAadharNumber() {
        Scanner scanner = new Scanner(System.in);
        long aadharNumber = 0;
        System.out.println("Enter your aadhar number : ");
        try {
            aadharNumber = scanner.nextLong();
            scanner.nextLine();
        }
        catch (Exception ex) {
            System.out.println("Invalid input");
            System.out.println();
            return getAadharNumber();
        }
        if (Account.validateAadharNumber(aadharNumber)) {
            return aadharNumber;
        } else {
            return getAadharNumber();
        }
    }

    public static Branch getBranch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select your branch......\n1--> Tenkasi\n2--> Tirunelveli\n3--> Kalakad\n4--> Chennai\n5--> Ambai");
        int branch = 0;
        try {
            branch = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception ex) {
            System.out.println("Invalid option");
            return getBranch();
        }
        if (branch < 1 || branch > 5) {
            System.out.println("Invalid option");
            return getBranch();
        }
        Branch userBranch = null;
        int i = 1;
        for (Branch br : Branch.values()) {
            if (branch == i) {
                userBranch = br;
                break;
            }
            ++i;
        }
        return userBranch;
    }

    public static String getPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your new password : ");
        String pass = scanner.nextLine();
        System.out.println("Confirm password : ");
        String confirmPass = scanner.nextLine();
        if (pass.equals(confirmPass)) {
            return pass;
        }
        else {
            System.out.println("Incorrect confirm password");
            return getPassword();
        }
    }

    public static String getAddress() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your address : ");
        String address = scanner.next();
        scanner.nextLine();
        return address;
    }

    public static String getAdminId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your id : ");
        String id = scanner.next();
        scanner.nextLine();
        return id;
    }

    public static String getLoginPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your password : ");
        String pass = scanner.next();
        scanner.nextLine();
        return pass;
    }

    public static String getAccountNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account number : ");
        String accountNumber = scanner.next();
        scanner.nextLine();
        return accountNumber;
    }

    public static long getAdminMobileNumber() {
        Scanner scanner = new Scanner(System.in);
        long mobileNumber = 0;
        System.out.println("Enter your mobile number : ");
        try {
            mobileNumber = scanner.nextLong();
            scanner.nextLine();
        } catch (Exception ex) {
            System.out.println("Invalid input");
            System.out.println();
            return getAdminMobileNumber();
        }
        return mobileNumber;
    }

    public static int homePage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("        Home Page");
        int choice = 0;
        try {
            System.out.println("1--> Admin \n2--> Already has account \n3--> Create account ");
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception ex) {
            System.out.println("Invalid input");
            return homePage();
        }
        if (choice < 1 || choice > 3) {
            System.out.println("Please enter valid input");
            return homePage();
        }
        return choice;
    }

    public static String getLastChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("1--> Home \nOthers--> Exit program");
        String choice = scanner.next();
        scanner.nextLine();
        return choice;
    }

    public static int getUserOption() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        try {
            System.out.println("1--> Account details \n2--> Deposit \n3--> Withdraw \n4--> History \n5--> Settings");
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception ex) {
            System.out.println("Invalid input");
            return getUserOption();
        }
        if (choice < 1 || choice > 5) {
            System.out.println("Please enter valid input");
            return getUserOption();
        }
        return choice;
    }

    public static double getDepositeAmount() {
        System.out.println("Minimum deposite amount is Rs:100 ");
        Scanner scanner = new Scanner(System.in);
        double amount = 0.0;
        System.out.println("Enter deposit amount : ");
        try {
            amount = scanner.nextDouble();
            scanner.nextLine();
        } catch (Exception ex) {
            System.out.println("Invalid input");
            System.out.println();
            return getDepositeAmount();
        }
        if (amount < 100) {
            System.out.println("Enter valid amount ");
            return getDepositeAmount();
        }
        return amount;
    }

    public static double getWithdrawAmount(Account userAccount) {
        Scanner scanner = new Scanner(System.in);
        double amount = 0.0;
        if (validateUserPassword(userAccount)) {
            System.out.println("Minimum withdraw amount is Rs:100 ");
            System.out.println("Enter amount ");
            try {
                amount = scanner.nextDouble();
                scanner.nextLine();
            } catch (Exception ex) {
                System.out.println("Invalid input");
                String lastChoice = getLastChoice();
                if (lastChoice.equals("1")) {
                    main(new String[]{});
                }
            }
            if (amount > userAccount.balance || amount < 100) {
                System.out.println("Insufficient balance ");
                userAccount.viewBalance();
                String lastChoice = getLastChoice();
                if (lastChoice.equals("1")) {
                    main(new String[]{});
                }
            }
        } else {
            System.out.println("Incorrect password");
            String lastChoice = getLastChoice();
            if (lastChoice.equals("1")) {
                main(new String[]{});
            }
        }
        return amount;
    }

    public static boolean validateUserPassword(Account userAccount) {
        String accountPassword;
        accountPassword = getLoginPassword();
        return accountPassword.equals(userAccount.password);
    }

    public static int getSettingOption() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        try {
            System.out.println("1--> Change name \n2--> Change password \n3--> Change address \n4--> Home");
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception ex) {
            System.out.println("Invalid input");
            return getUserOption();
        }
        if (choice < 1 || choice > 4) {
            System.out.println("Please enter valid input");
            return getUserOption();
        }
        return choice;
    }

    public static int getAdminChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        try {
            System.out.println("1--> View admin details \n2--> Add admin \n3--> Search(Enter account number) \n4--> Home");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        catch (Exception ex) {
            System.out.println("Invalid input");
            return getUserOption();
        }
        if (choice < 1 || choice > 4) {
            System.out.println("Please enter valid input");
            return getUserOption();
        }
        return choice;
    }
}
