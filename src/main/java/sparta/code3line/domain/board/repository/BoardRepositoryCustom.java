package sparta.code3line.domain.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sparta.code3line.domain.board.dto.BoardResponseDto;
import sparta.code3line.domain.board.dto.BoardSearchCond;

import java.util.Optional;

public interface BoardRepositoryCustom {
    Optional<BoardResponseDto> findWithLikeCountById(Long id);
    Page<BoardResponseDto> findWithLikeCountByUserId(Long id, Pageable pageable, String sortType);
    Page<BoardResponseDto> findByFollowing(Long userId, BoardSearchCond cond, Pageable pageable);
}
