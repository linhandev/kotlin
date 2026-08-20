// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 349 -> sentence 349
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 349 -> sentence 349
 * NUMBER: 1
 * DESCRIPTION: precise type inference for a class with an annotated companion object
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
annotation class MyAnnotation

class MyClass(val x: Int) {
    @MyAnnotation companion object
}

fun case_1() {
    val instance = MyClass(42)
    instance checkType { check<MyClass>() }
    checkSubtype<MyClass>(instance)
}
