package com.gdsc.project_bare_ground_heading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectBareGroundHeadingApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProjectBareGroundHeadingApplication.class, args);
  }
}

/** TODO: 기본적인 세팅하기
 *  1. spotless 세팅
 *  2. 도커 세팅
 *  3. 깃헙 액션으로 pre-commit 세팅하기
 *    a. pre-commit
 *    b. 디코에 연결하기
 */
