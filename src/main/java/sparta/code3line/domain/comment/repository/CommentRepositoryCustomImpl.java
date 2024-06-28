package sparta.code3line.domain.comment.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import sparta.code3line.domain.comment.dto.CommentResponseDto;
import sparta.code3line.domain.comment.dto.QCommentResponseDto;

import java.util.List;
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

    @Override
    public Page<CommentResponseDto> findWithLikeCountByUserId(Long id, Pageable pageable, String sortType) {

        List<CommentResponseDto> fetch = queryFactory
            .select(new QCommentResponseDto(
                comment.user.id,
                comment.board.id,
                comment.contents,
                likeComment.count().intValue()
            ))
            .from(comment)
            .leftJoin(comment.likeComments, likeComment)
            .where(likeComment.user.id.eq(id))
            .groupBy(comment.id)
            .offset(pageable.getOffset())
            .orderBy(createOrderSpecifier(sortType))
            .limit(pageable.getPageSize())
            .fetch();

        Long count = queryFactory
                .select(likeComment.count())
                .from(likeComment)
                .where(likeComment.user.id.eq(id))
                .fetchOne();

        return new PageImpl<>(fetch, pageable, count);

    }

    private OrderSpecifier createOrderSpecifier(String sortType) {

        return switch (sortType) {
            case "createdAt" -> new OrderSpecifier<>(Order.DESC, comment.createdAt);
            default -> new OrderSpecifier<>(Order.DESC, comment.createdAt);
        };
    }

}
