// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 346 -> sentence 346
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 346 -> sentence 346
 * NUMBER: 1
 * DESCRIPTION: precise type inference for a class with an annotation that has RUNTIME retention
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
@Retention(AnnotationRetention.RUNTIME)
annotation class MyAnnotation

@MyAnnotation
class MyClass(val x: Int)

fun case_1() {
    val instance = MyClass(42)
    instance checkType { check<MyClass>() }
    checkSubtype<MyClass>(instance)
}
