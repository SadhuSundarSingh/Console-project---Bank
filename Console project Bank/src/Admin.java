public class Admin {
    String name;
    String id;
    double salary;
    int age;
    Branch branch;
    String password;
    long mobileNumber;
    static int adminCount;

    Admin(String Name , int Age , double Salary , Branch _branch , String Password , long MobileNumber) {
        name = Name;
        age = Age;
        salary = Salary;
        branch = _branch;
        password = Password;
        mobileNumber = MobileNumber;
    }

    public void setId(Branch branch) {
        ++adminCount;
        id = "SADADMIN" + branch.branchCode + adminCount;
        Bank.adminList.add(this);
        Bank.adminIdList.add(id);
    }

    public String toString() {
        return "Name : " + name + "\nAge : " + age + "\nId :" + id + "\nPassword : "+ password + "\nSalary :" + salary + "\nBranch : " + branch + "\nMobile number : " + mobileNumber;
    }

    public static boolean adminValidation(String id) {
        return Bank.adminIdList.contains(id);
    }

    public static Admin findAdminObject(String Id) {
        Admin admin = null;
        for(Admin adminObj : Bank.adminList) {
            if(adminObj.id.equals(Id)) {
                admin = adminObj;
                break;
            }
        }
        return admin;
    }
}
