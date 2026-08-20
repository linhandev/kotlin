// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 206 -> sentence 206
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 206 -> sentence 206
 *                inheritance, overriding -> paragraph 206 -> sentence 206
 * NUMBER: 1
 * DESCRIPTION: a class declaration inheriting two interfaces that each provide a default implementation for the same member must explicitly override (MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED); covers fun defaults, val getter defaults, and multi-member dual defaults
 */

// TESTCASE NUMBER: 1
interface DefaultLeft {
    fun f(): Int = 1
}

interface DefaultRight {
    fun f(): Int = 2
}

<!MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED!>class DualDefaultConflict<!> : DefaultLeft, DefaultRight

// TESTCASE NUMBER: 2
interface PropLeft {
    val n: Int get() = 1
}

interface PropRight {
    val n: Int get() = 2
}

<!MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED!>class DualPropConflict<!> : PropLeft, PropRight

// TESTCASE NUMBER: 3
interface MixA {
    fun a(): Int = 1
    fun b(): Int = 10
}

interface MixB {
    fun a(): Int = 2
    fun b(): Int = 20
}

<!MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED!>class DualMixConflict<!> : MixA, MixB
