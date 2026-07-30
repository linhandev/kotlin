// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 14 -> sentence 14
 *                inheritance, overriding -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: implementing class inheriting two interfaces with same-named default function bodies must explicitly override (MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED)
 */

// TESTCASE NUMBER: 1
interface DefaultA {
    fun f(): Int = 1
}

interface DefaultB {
    fun f(): Int = 2
}

<!MANY_INTERFACES_MEMBER_NOT_IMPLEMENTED!>class ConflictUnresolved<!> : DefaultA, DefaultB
