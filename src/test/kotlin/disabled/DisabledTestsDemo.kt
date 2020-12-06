package disabled

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class DisabledTestsDemo {
    @Disabled("Disabled until bug #42 has been resolved")
    @Test
    fun testWillBeSkipped() {
    }

    /**
     * 해당 테스트는 실행됩니다.
     */
    @Test
    fun testWillBeExecuted() {
    }
}
