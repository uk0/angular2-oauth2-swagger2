package com.sparksdev.flo.domain.user;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparksdev.flo.common.domain.BaseEntity;
import com.sparksdev.flo.common.domain.UserId;
import com.sparksdev.flo.domain.role.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bengill
 */
@Entity
@Document(collection = "user")
public class User extends BaseEntity implements UserDetails {

    @Indexed(unique = true, sparse = true)
    private String username;

    /** Session id when created. */
    private String sessionId;

    private List<Role> roles = new ArrayList();

    public User(final String username) {
        this.username = username;
    }

    @Deprecated
    public User() {
    }

    @JsonIgnore
    public UserId getUserId() {
        if (getId() != null) {
            return new UserId(getId());
        }
        return null;
    }




    public void setUsername(final String username) {
        this.username = username;
    }

    private String password;                    // required
    private String confirmPassword;
    private String passwordHint;
    private String firstName;                   // required
    private String lastName;                    // required
    private String phoneNumber;
    private boolean enabled;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;


    public String getPassword() {
        return password;
    }


    public String getConfirmPassword() {
        return confirmPassword;
    }


    @XmlTransient
    public String getPasswordHint() {
        return passwordHint;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    @JsonIgnore
    public String getEmail() {
        return getUsername();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(final String sessionId) {
        this.sessionId = sessionId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonIgnore
    public String getFullName() {
        return firstName + ' ' + lastName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isAccountExpired() {
        return accountExpired;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     * @return true if account is still active
     */
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return !isAccountExpired();
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     * @return false if account is locked
     */
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !isAccountLocked();
    }

    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     * @return true if credentials haven't expired
     */
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }



    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {


        return roles;
    }

    /**
     * Returns the username used to authenticate the user. Cannot return <code>null</code> .
     *
     * @return the username (never <code>null</code>)
     */
    @Override public String getUsername() {
        return username;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        final User user = (User) o;

        return !(username != null ? !username.equals(user.getUsername()) : user.getUsername() != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (username != null ? username.hashCode() : 0);
    }

    @Override public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", passwordHint='" + passwordHint + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", enabled=" + enabled +
                ", accountExpired=" + accountExpired +
                ", accountLocked=" + accountLocked +
                ", credentialsExpired=" + credentialsExpired +
                '}';
    }
}
