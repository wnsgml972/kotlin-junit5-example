package meta

import domain.Calculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * 빌드할때 Tag 관련 옵션을 추가하여 특정 Tag 를 포함 or 제외 할 수 있다.
 * gradle clean test
 * Reference - https://www.mkyong.com/junit5/junit-5-tagging-and-filtering-tag-examples/
 * gradle 추가 및 설정이 존재하니 Reference 확인해야 한다.
 */
@Tag("branch-20") // 여기서도 사용 가능
class TagDemo {
    private val calculator = Calculator()

    /**
     * 사용 법 기본!
     */
    @Test
    @Tag("feature-168")
    fun test1Plus1() {
        assertEquals(2, 1 + 1)
    }

    /**
     * 심화 Annotation 직접 구현
     * @Fast 태그 추가 & @Test Annotation 분리
     */
    @Fast
    @Test
    fun tagAnnotationTest() {
        assertThat(2).isEqualTo(calculator.add(1, 1))
    }

    /**
     * @Fast 태그 추가 & @Test Annotation 결합
     */
    @FastTest
    fun integrationTagAnnotation() {
        assertThat(2).isEqualTo(calculator.add(1, 1))
    }

    @SlowTest
    fun testSlow() {
        assertEquals(2, 1 + 1)
    }
}

@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(
    AnnotationRetention.RUNTIME
)
@Tag("fast")
internal annotation class Fast

@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(
    AnnotationRetention.RUNTIME
)
@Tag("fast")
@Test
internal annotation class FastTest

@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(
    AnnotationRetention.RUNTIME
)
@Tag("slow")
@Test
internal annotation class SlowTest
