## JUnit5 Tutorial

## Requirements
- `jvm` >= `1.8`

## [Architecture](https://github.com/junit-team/junit5-workshop/blob/master/slides/05_modularization/modularization.md)
- Platform
    - 테스트를 실행해주는 런처 제공. TestEngine API 제공.
    - Maven / Gradle Plugins, JUnit4 Runner
- Jupiter
    - JUnit 5를 지원하는 TestEngine API 구현체
- Vintage 
    - JUnit 4와 3을 지원하는 TestEngine API 구현체

## [Index](https://github.com/NESOY/junit5-example/tree/master/src/test/java)
- [Standard](https://github.com/NESOY/junit5-example/tree/master/src/test/java/standard)
- [Assertions](https://github.com/NESOY/junit5-example/tree/master/src/test/java/assertions)
- [assumptions](https://github.com/NESOY/junit5-example/tree/master/src/test/java/assumptions)
- [meta](https://github.com/NESOY/junit5-example/tree/master/src/test/java/meta)
- [disabled](https://github.com/NESOY/junit5-example/tree/master/src/test/java/disabled)
- [displayname](https://github.com/NESOY/junit5-example/tree/master/src/test/java/displayname)
- [timeout](https://github.com/NESOY/junit5-example/tree/master/src/test/java/timeout)
- [order](https://github.com/NESOY/junit5-example/tree/master/src/test/java/order)
- [repeat](https://github.com/NESOY/junit5-example/tree/master/src/test/java/repeat)
- [test_info](https://github.com/NESOY/junit5-example/tree/master/src/test/java/test_info)
- [test_interface](https://github.com/NESOY/junit5-example/tree/master/src/test/java/test_interface)
- [parameterized](https://github.com/NESOY/junit5-example/tree/master/src/test/java/parameterized)
- [conditional](https://github.com/NESOY/junit5-example/tree/master/src/test/java/conditional)
- [extension_model](https://github.com/NESOY/junit5-example/tree/master/src/test/java/extension_model)
- [dynamic_test](https://github.com/NESOY/junit5-example/tree/master/src/test/java/dynamic_test)
- [parallel](https://github.com/NESOY/junit5-example/tree/master/src/test/java/parallel)


## [Best Practices](https://phauer.com/2018/best-practices-unit-testing-kotlin/)
### TL;DR

#### build.gradle.kts

- 많은 내용이 `build.gradle.kts`에 포함되어 있습니다.
- 주의 깊게 읽는 것이 좋습니다.

#### Test Container 사용

> 쓰는 게 문제가 아니라, 마이그레이션이 문제 될 수 있음.

- In Memory Database나, 실제 DB 말고 컨테이너 DB를 쓰는 것이 좋다.

#### 모든 테스트 클래스의 수명주기 기본값 변경

- 성능을 위해 사용

```kotlin
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // A new test instance will be created once per test class.
```

#### 백틱 및 @Nested내부 클래스 사용

- ``: 공백을 포함한 가독성 있는 메소드 이름 사용 가능
- `@Nested` 를 사용해 테스트 방법을 그룹화 할 수 있다.

```kotlin
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DesignControllerTest {
    @Nested
    inner class GetDesigns {
        @Test
        fun `all fields are included`() {}
        @Test
        fun `limit parameter`() {}
        @Test
        fun `filter parameter`() {}
    }
    @Nested
    inner class DeleteDesign {
        @Test
        fun `design is removed from db`() {}
        @Test
        fun `return 404 on invalid id parameter`() {}
        @Test
        fun `return 401 if not authorized`() {}
    }
}
```

#### 모의 처리

- kotlin에서는 기존 `mockito`에 버그 이슈가 있다고 함, [MockK](https://mockk.io/) 사용
```kotlin
val clientMock: UserClient = mockk()
val user = User(id = 1, name = "Ben")
every { clientMock.getUser(any()) } returns user
val daoMock: UserDAO = mockk(relaxed = true)

val scheduler = UserScheduler(clientMock, daoMock)
scheduler.start(1)

verifySequence {
    clientMock.getUser(1)
    daoMock.saveUser(user)
}
```

## JUnit5 Testing in IDE 
- [Intellij](https://blog.jetbrains.com/idea/2016/08/using-junit-5-in-intellij-idea/)
- [vscode](https://code.visualstudio.com/docs/java/java-testing)

### Reference
- <https://junit.org/junit5/docs/current/user-guide/>
- [assertThat Dependency 없음(Hamcrest, AssertJ)](https://github.com/junit-team/junit5/issues/147)
- [Support JUnit 5 in SpringBoot Version 2.2](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.2-Release-Notes#junit-5)
- [JUnit5 in Spring - Eddy Kim님](https://brunch.co.kr/@springboot/77)