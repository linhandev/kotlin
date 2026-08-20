// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 177 -> sentence 177
 * PRIMARY LINKS: inheritance, overriding -> paragraph 177 -> sentence 177
 *                inheritance, inheriting -> paragraph 177 -> sentence 177
 * NUMBER: 1
 * DESCRIPTION: class members are final by default, so override of a member that is not marked open is rejected in a class declaration
 */

// TESTCASE NUMBER: 1
open class DefaultFinal {
    fun compute(): Int = 1
}

class OverrideDefault : DefaultFinal() {
    <!OVERRIDING_FINAL_MEMBER!>override<!> fun compute(): Int = 2
}

// TESTCASE NUMBER: 2
open class Holder {
    val tag: Int = 1
}

class BadHolder : Holder() {
    <!OVERRIDING_FINAL_MEMBER!>override<!> val tag: Int = 2
}

// TESTCASE NUMBER: 3
open class Root {
    fun locked(): Int = 1
}

open class Middle : Root()

class Leaf : Middle() {
    <!OVERRIDING_FINAL_MEMBER!>override<!> fun locked(): Int = 2
}
