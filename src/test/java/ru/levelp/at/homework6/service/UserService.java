package ru.levelp.at.homework6.service;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import ru.levelp.at.homework6.data.users.CreateUserRequest;

@RequiredArgsConstructor
public class UserService {
    private static final String TOKEN = "3b12d6374f03280c1cb77f63b920219765d1e7767e38c534815528b8f4a28a77";
    private static final String USERS_ENDPOINT = "/users";
    public static final String USER_ENDPOINT = USERS_ENDPOINT + "/{userId}";
    private final RequestSpecification requestSpecification;

    public Response getAllUsers() {
        return given(requestSpecification)
            .header("Authorization", "Bearer " + TOKEN)
            .when()
            .get(USERS_ENDPOINT + "?page=1")
            .andReturn();
    }

    public Response createUser(CreateUserRequest request) {
        return given(requestSpecification)
            .header("Authorization", "Bearer " + TOKEN)
            .body(request)
            .when()
            .post(USERS_ENDPOINT)
            .andReturn();
    }

    public Response putUser(CreateUserRequest request, final String id) {
        return given(requestSpecification)
            .header("Authorization", "Bearer " + TOKEN)
            .body(request)
            .when()
            .put(USER_ENDPOINT, id)
            .andReturn();
    }

    public Response getUser(final String id) {
        return given(requestSpecification)
            .header("Authorization", "Bearer " + TOKEN)
            .when()
            .get(USER_ENDPOINT, id)
            .andReturn();
    }

    public Response deleteUser(final String id) {
        return given(requestSpecification)
            .header("Authorization", "Bearer " + TOKEN)
            .when()
            .delete(USER_ENDPOINT, id)
            .andReturn();
    }
}
