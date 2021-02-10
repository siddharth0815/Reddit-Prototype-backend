package Reddit.Prototype.Backend.models;

import lombok.Data;

@Data
public class CommunityDto {

    private String communityName;

    public CommunityDto() {
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
