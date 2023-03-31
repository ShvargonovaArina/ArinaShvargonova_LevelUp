package ru.levelp.at.homework6.tests;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.levelp.at.homework6.data.comments.CommentListData;
import ru.levelp.at.homework6.data.comments.CommentResponse;
import ru.levelp.at.homework6.data.comments.CreateCommentRequest;
import ru.levelp.at.homework6.data.posts.CreatePostRequest;
import ru.levelp.at.homework6.data.posts.PostResponse;
import ru.levelp.at.homework6.data.users.CreateUserRequest;
import ru.levelp.at.homework6.data.users.UserResponse;
import ru.levelp.at.homework6.suits.Tags;

public class CommentApiTest extends BaseApiTest {

    @Tag(Tags.SUITE_TAG_NAME)
    @Test
    void getAllComments() {
        commentService
            .getAllComments()
            .then()
            .spec(responseSpecificationHttpStatusOk)
            .extract()
            .as(CommentListData.class);
    }

    @Tag(Tags.SUITE_TAG_NAME)
    @Test
    void createComment() {
        var requestUserBody = generateUserCreateRequestBody();
        var createdUser = userService.createUser(requestUserBody)
                                     .then()
                                     .spec(responseSpecificationHttpStatusCreated)
                                     .extract()
                                     .as(UserResponse.class);

        var requestPostBody = generateCreatePostRequestBody(createdUser.getId());
        var createdPost = postService.createPost(requestPostBody)
                                      .then()
                                      .spec(responseSpecificationHttpStatusCreated)
                                      .extract()
                                      .as(PostResponse.class);

        var requestCommentBody = generateCommentRequestBody(
            createdPost.getId(), createdUser.getName(), createdUser.getEmail()
        );
        var createdComment = commentService.createComment(requestCommentBody)
                                      .then()
                                      .spec(responseSpecificationHttpStatusCreated)
                                      .extract()
                                      .as(CommentResponse.class);

        checkCreateComment(requestCommentBody, createdComment);
    }

    @Tag(Tags.SUITE_TAG_NAME)
    @Test
    void getPost() {
        var requestUserBody = generateUserCreateRequestBody();
        var createdUser = userService.createUser(requestUserBody)
                                     .then()
                                     .spec(responseSpecificationHttpStatusCreated)
                                     .extract()
                                     .as(UserResponse.class);

        var requestPostBody = generateCreatePostRequestBody(createdUser.getId());
        var createdPost = postService.createPost(requestPostBody)
                                     .then()
                                     .spec(responseSpecificationHttpStatusCreated)
                                     .extract()
                                     .as(PostResponse.class);

        var requestCommentBody = generateCommentRequestBody(
            createdPost.getId(), createdUser.getName(), createdUser.getEmail()
        );
        var createdComment = commentService.createComment(requestCommentBody)
                                           .then()
                                           .spec(responseSpecificationHttpStatusCreated)
                                           .extract()
                                           .as(CommentResponse.class);

        var commentResponse = commentService.getComment(createdComment.getId().toString())
                                         .then()
                                         .spec(responseSpecificationHttpStatusOk)
                                         .extract()
                                         .as(CommentResponse.class);
        checkGetComment(createdComment, commentResponse);
    }

    @Tag(Tags.SUITE_TAG_NAME)
    @Test
    void putComment() {
        var requestUserBody = generateUserCreateRequestBody();
        var createdUser = userService.createUser(requestUserBody)
                                     .then()
                                     .spec(responseSpecificationHttpStatusCreated)
                                     .extract()
                                     .as(UserResponse.class);

        var requestPostBody = generateCreatePostRequestBody(createdUser.getId());
        var createdPost = postService.createPost(requestPostBody)
                                     .then()
                                     .spec(responseSpecificationHttpStatusCreated)
                                     .extract()
                                     .as(PostResponse.class);

        var requestCommentBody = generateCommentRequestBody(
            createdPost.getId(), createdUser.getName(), createdUser.getEmail()
        );
        var createdComment = commentService.createComment(requestCommentBody)
                                           .then()
                                           .spec(responseSpecificationHttpStatusCreated)
                                           .extract()
                                           .as(CommentResponse.class);

        var secondRequestBody = generatePutRequestBody(
            createdPost.getId(), createdUser.getName(), createdUser.getEmail()
        );
        var changedComment = commentService.putComment(secondRequestBody, createdComment.getId().toString())
                                       .then()
                                       .spec(responseSpecificationHttpStatusOk)
                                       .extract()
                                       .as(CommentResponse.class);
        checkPutComment(createdComment, changedComment);
    }

    @Tag(Tags.SUITE_TAG_NAME)
    @Test
    void deleteComment() {
        var requestUserBody = generateUserCreateRequestBody();
        var createdUser = userService.createUser(requestUserBody)
                                     .then()
                                     .spec(responseSpecificationHttpStatusCreated)
                                     .extract()
                                     .as(UserResponse.class);

        var requestPostBody = generateCreatePostRequestBody(createdUser.getId());
        var createdPost = postService.createPost(requestPostBody)
                                     .then()
                                     .spec(responseSpecificationHttpStatusCreated)
                                     .extract()
                                     .as(PostResponse.class);

        var requestCommentBody = generateCommentRequestBody(
            createdPost.getId(), createdUser.getName(), createdUser.getEmail()
        );
        var createdComment = commentService.createComment(requestCommentBody)
                                           .then()
                                           .spec(responseSpecificationHttpStatusCreated)
                                           .extract()
                                           .as(CommentResponse.class);

        commentService.deleteComment(createdComment.getId().toString())
                      .then()
                      .spec(responseSpecificationHttpStatusDelete);
    }

    public CreateUserRequest generateUserCreateRequestBody() {
        return CreateUserRequest
            .builder()
            .name(faker.name().fullName())
            .email(faker.internet().emailAddress())
            .gender(faker.dog().gender())
            .status("active")
            .build();
    }

    public CreatePostRequest generateCreatePostRequestBody(Integer userId) {
        return CreatePostRequest
            .builder()
            .user_id(userId)
            .title(faker.lorem().words(3).toString())
            .body(faker.lorem().sentences(5).toString())
            .build();
    }

    public CommentResponse generatePutRequestBody(Integer postId, String name, String email) {
        return CommentResponse
            .builder()
            .post_id(postId)
            .name(name)
            .email(email)
            .body(faker.lorem().sentences(5).toString())
            .build();
    }

    public CreateCommentRequest generateCommentRequestBody(Integer postId, String name, String email) {
        return CreateCommentRequest
            .builder()
            .post_id(postId)
            .name(name)
            .email(email)
            .body(faker.lorem().sentences(5).toString())
            .build();
    }

    private void checkCreateComment(CreateCommentRequest request, CommentResponse actualResponse) {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualResponse.getId()).isNotNull();
            softAssertions.assertThat(actualResponse.getPost_id())
                          .isEqualTo(request.getPost_id());
            softAssertions.assertThat(actualResponse.getName())
                          .isEqualTo(request.getName());
            softAssertions.assertThat(actualResponse.getEmail())
                          .isEqualTo(request.getEmail());
            softAssertions.assertThat(actualResponse.getBody())
                          .isEqualTo(request.getBody());
        });
    }

    private void checkGetComment(CommentResponse request, CommentResponse actualResponse) {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualResponse.getId()).isNotNull();
            softAssertions.assertThat(actualResponse.getPost_id())
                          .isEqualTo(request.getPost_id());
            softAssertions.assertThat(actualResponse.getName())
                          .isEqualTo(request.getName());
            softAssertions.assertThat(actualResponse.getEmail())
                          .isEqualTo(request.getEmail());
            softAssertions.assertThat(actualResponse.getBody())
                          .isEqualTo(request.getBody());
        });
    }

    private void checkPutComment(CommentResponse request, CommentResponse actualResponse) {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualResponse.getId()).isNotNull();
            softAssertions.assertThat(actualResponse.getPost_id())
                          .isEqualTo(request.getPost_id());
            softAssertions.assertThat(actualResponse.getName())
                          .isEqualTo(request.getName());
            softAssertions.assertThat(actualResponse.getEmail())
                          .isEqualTo(request.getEmail());
            softAssertions.assertThat(actualResponse.getBody())
                          .isNotEqualTo(request.getBody());
        });
    }
}
