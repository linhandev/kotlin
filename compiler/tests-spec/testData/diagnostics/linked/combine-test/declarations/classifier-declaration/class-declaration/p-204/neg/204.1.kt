// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 204 -> sentence 204
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 204 -> sentence 204
 *                inheritance, overriding -> paragraph 204 -> sentence 204
 * NUMBER: 1
 * DESCRIPTION: implementing the same abstract member inherited from two interfaces in a class declaration without override hides the supertype members (VIRTUAL_MEMBER_HIDDEN)
 */

// TESTCASE NUMBER: 1
interface AbstractA {
    fun f(): Int
}

interface AbstractB {
    fun f(): Int
}

class MissingOverride : AbstractA, AbstractB {
    fun <!VIRTUAL_MEMBER_HIDDEN!>f<!>(): Int = 1
}

// TESTCASE NUMBER: 2
interface LeftId {
    fun id(): String
}

interface RightId {
    fun id(): String
}

class HiddenId : LeftId, RightId {
    fun <!VIRTUAL_MEMBER_HIDDEN!>id<!>(): String = "x"
}

// TESTCASE NUMBER: 3
interface Pinger {
    fun ping(): Int
}

interface Ponger {
    fun ping(): Int
}

class HiddenPing : Pinger, Ponger {
    fun <!VIRTUAL_MEMBER_HIDDEN!>ping<!>(): Int = 0
}
