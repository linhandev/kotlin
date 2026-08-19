// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: abstract class object literal implements all abstract members
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
abstract class A {
    abstract fun f(): Int
}

fun case_1(): Int = object : A() {
    override fun f(): Int = 1
}.f()

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
