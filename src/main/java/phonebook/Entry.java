package phonebook;

import javax.persistence.*;

@Entity
public class Entry {

    @Id
    @GeneratedValue
    private Long index;
    private String lastName;
    private String firstName;
    private String phone;

    public Long getIndex() {
        return index;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
