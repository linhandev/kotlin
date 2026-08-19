// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 3 -> sentence 3
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: null as String throws NPE at runtime
 */

// TESTCASE NUMBER: 1
@Suppress("CAST_NEVER_SUCCEEDS")
fun test(): String = null as String

fun box(): String {
    try {
        test()
        return "NOK"
    } catch (_: NullPointerException) {
        return "OK"
    }
}
