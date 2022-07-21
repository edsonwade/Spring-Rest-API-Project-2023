package traineer.vanilson.restfullapis_with_spring_boot_2022.security;

import java.io.Serializable;
import java.util.Objects;

public class AccountCredentialSecurity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;

    public AccountCredentialSecurity() {
    }

    public AccountCredentialSecurity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountCredentialSecurity that = (AccountCredentialSecurity) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }

    @Override
    public String toString() {
        return "AccountCredential{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
