package com.fastcampus.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@EntityListeners(AuditingEntityListener.class) // 해당 클래스에 오디팅 기능을 포함함.
@Entity
public class ArticleComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne(optional = false) 연관관계 매핑에서 다중성을 나타내는 어노테이션을 붙여야 한다.
    //다대일 관계일 때 ManyToOne을 붙이는 것이고, (optional = false)는 필수값이라는 뜻이다.
    @Setter @ManyToOne(optional = false) private Article article; // 게시글(id)
    //@Column은 객체 필드를 테이블의 컬럼에 매핑시켜주는 어노테이션입니다.
    @Setter @Column(nullable = false, length = 500) private String content; // 내용

    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt; // 생성 일시
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy; // 생성자
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; // 수정일시
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; // 수정자

    protected ArticleComment() {}

    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }
    public static ArticleComment of(Article article, String content) {
        return new ArticleComment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment)) return false;
        ArticleComment that = (ArticleComment) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
