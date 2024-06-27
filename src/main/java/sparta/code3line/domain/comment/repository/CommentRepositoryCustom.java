package sparta.code3line.domain.comment.repository;

import sparta.code3line.domain.comment.dto.CommentResponseDto;

import java.util.Optional;

public interface CommentRepositoryCustom {
    Optional<CommentResponseDto> findWithLikeCountById(Long id);

}
