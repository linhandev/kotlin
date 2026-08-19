// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: type-system, type-kinds, flexible-types, platform-types -> paragraph 28 -> sentence 28
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 28 -> sentence 28
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: reified is-check works on values originating from platform types
 */

// TESTCASE NUMBER: 1
inline fun <reified T> isA56228(x: Any?): Boolean = x is T

fun box(): String {
    val name = System.getProperty("user.name")
    if (name != null && !isA56228<String>(name)) return "NOK"
    if (isA56228<String>(null)) return "NOK"
    return "OK"
}
