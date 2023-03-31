package ru.levelp.at.homework6.service;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import ru.levelp.at.homework6.data.posts.CreatePostRequest;

@RequiredArgsConstructor
public class PostService {
    private static final String TOKEN = "3b12d6374f03280c1cb77f63b920219765d1e7767e38c534815528b8f4a28a77";
    private static final String POSTS_ENDPOINT = "/posts";
    private static final String POST_ENDPOINT = "posts/{postId}";
    private final RequestSpecification requestSpecification;

    public Response getAllPosts() {
        return given(requestSpecification)
            .header("Authorization", "Bearer " + TOKEN)
            .when()
            .get(POSTS_ENDPOINT + "?page=1")
            .andReturn();
    }

    public Response createPost(CreatePostRequest request) {
        return given(requestSpecification)
            .header("Authorization", "Bearer " + TOKEN)
            .body(request)
            .when()
            .post(POSTS_ENDPOINT)
            .andReturn();
    }

    public Response getPost(Integer postId) {
        return given(requestSpecification)
            .header("Authorization", "Bearer " + TOKEN)
            .when()
            .get(POST_ENDPOINT, postId)
            .andReturn();
    }

    public Response putPost(CreatePostRequest request, final String id, String name, String email) {
        return given(requestSpecification)
            .header("Authorization", "Bearer " + TOKEN)
            .body(request)
            .when()
            .put(POST_ENDPOINT, id)
            .andReturn();
    }

    public Response deletePost(final String id) {
        return given(requestSpecification)
            .header("Authorization", "Bearer " + TOKEN)
            .when()
            .delete(POST_ENDPOINT, id)
            .andReturn();
    }
}
