// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 331 -> sentence 331
 * PRIMARY LINKS: annotations, annotation-use-site-targets -> paragraph 331 -> sentence 331
 * NUMBER: 1
 * DESCRIPTION: precise type inference for a class with @delegate: use-site target annotation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
annotation class MyAnnotation
class MyClass { @delegate:MyAnnotation val x: String by lazy { "hello" } }

fun case_1() {
    val instance = MyClass()
    instance.x checkType { check<String>() }
    checkSubtype<String>(instance.x)
}
