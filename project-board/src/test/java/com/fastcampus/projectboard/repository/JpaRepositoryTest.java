package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.config.JpaConfig;
import com.fastcampus.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName(("JPA 연결 테스트"))
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest
            (@Autowired ArticleRepository articleRepository,
             @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }
    @DisplayName("Insert Test")
    @Test
    void given_when_then_Inserting(){
        //given
        long previousCount = articleRepository.count();

        //when
        Article savedArticle = articleRepository.save(Article.of("new article","new content", "#spring"));

        //then
        assertThat(articleRepository.count()).isEqualTo(previousCount+1);
    }
    @DisplayName("Update Test")
    @Test
    void given_when_then_Updating(){
        //given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);

        //when
        Article savedArticle = articleRepository.saveAndFlush(article);

        //then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }
    @DisplayName("Delete Test")
    @Test
    void given_when_then_Deleting(){
        //given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommentsSize = article.getArticleComments().size();
        //when
        articleRepository.delete(article);

        //then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount-1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount-deletedCommentsSize);
    }
}