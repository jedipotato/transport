package hr.tvz.zavrsni.domain.api;

import com.google.gson.annotations.SerializedName;

import hr.tvz.zavrsni.util.Const;


public class User extends BasicModel {
    @SerializedName(Const.USER_ID) private String userId;
    @SerializedName(Const.USER_NAME) private String name;
    @SerializedName(Const.USER_SURNAME) private String surname;
    @SerializedName(Const.USER_USERNAME) private String username;
    @SerializedName(Const.USER_PASSWORD) private String password;
    @SerializedName(Const.USER_EMAIL) private String email;
    @SerializedName(Const.USER_COMPANY) private String companyName;
    @SerializedName(Const.USER_CONTACT) private String contact;

    public User(String name, String surname,String username, String password, String email,
                String companyName, String contact) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.companyName = companyName;
        this.contact = contact;
    }

    public String getUserId() {
        return userId == null ? "" : userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname == null ? "" : surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email == null ? "" : email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username == null ? "" : username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyName() {
        return companyName == null ? "" : companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContact() {
        return contact == null ? "" : contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (companyName != null ? !companyName.equals(user.companyName) : user.companyName != null)
            return false;
        if (contact != null ? !contact.equals(user.contact) : user.contact != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null)
            return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", companyName='" + companyName + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
