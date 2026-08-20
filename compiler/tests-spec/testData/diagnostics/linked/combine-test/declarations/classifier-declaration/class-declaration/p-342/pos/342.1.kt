// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 342 -> sentence 342
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 342 -> sentence 342
 * NUMBER: 1
 * DESCRIPTION: precise type inference for a class with an annotation that has default parameter values
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
annotation class MyAnnotation(val value: String = "default", val count: Int = 0)

@MyAnnotation
class MyClassA(val x: Int)

@MyAnnotation("custom")
class MyClassB(val x: Int)

fun case_1() {
    val a = MyClassA(1)
    a checkType { check<MyClassA>() }
    checkSubtype<MyClassA>(a)

    val b = MyClassB(2)
    b checkType { check<MyClassB>() }
    checkSubtype<MyClassB>(b)
}
