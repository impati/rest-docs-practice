package com.example.restdocspractice.integrationTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.preprocess.Preprocessors;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@ExtendWith(RestDocumentationExtension.class)
public class RestDocsIntegrationTest extends IntegrationTest {
    private RequestSpecification spec;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider provider) {
        super.setUp();
        this.spec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(provider))
                .build();
    }

    @Test
    @DisplayName("[put] [/api/v1/articles] 게시글 등록")
    void postArticlesTest() {

        ExtractableResponse<Response> response = RestAssured
                .given(this.spec).log().all()
                .accept("application/json")
                .filter(
                        document("index",
                                preprocessRequest(Preprocessors.prettyPrint()),
                                preprocessResponse(Preprocessors.prettyPrint()),
                                responseFields(
                                        fieldWithPath("status").description("응답 상태")
                                ))
                )
                .when().get("/api/v1/docs")
                .then().log().all()
                .extract();

    }

}
