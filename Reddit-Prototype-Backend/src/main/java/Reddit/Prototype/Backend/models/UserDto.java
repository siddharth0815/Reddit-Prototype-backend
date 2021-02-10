package Reddit.Prototype.Backend.models;

import lombok.Data;

@Data
public class UserDto {

    private String userName;

    public UserDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
