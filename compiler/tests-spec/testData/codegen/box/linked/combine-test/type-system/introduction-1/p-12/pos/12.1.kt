// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: expressions, cast-expressions -> paragraph 12 -> sentence 12
 *                declarations, function-declaration -> paragraph 12 -> sentence 12
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: inline reified cast throws ClassCastException on type mismatch
 */

// TESTCASE NUMBER: 1
inline fun <reified T> cast56212(x: Any): T = x as T

fun box(): String {
    return try {
        cast56212<Int>("s")
        "NOK"
    } catch (e: ClassCastException) {
        "OK"
    }
}
