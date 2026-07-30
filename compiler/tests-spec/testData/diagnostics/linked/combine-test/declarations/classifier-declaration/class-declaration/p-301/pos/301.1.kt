// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 301 -> sentence 301
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 301 -> sentence 301
 *                declarations, declaration-visibility -> paragraph 301 -> sentence 301
 * NUMBER: 1
 * DESCRIPTION: precise types for inner class can access private members of the outer instance
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer(private val secret: Int) {
    inner class Inner {
        fun get(): Int = secret
    }
}

fun case_1() {
    Outer(3).Inner().get() checkType { check<Int>() }
    checkSubtype<Int>(Outer(3).Inner().get())
}
