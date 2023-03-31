package ru.levelp.at.homework6.tests;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.levelp.at.homework6.data.posts.CreatePostRequest;
import ru.levelp.at.homework6.data.posts.PostResponse;
import ru.levelp.at.homework6.data.users.CreateUserRequest;
import ru.levelp.at.homework6.data.users.UserResponse;
import ru.levelp.at.homework6.suits.Tags;

public class PostApiTest extends BaseApiTest {

    @Tag(Tags.SUITE_TAG_NAME)
    @Test
    void getAllPosts() {
        postService
            .getAllPosts()
            .then()
            .spec(responseSpecificationHttpStatusOk)
            .extract()
            .as(PostResponse.class);
    }

    @Tag(Tags.SUITE_TAG_NAME)
    @Test
    void createPost() {
        var requestUserBody = generateUserCreateRequestBody();
        var createdUser = userService.createUser(requestUserBody)
                                     .then()
                                     .spec(responseSpecificationHttpStatusCreated)
                                     .extract()
                                     .as(UserResponse.class);

        var requestBody = generatePostCreateRequestBody(createdUser.getId());
        var postPostData = postService.createPost(requestBody)
                                      .then()
                                      .spec(responseSpecificationHttpStatusCreated)
                                      .extract()
                                      .as(PostResponse.class);

        checkCreatePost(requestBody, postPostData);
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

        var requestBody = generatePostCreateRequestBody(createdUser.getId());
        var createdPost = postService.createPost(requestBody)
                                    .then()
                                    .spec(responseSpecificationHttpStatusCreated)
                                    .extract()
                                    .as(PostResponse.class);
        checkCreatePost(requestBody, createdPost);

        var postResponse = postService.getPost(createdPost.getId())
                                         .then()
                                         .spec(responseSpecificationHttpStatusOk)
                                         .extract()
                                         .as(PostResponse.class);
        checkGetPost(createdPost, postResponse);
    }

    @Tag(Tags.SUITE_TAG_NAME)
    @Test
    void putPost() {
        var requestUserBody = generateUserCreateRequestBody();
        var createdUser = userService.createUser(requestUserBody)
                                     .then()
                                     .spec(responseSpecificationHttpStatusCreated)
                                     .extract()
                                     .as(UserResponse.class);

        var firstRequestBody = generatePostCreateRequestBody(createdUser.getId());
        var createPost = postService.createPost(firstRequestBody)
                                    .then()
                                    .spec(responseSpecificationHttpStatusCreated)
                                    .extract()
                                    .as(PostResponse.class);

        var secondRequestBody = generatePutRequestBody(createdUser.getId());
        var putPost = postService
            .putPost(secondRequestBody, createPost.getId().toString(), createdUser.getName(), createdUser.getEmail())
                                 .then()
                                 .spec(responseSpecificationHttpStatusOk)
                                 .extract()
                                 .as(PostResponse.class);
        checkPutPost(createPost, putPost);
    }

    @Tag(Tags.SUITE_TAG_NAME)
    @Test
    void deletePost() {
        var requestUserBody = generateUserCreateRequestBody();
        var createdUser = userService.createUser(requestUserBody)
                                     .then()
                                     .spec(responseSpecificationHttpStatusCreated)
                                     .extract()
                                     .as(UserResponse.class);

        var requestPostBody = generatePostCreateRequestBody(createdUser.getId());
        var createdPost = postService.createPost(requestPostBody)
                                    .then()
                                    .spec(responseSpecificationHttpStatusCreated)
                                    .extract()
                                    .as(PostResponse.class);
        checkCreatePost(requestPostBody, createdPost);

        postService.deletePost(createdPost.getId().toString())
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

    public CreatePostRequest generatePostCreateRequestBody(Integer userId) {
        return CreatePostRequest
            .builder()
            .user_id(userId)
            .title(faker.lorem().words(3).toString())
            .body(faker.lorem().sentences(5).toString())
            .build();
    }

    public CreatePostRequest generatePutRequestBody(Integer userId) {
        return CreatePostRequest
            .builder()
            .title(faker.lorem().words(3).toString())
            .user_id(userId)
            .body(faker.lorem().sentences(5).toString())
            .build();
    }

    private void checkCreatePost(CreatePostRequest request, PostResponse actualResponse) {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualResponse.getId()).isNotNull();
            softAssertions.assertThat(actualResponse.getUser_id())
                          .isEqualTo(request.getUser_id());
            softAssertions.assertThat(actualResponse.getTitle())
                          .isEqualTo(request.getTitle());
            softAssertions.assertThat(actualResponse.getBody())
                          .isEqualTo(request.getBody());
        });
    }

    private void checkGetPost(PostResponse request, PostResponse actualResponse) {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualResponse.getId()).isNotNull();
            softAssertions.assertThat(actualResponse.getUser_id())
                          .isEqualTo(request.getUser_id());
            softAssertions.assertThat(actualResponse.getTitle())
                          .isEqualTo(request.getTitle());
            softAssertions.assertThat(actualResponse.getBody())
                          .isEqualTo(request.getBody());
        });
    }

    private void checkPutPost(PostResponse request, PostResponse actualResponse) {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualResponse.getId()).isEqualTo(request.getId());
            softAssertions.assertThat(actualResponse.getUser_id()).isEqualTo(request.getUser_id());
            softAssertions.assertThat(actualResponse.getTitle()).isNotEqualTo(request.getTitle());
            softAssertions.assertThat(actualResponse.getBody()).isNotEqualTo(request.getBody());
        });
    }
}
