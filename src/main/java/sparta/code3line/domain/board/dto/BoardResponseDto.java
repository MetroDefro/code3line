package sparta.code3line.domain.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import sparta.code3line.domain.board.entity.Board;

import java.time.LocalDateTime;

@Data
public class BoardResponseDto {

    private String nickname;
    private Long boardId;
    private String title;
    private String contents;
    private Integer likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public BoardResponseDto(Board board) {

        this.nickname = board.getUser().getNickname();
        this.boardId = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();

    }

    @QueryProjection
    public BoardResponseDto(String nickname, Long boardId, String title, String contents, Integer likeCount,
                            LocalDateTime createdAt, LocalDateTime modifiedAt) {

        this.nickname = nickname;
        this.boardId = boardId;
        this.title = title;
        this.contents = contents;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;

    }

}
