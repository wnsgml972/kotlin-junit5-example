package disabled

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

/**
 * JUnit4 - @Ignore
 */
@Disabled("Disabled until bug #99 has been fixed")
internal class DisabledClassDemo {
    @Test
    fun testWillBeSkipped() {
    }

    @Test
    fun failTestisNotSkipped() {
        Assertions.assertEquals(1, 2)
    }
}