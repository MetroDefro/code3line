package sparta.code3line.domain.board.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sparta.code3line.domain.board.dto.BoardResponseDto;
import sparta.code3line.domain.board.dto.QBoardResponseDto;

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
}
