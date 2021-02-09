package Reddit.Prototype.Backend.models;

import lombok.Data;

@Data
public class ContentDto {
    private  Long id;
    private  String contentBody;

    public String getContentBody() {
        return contentBody;
    }

    public void setContentBody(String contentBody) {
        this.contentBody = contentBody;
    }

    private  Long parentId;
    private  Long upvotes;
    private  Long downvotes;

//    public ContentDto(Long id, Long parentId, Long upvotes, Long downvotes) {
//        this.id = id;
//        this.parentId = parentId;
//        this.upvotes = upvotes;
//        this.downvotes = downvotes;
//    }

    public ContentDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Long upvotes) {
        this.upvotes = upvotes;
    }

    public Long getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Long downvotes) {
        this.downvotes = downvotes;
    }
}
