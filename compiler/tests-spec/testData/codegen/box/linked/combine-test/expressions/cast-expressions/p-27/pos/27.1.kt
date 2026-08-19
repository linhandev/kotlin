// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: reified inline as checks runtime type
 */

// TESTCASE NUMBER: 1
inline fun <reified T> cast(x: Any): T = x as T

fun test(): Int = cast<Int>(1)

fun box(): String {
    if (test() != 1) return "NOK"
    try {
        cast<Int>("x")
        return "NOK"
    } catch (_: ClassCastException) {
    }
    return "OK"
}
