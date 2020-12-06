package assumptions

import domain.Calculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.Test

/**
 * assume : 특정 환경에서만 테스트가 실행 될 수 있는 환경을 만들 수 있음.
 */
internal class AssumptionsDemo {
    private val calculator = Calculator()

    /**
     * 특정 환경의 경우에만 Test 동작할 수 있도록
     */
    @Test
    fun testOnlyOnCiServer() {
        Assumptions.assumeTrue("CI" == System.getenv("ENV"))
        assertThat(1).isEqualTo(1) // 따로 gradle 에서 추가가 필요하다.
    }

    /**
     * Message 전달도 가능
     */
    @Test
    fun testOnlyOnDeveloperWorkstation() {
        Assumptions.assumeTrue("DEV" == System.getenv("ENV")) {
            "Aborting test: not on developer workstation"
        }
    }

    /**
     * 통합환경 TEST + CI 전용 테스트 작성도 가능
     * env - System에 의존적
     * property - JVM 환경에 의존적
     */
    @Test
    fun testInAllEnvironments() {
        println(System.getenv())
        // System.setProperty("ENV", "CI");

        Assumptions.assumingThat("CI" == System.getProperty("ENV")) {
            Assertions.assertEquals(4, calculator.divide(4, 2))
        }

        Assertions.assertEquals(42, calculator.multiply(6, 7))
    }
}