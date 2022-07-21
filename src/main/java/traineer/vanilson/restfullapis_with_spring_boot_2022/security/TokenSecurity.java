package traineer.vanilson.restfullapis_with_spring_boot_2022.security;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TokenSecurity implements Serializable {


    private static final long serialVersionUID = 1L;

    private String userName;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

    public TokenSecurity() {
    }

    public TokenSecurity(String userName,
                         Boolean authenticated,
                         Date created,
                         Date expiration,
                         String accessToken,
                         String refreshToken) {
        this.userName = userName;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenSecurity that = (TokenSecurity) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(authenticated, that.authenticated) &&
                Objects.equals(created, that.created) &&
                Objects.equals(expiration, that.expiration) &&
                Objects.equals(accessToken, that.accessToken) &&
                Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, authenticated, created, expiration, accessToken, refreshToken);
    }

    @Override
    public String toString() {
        return "TokenSecurity{" +
                "userName='" + userName + '\'' +
                ", authenticated=" + authenticated +
                ", created=" + created +
                ", expiration=" + expiration +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
