// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 330 -> sentence 330
 * PRIMARY LINKS: annotations, annotation-use-site-targets -> paragraph 330 -> sentence 330
 * NUMBER: 1
 * DESCRIPTION: precise type inference for a class with @receiver: use-site target annotation on an extension function
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
annotation class MyAnnotation
class MyClass {
    fun @receiver:MyAnnotation String.ext(): Int = this.length
}

fun case_1() {
    val instance = MyClass()
    instance.run { "hello".ext() } checkType { check<Int>() }
    checkSubtype<Int>(instance.run { "test".ext() })
}
