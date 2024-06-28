package sparta.code3line.domain.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sparta.code3line.domain.board.dto.BoardResponseDto;

import java.util.Optional;

public interface BoardRepositoryCustom {
    Optional<BoardResponseDto> findWithLikeCountById(Long id);
    Page<BoardResponseDto> findWithLikeCountByUserId(Long id, Pageable pageable, String sortType);
}
