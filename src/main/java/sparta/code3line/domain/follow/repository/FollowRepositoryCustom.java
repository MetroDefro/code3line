package sparta.code3line.domain.follow.repository;

import sparta.code3line.domain.follow.dto.TopFollowerResponseDto;

import java.util.List;

public interface FollowRepositoryCustom {

    List<TopFollowerResponseDto> findTop10Followers();

}
