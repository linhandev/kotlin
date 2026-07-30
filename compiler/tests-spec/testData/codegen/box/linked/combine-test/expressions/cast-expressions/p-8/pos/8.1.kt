// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 8 -> sentence 8
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: impossible as? yields null at runtime
 */

// TESTCASE NUMBER: 1
@Suppress("CAST_NEVER_SUCCEEDS")
fun test(): String? = 1 as? String

fun box(): String {
    if (test() != null) return "NOK"
    return "OK"
}
