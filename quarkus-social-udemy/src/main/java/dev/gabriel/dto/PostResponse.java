package dev.gabriel.dto;

import dev.gabriel.domain.model.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {
    private String text;
    private LocalDateTime datetime;

    public static PostResponse fromEntity(Post post){
        PostResponse response = new PostResponse();
        response.setText(post.getText());
        response.setDatetime(post.getDateTime());
        return response;
    }
}
