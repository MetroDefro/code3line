package sparta.code3line.domain.board.repository;

import sparta.code3line.domain.board.dto.BoardResponseDto;

import java.util.Optional;

public interface BoardRepositoryCustom {
    Optional<BoardResponseDto> findWithLikeCountById(Long id);
}
