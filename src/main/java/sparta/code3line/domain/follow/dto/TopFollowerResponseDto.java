package sparta.code3line.domain.follow.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class TopFollowerResponseDto {

    private String nickname;
    private Integer followerCount;

    @QueryProjection
    public TopFollowerResponseDto(String nickname, Integer followerCount) {

        this.nickname = nickname;
        this.followerCount = followerCount;

    }

}
