// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 336 -> sentence 336
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 336 -> sentence 336
 * NUMBER: 1
 * DESCRIPTION: precise type inference for a nested class with an annotation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
annotation class MyAnnotation

class Outer {
    @MyAnnotation
    class Nested(val value: Int) {
        fun compute(): Int = value * 2
    }
}

fun case_1() {
    val instance = Outer.Nested(21)
    instance checkType { check<Outer.Nested>() }
    checkSubtype<Outer.Nested>(instance)
}
