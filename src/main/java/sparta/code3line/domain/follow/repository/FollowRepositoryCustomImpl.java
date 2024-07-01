package sparta.code3line.domain.follow.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sparta.code3line.domain.follow.dto.QTopFollowerResponseDto;
import sparta.code3line.domain.follow.dto.TopFollowerResponseDto;

import java.util.List;

import static sparta.code3line.domain.follow.entity.QFollow.follow;
import static sparta.code3line.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryCustomImpl implements FollowRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<TopFollowerResponseDto> findTop10Followers() {

        return queryFactory
                .select(new QTopFollowerResponseDto(
                        user.nickname,
                        follow.count().intValue()
                ))
                .from(user)
                .leftJoin(follow).on(follow.following.eq(user))
                .groupBy(user.id)
                .orderBy(new OrderSpecifier<>(Order.DESC, follow.count()))
                .limit(10)
                .fetch();

    }

}
