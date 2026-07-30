// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 311 -> sentence 311
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 311 -> sentence 311
 *                type-system, introduction-1 -> paragraph 311 -> sentence 311
 * NUMBER: 1
 * DESCRIPTION: precise types for nullable outer instance allows safe inner class construction
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    inner class Inner(val v: Int)
}

fun test(o: Outer?): Int? = o?.Inner(1)?.v

fun case_1() {
    test(null) checkType { check<Int?>() }
    test(Outer()) checkType { check<Int?>() }
    checkSubtype<Int?>(test(Outer()))
}
