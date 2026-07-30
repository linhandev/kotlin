// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 337 -> sentence 337
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 337 -> sentence 337
 * NUMBER: 1
 * DESCRIPTION: precise type inference for an enum class with an annotation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
annotation class MyAnnotation

@MyAnnotation
enum class MyEnum(val value: Int) {
    A(1),
    B(2);

    fun compute(): Int = value * 2
}

fun case_1() {
    val instance = MyEnum.A
    instance checkType { check<MyEnum>() }
    checkSubtype<MyEnum>(instance)
}
