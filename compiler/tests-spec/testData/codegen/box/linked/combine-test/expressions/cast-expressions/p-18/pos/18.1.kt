// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: impossible Array cast throws CCE at runtime
 */

// TESTCASE NUMBER: 1
@Suppress("UNCHECKED_CAST")
fun test(): Array<String> = arrayOf(1) as Array<String>

fun box(): String {
    try {
        test()
        return "NOK"
    } catch (_: ClassCastException) {
        return "OK"
    }
}
