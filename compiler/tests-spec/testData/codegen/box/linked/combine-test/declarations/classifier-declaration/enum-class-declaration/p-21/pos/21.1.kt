// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 21 -> sentence 21
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: single-constant enum when must still cover that constant
 */

// TESTCASE NUMBER: 1
enum class UnitE { ONLY }

fun test(e: UnitE): Int = when (e) {
    UnitE.ONLY -> 0
}

fun box(): String {
    if (test(UnitE.ONLY) != 0) return "NOK"
    return "OK"
}
