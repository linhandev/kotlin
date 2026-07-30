// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, member-access -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 16 -> sentence 16
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 16 -> sentence 16
 *                expressions, cast-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: as? then safe call length three paths
 */

// TESTCASE NUMBER: 1
fun test(obj: Any?): Int? = (obj as? String)?.length

fun box(): String {
    if (test("hi") != 2) return "NOK"
    if (test(123) != null) return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
