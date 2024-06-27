package sparta.code3line.domain.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sparta.code3line.domain.comment.dto.CommentResponseDto;
import sparta.code3line.domain.comment.dto.QCommentResponseDto;

import java.util.Optional;

import static sparta.code3line.domain.comment.entity.QComment.comment;
import static sparta.code3line.domain.like.entity.QLikeComment.likeComment;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<CommentResponseDto> findWithLikeCountById(Long id) {

        return Optional.ofNullable(queryFactory
                .select(new QCommentResponseDto(
                        comment.user.id,
                        comment.board.id,
                        comment.contents,
                        likeComment.count().intValue()
                ))
                .from(comment)
                .leftJoin(comment.likeComments, likeComment)
                .where(comment.id.eq(id))
                .groupBy(comment.id)
                .fetchOne()
        );

    }

}
