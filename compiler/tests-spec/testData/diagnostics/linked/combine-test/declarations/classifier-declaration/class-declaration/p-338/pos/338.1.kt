// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 338 -> sentence 338
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 338 -> sentence 338
 * NUMBER: 1
 * DESCRIPTION: precise type inference for a data class with an annotation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
annotation class MyAnnotation

@MyAnnotation
data class MyData(val x: Int, val y: String)

fun case_1() {
    val instance = MyData(42, "hello")
    instance checkType { check<MyData>() }
    checkSubtype<MyData>(instance)
}
