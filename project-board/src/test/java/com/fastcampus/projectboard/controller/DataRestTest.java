package com.fastcampus.projectboard.controller;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled("Spring Data REST 통합테스트는 불필요함.")
@DisplayName("Data REST-API Test")
@Transactional // DB에 영향을 주는 Test가 되지 않도록 하는 것이다.트랜잭셔널의 기본 동작은 RollBack이다.
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {

    private final MockMvc mvc; // 애플리케이션을 배포하지 않고도, 서버의 MVC 동작을 테스트 할 수 있는 라이브러리이다.

    public DataRestTest(@Autowired MockMvc mvc){
        this.mvc = mvc;
    }

    @DisplayName("[api] 게시글 리스트 조회")
    @Test
    void test() throws Exception {
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

}
