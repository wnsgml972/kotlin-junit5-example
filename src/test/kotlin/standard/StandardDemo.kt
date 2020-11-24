package standard

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

/**
 * Annotation Naming이 변경되었습니다.
 */
internal class StandardDemo {
    /**
     * junit 4 - @Before
     * junit 5 - @BeforeEach
     */
    @BeforeEach
    fun init() {
    }

    @Test
    fun succeedingTest() {
    }

    @Test
    fun failingTest() {
        Assertions.fail<Any>("a failing test")
    }

    /**
     * junit 4 - @Ignore
     * junit 5 - @Disabled
     */
    @Test
    @Disabled("for demonstration purposes")
    fun skippedTest() {
        // not executed
    }

    @Test
    fun abortedTest() {
        Assumptions.assumeTrue("abc".contains("Z"))
        Assertions.fail<Any>("test should have been aborted")
    }

    /**
     * junit 4 - @After
     * junit 5 - @AfterEach
     */
    @AfterEach
    fun tearDown() {
    }

    companion object {
        /**
         * junit 4 - @BeforeClass
         * junit 5 - @BeforeAll
         */
        @BeforeAll
        fun initAll() {
        }

        /**
         * junit 4 - @AfterClass
         * junit 5 - @AfterAll
         */
        @AfterAll
        fun tearDownAll() {
        }
    }
}