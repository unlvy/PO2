import java.io.Serializable;

public class Contact implements Serializable {

    private String name;
    private int number;
    private String email;
    
    public Contact(String name, int number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        String result = "nazwa: " + name;
        result += "\nnumer: " + number;
        result += "\nemail: " + email;
        return result;
    }

}
