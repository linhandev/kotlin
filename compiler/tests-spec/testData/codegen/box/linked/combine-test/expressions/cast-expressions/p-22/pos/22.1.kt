// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: String literal as Int throws CCE
 */

// TESTCASE NUMBER: 1
@Suppress("CAST_NEVER_SUCCEEDS")
fun test(): Int = "a" as Int

fun box(): String {
    try {
        test()
        return "NOK"
    } catch (_: ClassCastException) {
        return "OK"
    }
}
