package ru.levelp.at.homework6.service;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import ru.levelp.at.homework6.data.comments.CommentResponse;
import ru.levelp.at.homework6.data.comments.CreateCommentRequest;

@RequiredArgsConstructor
public class CommentService {
    private static final String TOKEN = "3b12d6374f03280c1cb77f63b920219765d1e7767e38c534815528b8f4a28a77";
    private static final String COMMENTS_ENDPOINT = "/comments";
    public static final String COMMENT_ENDPOINT = COMMENTS_ENDPOINT + "/{commentId}";
    private final RequestSpecification requestSpecification;

    public Response getAllComments() {
        return given(requestSpecification)
            .header("Authorization", "Bearer " + TOKEN)
            .when()
            .get(COMMENTS_ENDPOINT + "?page=1")
            .andReturn();
    }

    public Response createComment(CreateCommentRequest request) {
        return given(requestSpecification)
            .header("Authorization", "Bearer " + TOKEN)
            .body(request)
            .when()
            .post(COMMENTS_ENDPOINT)
            .andReturn();
    }

    public Response getComment(final String id) {
        return given(requestSpecification)
            .header("Authorization", "Bearer " + TOKEN)
            .when()
            .get(COMMENT_ENDPOINT, id)
            .andReturn();
    }

    public Response putComment(CommentResponse request, final String id) {
        return given(requestSpecification)
            .header("Authorization", "Bearer " + TOKEN)
            .body(request)
            .when()
            .put(COMMENT_ENDPOINT, id)
            .andReturn();
    }


    public Response deleteComment(final String id) {
        return given(requestSpecification)
            .header("Authorization", "Bearer " + TOKEN)
            .when()
            .delete(COMMENT_ENDPOINT, id)
            .andReturn();
    }
}
