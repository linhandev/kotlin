// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 335 -> sentence 335
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 335 -> sentence 335
 * NUMBER: 1
 * DESCRIPTION: precise type inference for an inner class with an annotation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
annotation class MyAnnotation

class Outer(val prefix: String) {
    @MyAnnotation
    inner class Inner(val value: Int) {
        fun message(): String = "$prefix: $value"
    }
}

fun case_1() {
    val outer = Outer("val")
    val instance = outer.Inner(42)
    instance checkType { check<Outer.Inner>() }
    checkSubtype<Outer.Inner>(instance)
}
