package ru.levelp.at.homework6.tests;


import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.levelp.at.homework6.data.users.CreateUserRequest;
import ru.levelp.at.homework6.data.users.UserListData;
import ru.levelp.at.homework6.data.users.UserResponse;
import ru.levelp.at.homework6.suits.Tags;

class UsersApiTest extends BaseApiTest {

    @Tag(Tags.SUITE_TAG_NAME)
    @Test
    void getAllUsers() {
        postService.getAllPosts()
                   .then()
                   .spec(responseSpecificationHttpStatusOk)
                   .extract()
                   .as(UserListData.class);
    }

    @Tag(Tags.SUITE_TAG_NAME)
    @Test
    void createUser() {
        var requestBody = generateUserCreateRequestBody();

        var createdUser = userService.createUser(requestBody)
                                    .then()
                                    .spec(responseSpecificationHttpStatusCreated)
                                    .extract()
                                    .as(UserResponse.class);
        checkCreateUser(requestBody, createdUser);
    }

    @Tag(Tags.SUITE_TAG_NAME)
    @Test
    void getUser() {
        var requestBody = generateUserCreateRequestBody();

        var createdUser = userService.createUser(requestBody)
                                        .then()
                                        .spec(responseSpecificationHttpStatusCreated)
                                        .extract()
                                        .as(UserResponse.class);
        checkCreateUser(requestBody, createdUser);
        var userResponse = userService.getUser(createdUser.getId().toString())
                                         .then()
                                         .spec(responseSpecificationHttpStatusOk)
                                         .extract()
                                         .as(UserResponse.class);
        checkGetUser(createdUser, userResponse);
    }

    @Tag(Tags.SUITE_TAG_NAME)
    @Test
    void putUser() {
        var firstBody = generateUserCreateRequestBody();

        var createdUser = userService.createUser(firstBody)
                                    .then()
                                    .spec(responseSpecificationHttpStatusCreated)
                                    .extract()
                                    .as(UserResponse.class);

        var secondBody = generateSecondUserCreateRequestBody();

        var changedUser = userService.putUser(secondBody, createdUser.getId().toString())
                                 .then()
                                 .spec(responseSpecificationHttpStatusOk)
                                 .extract()
                                 .as(UserResponse.class);
        checkPutUser(createdUser, changedUser);
    }

    @Tag(Tags.SUITE_TAG_NAME)
    @Test
    void deleteUser() {
        var requestBody = generateUserCreateRequestBody();

        var createdUser = userService.createUser(requestBody)
                                    .then()
                                    .spec(responseSpecificationHttpStatusCreated)
                                    .extract()
                                    .as(UserResponse.class);
        checkCreateUser(requestBody, createdUser);

        userService.deleteUser(createdUser.getId().toString())
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

    public CreateUserRequest generateSecondUserCreateRequestBody() {
        return CreateUserRequest
            .builder()
            .name(faker.name().fullName())
            .email(faker.internet().emailAddress())
            .gender(faker.dog().gender())
            .status("inactive")
            .build();
    }

    private void checkCreateUser(CreateUserRequest request, UserResponse actualResponse) {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualResponse.getId()).isNotNull();
            softAssertions.assertThat(actualResponse.getName())
                          .isEqualTo(request.getName());
            softAssertions.assertThat(actualResponse.getEmail())
                          .isEqualTo(request.getEmail());
            softAssertions.assertThat(actualResponse.getGender())
                          .isEqualTo(request.getGender());
            softAssertions.assertThat(actualResponse.getStatus())
                          .isEqualTo(request.getStatus());
        });
    }

    private void checkGetUser(UserResponse request, UserResponse actualResponse) {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualResponse.getId()).isNotNull();
            softAssertions.assertThat(actualResponse.getName())
                          .isEqualTo(request.getName());
            softAssertions.assertThat(actualResponse.getEmail())
                          .isEqualTo(request.getEmail());
            softAssertions.assertThat(actualResponse.getGender())
                          .isEqualTo(request.getGender());
            softAssertions.assertThat(actualResponse.getStatus())
                          .isEqualTo(request.getStatus());
        });
    }

    private void checkPutUser(UserResponse request, UserResponse actualResponse) {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualResponse.getId()).isEqualTo(request.getId());
            softAssertions.assertThat(actualResponse.getName()).isNotEqualTo(request.getName());
            softAssertions.assertThat(actualResponse.getEmail()).isNotEqualTo(request.getEmail());
            softAssertions.assertThat(actualResponse.getGender()).isNotEqualTo(request.getGender());
            softAssertions.assertThat(actualResponse.getStatus()).isNotEqualTo(request.getStatus());
        });
    }
}

