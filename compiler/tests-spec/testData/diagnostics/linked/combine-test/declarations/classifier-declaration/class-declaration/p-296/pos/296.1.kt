// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 296 -> sentence 296
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 296 -> sentence 296
 * NUMBER: 1
 * DESCRIPTION: precise types for inner class implicitly holds a reference to the outer instance
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer(val tag: String) {
    inner class Inner {
        fun outerTag(): String = this@Outer.tag
    }
}

fun case_1() {
    Outer("x").Inner().outerTag() checkType { check<String>() }
    checkSubtype<String>(Outer("x").Inner().outerTag())
}
