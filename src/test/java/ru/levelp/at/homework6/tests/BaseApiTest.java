package ru.levelp.at.homework6.tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.levelp.at.homework6.service.CommentService;
import ru.levelp.at.homework6.service.PostService;
import ru.levelp.at.homework6.service.UserService;

public class BaseApiTest {
    private static final String SERVICE_BASE_URI = "https://gorest.co.in";
    private static final String SERVICE_BASE_PATH = "/public/v2";
    protected Faker faker = new Faker();
    protected RequestSpecification requestSpecification;
    protected ResponseSpecification responseSpecificationHttpStatusOk;
    protected ResponseSpecification responseSpecificationHttpStatusCreated;
    protected ResponseSpecification responseSpecificationHttpStatusDelete;
    protected UserService userService;
    protected PostService postService;
    protected CommentService commentService;

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = SERVICE_BASE_URI;
        RestAssured.basePath = SERVICE_BASE_PATH;
    }

    @BeforeEach
    void setUp() {
        requestSpecification = new RequestSpecBuilder()
            .log(LogDetail.ALL)
            .setContentType(ContentType.JSON)
            .build();

        responseSpecificationHttpStatusOk = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

        responseSpecificationHttpStatusCreated = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_CREATED)
            .build();

        responseSpecificationHttpStatusDelete = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_NO_CONTENT)
            .build();

        userService = new UserService(requestSpecification);
        postService = new PostService(requestSpecification);
        commentService = new CommentService(requestSpecification);
    }
}
