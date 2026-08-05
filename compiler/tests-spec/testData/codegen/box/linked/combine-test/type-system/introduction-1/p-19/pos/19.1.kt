// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 19 -> sentence 19
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: safe call on platform type avoids NPE when the value is null
 */

// TESTCASE NUMBER: 1
fun need56219(s: String): Int = s.length

fun test56219(): Int = System.getProperty("no.such.prop.abc.56219")?.let { need56219(it) } ?: 0

fun box(): String {
    if (test56219() != 0) return "NOK"
    return "OK"
}
