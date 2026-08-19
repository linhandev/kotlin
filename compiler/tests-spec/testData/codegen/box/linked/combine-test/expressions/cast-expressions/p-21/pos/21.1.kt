// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 21 -> sentence 21
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: Number as String then length throws CCE
 */

// TESTCASE NUMBER: 1
@Suppress("CAST_NEVER_SUCCEEDS")
fun test(x: Number): Int = (x as String).length

fun box(): String {
    try {
        test(1)
        return "NOK"
    } catch (_: ClassCastException) {
        return "OK"
    }
}
