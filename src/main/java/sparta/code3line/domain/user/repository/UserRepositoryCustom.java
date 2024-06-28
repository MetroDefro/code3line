package sparta.code3line.domain.user.repository;

public interface UserRepositoryCustom {

    Integer getLikeBoardCountById(Long id);
    Integer getLikeCommentCountById(Long id);

}
