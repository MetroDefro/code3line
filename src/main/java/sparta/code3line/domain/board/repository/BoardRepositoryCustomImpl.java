package sparta.code3line.domain.board.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import sparta.code3line.domain.board.dto.BoardResponseDto;
import sparta.code3line.domain.board.dto.QBoardResponseDto;

import java.util.List;
import java.util.Optional;

import static sparta.code3line.domain.board.entity.QBoard.board;
import static sparta.code3line.domain.like.entity.QLikeBoard.likeBoard;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<BoardResponseDto> findWithLikeCountById(Long id) {

        return Optional.ofNullable(queryFactory
                .select(new QBoardResponseDto(
                        board.user.nickname,
                        board.id,
                        board.title,
                        board.contents,
                        likeBoard.count().intValue(),
                        board.createdAt,
                        board.modifiedAt
                ))
                .from(board)
                .leftJoin(board.likeBoards, likeBoard)
                .where(board.id.eq(id))
                .groupBy(board.id)
                .fetchOne()
        );

    }

    @Override
    public Page<BoardResponseDto> findWithLikeCountByUserId(Long id, Pageable pageable, String sortType) {

        List<BoardResponseDto> fetch = queryFactory
                .select(new QBoardResponseDto(
                        board.user.nickname,
                        board.id,
                        board.title,
                        board.contents,
                        likeBoard.count().intValue(),
                        board.createdAt,
                        board.modifiedAt
                ))
                .from(board)
                .leftJoin(board.likeBoards, likeBoard)
                .where(likeBoard.user.id.eq(id))
                .groupBy(board.id)
                .offset(pageable.getOffset())
                .orderBy(createOrderSpecifier(sortType))
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(likeBoard.count())
                .from(likeBoard)
                .where(likeBoard.user.id.eq(id))
                .fetchOne();

        return new PageImpl<>(fetch, pageable, count);

    }

    private OrderSpecifier createOrderSpecifier(String sortType) {

        return switch (sortType) {
            case "createdAt" -> new OrderSpecifier<>(Order.DESC, board.createdAt);
            default -> new OrderSpecifier<>(Order.DESC, board.createdAt);
        };
    }
}
