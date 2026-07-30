// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 297 -> sentence 297
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 297 -> sentence 297
 * NUMBER: 1
 * DESCRIPTION: precise types for this@outer and inner this refer to different receivers
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    inner class Inner {
        fun same(): Boolean = (this@Outer as Any) !== (this as Any)
    }
}

fun case_1() {
    Outer().Inner().same() checkType { check<Boolean>() }
    checkSubtype<Boolean>(Outer().Inner().same())
}
