// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 341 -> sentence 341
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 341 -> sentence 341
 * NUMBER: 1
 * DESCRIPTION: precise type inference for an annotation class with an annotation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
annotation class MyAnnotation

@MyAnnotation
annotation class MyAnnotatedAnnotation(val message: String)

@MyAnnotatedAnnotation("hello")
class MyClass(val value: Int)

fun case_1() {
    val instance = MyClass(42)
    instance checkType { check<MyClass>() }
    checkSubtype<MyClass>(instance)
}
