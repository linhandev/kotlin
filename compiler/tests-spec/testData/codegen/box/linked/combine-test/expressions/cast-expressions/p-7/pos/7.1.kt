// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: impossible as throws CCE at runtime
 */

// TESTCASE NUMBER: 1
@Suppress("CAST_NEVER_SUCCEEDS")
fun test(): String = 1 as String

fun box(): String {
    try {
        test()
        return "NOK"
    } catch (_: ClassCastException) {
        return "OK"
    }
}
