// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 312 -> sentence 312
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 312 -> sentence 312
 * NUMBER: 1
 * DESCRIPTION: precise types for this@outer inside an inner class refers to the same outer instance
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    inner class Inner {
        fun ref(): Outer = this@Outer
    }

    fun same(): Boolean {
        val o = this
        return o.Inner().ref() === o
    }
}

fun case_1() {
    Outer().same() checkType { check<Boolean>() }
    Outer().Inner().ref() checkType { check<Outer>() }
}
