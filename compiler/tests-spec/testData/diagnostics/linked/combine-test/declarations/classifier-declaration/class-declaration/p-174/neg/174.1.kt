// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 174 -> sentence 174
 * PRIMARY LINKS: inheritance, overriding -> paragraph 174 -> sentence 174
 *                declarations, classifier-declaration, interface-declaration -> paragraph 174 -> sentence 174
 *                inheritance, inheriting -> paragraph 174 -> sentence 174
 * NUMBER: 1
 * DESCRIPTION: implementing interface abstract members in a class declaration requires override; omitting it hides the member
 */

// TESTCASE NUMBER: 1
interface I {
    fun f(): Int
}

class C : I {
    fun <!VIRTUAL_MEMBER_HIDDEN!>f<!>(): Int = 1
}

// TESTCASE NUMBER: 2
interface Named {
    fun name(): String
}

open class Base

class NamedChild : Base(), Named {
    fun <!VIRTUAL_MEMBER_HIDDEN!>name<!>(): String = "x"
}

// TESTCASE NUMBER: 3
interface Left {
    fun left(): Int
}

interface Right {
    fun right(): Int
}

class Both : Left, Right {
    fun <!VIRTUAL_MEMBER_HIDDEN!>left<!>(): Int = 1
    fun <!VIRTUAL_MEMBER_HIDDEN!>right<!>(): Int = 2
}
