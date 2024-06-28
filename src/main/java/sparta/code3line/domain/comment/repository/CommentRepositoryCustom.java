package sparta.code3line.domain.comment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sparta.code3line.domain.comment.dto.CommentResponseDto;

import java.util.Optional;

public interface CommentRepositoryCustom {
    Optional<CommentResponseDto> findWithLikeCountById(Long id);
    Page<CommentResponseDto> findWithLikeCountByUserId(Long id, Pageable pageable, String sortType);

}
