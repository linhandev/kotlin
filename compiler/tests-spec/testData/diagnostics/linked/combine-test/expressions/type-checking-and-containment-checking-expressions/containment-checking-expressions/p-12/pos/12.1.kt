// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: in operator prefers local Int contains extension over package-level contains and infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
operator fun Int.contains(other: Int): Boolean = this <= other

fun case1() {
    fun test(x: Int): Boolean {
        operator fun Int.contains(other: Int): Boolean = this > other
        return 5 in x
    }
    checkSubtype<Boolean>(test(10))
}
