package sparta.code3line.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static sparta.code3line.domain.like.entity.QLikeBoard.likeBoard;
import static sparta.code3line.domain.like.entity.QLikeComment.likeComment;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Integer getLikeBoardCountById(Long id) {
        return Objects.requireNonNull(queryFactory
                        .select(likeBoard.count())
                        .from(likeBoard)
                        .where(likeBoard.user.id.eq(id))
                        .groupBy(likeBoard.user.id)
                        .fetchOne())
                .intValue();
    }

    @Override
    public Integer getLikeCommentCountById(Long id) {
        return Objects.requireNonNull(queryFactory
                        .select(likeComment.count())
                        .from(likeComment)
                        .where(likeComment.user.id.eq(id))
                        .groupBy(likeComment.user.id)
                        .fetchOne())
                .intValue();
    }
}
