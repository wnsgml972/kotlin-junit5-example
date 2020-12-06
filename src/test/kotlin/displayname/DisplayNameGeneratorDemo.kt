package displayname

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.reflect.Method

/**
 * Junit5에서는 Display Name을 유연하게 생성할 수 있다.
 * 근데 그냥 백틱 쓰는 게 베스트 프렉티스 인 것 같다.
 */
internal class DisplayNameGeneratorDemo {
    /**
     * [DisplayNameGenerator.Standard]
     * [DisplayNameGenerator.ReplaceUnderscores] -- '_'를 Space로 변경
     * 기본적으로 Method 이름만 적용
     * DisplayName, ParameterizedTest 에는 적용되지 않음.
     */
    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
    internal inner class A_year_is_not_supported {
        @Test
        fun if_it_is_zero() {
        }

        @Test
        @DisplayName("if_it_is_one")
        fun if_it_is_one() {
        }

        @DisplayName("A negative value for year is not supported by the leap year computation.")
        @ParameterizedTest(name = "For example, year {0} is not supported.")
        @ValueSource(ints = [-1, -4])
        fun if_it_is_negative(year: Int) {
        }
    }

    /**
     * Custom TestCase Name 생성자도 만들 수 있음.
     */
    @Nested
    @DisplayNameGeneration(IndicativeSentences::class)
    internal inner class A_year_is_a_leap_year {
        @Test
        fun if_it_is_divisible_by_4_but_not_by_100() {
        }

        @ParameterizedTest(name = "Year {0} is a leap year.")
        @ValueSource(ints = [2016, 2020, 2048])
        fun if_it_is_one_of_the_following_years(year: Int) {
        }
    }

    internal class IndicativeSentences : DisplayNameGenerator.ReplaceUnderscores() {
        override fun generateDisplayNameForClass(testClass: Class<*>?): String {
            return super.generateDisplayNameForClass(testClass)
        }

        override fun generateDisplayNameForNestedClass(nestedClass: Class<*>?): String {
            return super.generateDisplayNameForNestedClass(nestedClass).toString() + "..."
        }

        override fun generateDisplayNameForMethod(testClass: Class<*>, testMethod: Method): String {
            val name = testClass.simpleName + ' ' + testMethod.name
            return name.replace('_', ' ') + '.'
        }
    }
}