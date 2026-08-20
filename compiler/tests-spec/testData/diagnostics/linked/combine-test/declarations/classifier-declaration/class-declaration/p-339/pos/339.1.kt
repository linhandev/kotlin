// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 339 -> sentence 339
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 339 -> sentence 339
 * NUMBER: 1
 * DESCRIPTION: precise type inference for a sealed class with an annotation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
annotation class MyAnnotation

@MyAnnotation
sealed class MySealed {
    data class A(val value: Int) : MySealed()
    data class B(val text: String) : MySealed()
}

fun case_1() {
    val instance: MySealed = MySealed.A(42)
    instance checkType { check<MySealed>() }
    checkSubtype<MySealed>(instance)
}
