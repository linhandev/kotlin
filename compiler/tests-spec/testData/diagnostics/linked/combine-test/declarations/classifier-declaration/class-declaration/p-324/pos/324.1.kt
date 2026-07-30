// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 324 -> sentence 324
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 324 -> sentence 324
 * NUMBER: 1
 * DESCRIPTION: precise type inference for a class with an annotation on a method
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
annotation class MyAnnotation
class MyClass { @MyAnnotation fun foo(x: Int): Int = x + 1 }

fun case_1() {
    val instance = MyClass()
    instance.foo(1) checkType { check<Int>() }
    checkSubtype<Int>(instance.foo(1))
}
