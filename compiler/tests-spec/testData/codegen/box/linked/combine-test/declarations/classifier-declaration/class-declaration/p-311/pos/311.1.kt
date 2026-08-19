/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 311 -> sentence 311
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 311 -> sentence 311
 *                type-system, introduction-1 -> paragraph 311 -> sentence 311
 * NUMBER: 1
 * DESCRIPTION: nullable outer instance allows safe inner class construction
 */

// TESTCASE NUMBER: 1
class Outer {
    inner class Inner(val v: Int)
}

fun test(o: Outer?): Int? = o?.Inner(1)?.v

fun box(): String {
    if (test(null) != null) return "NOK: null"
    if (test(Outer()) != 1) return "NOK: non-null"
    if (Outer().Inner(5).v != 5) return "NOK: direct"
    return "OK"
}
