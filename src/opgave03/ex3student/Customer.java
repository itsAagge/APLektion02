package opgave03.ex3student;

public class Customer {
    private final String id;
    private final String mobileNo;
    private final String firstname;
    private final String lastname;

    public Customer(String id, String mobileNo, String firstname, String lastname) {
        this.id = id;
        this.mobileNo = mobileNo;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s", id, mobileNo, firstname, lastname);
    }
}
