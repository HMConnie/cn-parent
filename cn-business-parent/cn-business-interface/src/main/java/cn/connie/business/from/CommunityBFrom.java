package cn.connie.business.from;

import com.sgcai.commons.lang.base.BasicTO;

public class CommunityBFrom extends BasicTO {
    private String userId;

    private String content;

    private String images;

    private static final long serialVersionUID = 1L;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

}
