// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 340 -> sentence 340
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 340 -> sentence 340
 * NUMBER: 1
 * DESCRIPTION: precise type inference for an inline value class with an annotation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
annotation class MyAnnotation

@MyAnnotation
@JvmInline
value class MyValue(val x: Int) {
    fun compute(): Int = x * 2
}

fun case_1() {
    val instance = MyValue(21)
    instance checkType { check<MyValue>() }
    checkSubtype<MyValue>(instance)
}
