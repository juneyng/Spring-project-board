package com.fastcampus.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing // 엔티티 객체가 생성이 되거나 변경이 되었을 때 @EnableJpaAuditing 어노테이션을 활용하여 자동으로 값을 등록할 수 있습니다.
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> autditorAware(){
        return () -> Optional.of("uno"); // TODO : 스프링 시큐리티로 인증기능을 붙이게 할 때, 수정하자.
    }
}
