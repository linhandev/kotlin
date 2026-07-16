// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, matching-and-subsumption-of-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: override val on base fun; foo with String param; bar with extra Int param report NOTHING_TO_OVERRIDE
 */

// TESTCASE NUMBER: 1
open class KindBase520 {
    open fun foo(): Int = 1
}

class KindDerived520 : KindBase520() {
    <!NOTHING_TO_OVERRIDE!>override<!> val foo: Int = 1
}

// TESTCASE NUMBER: 2
open class ParamBase520 {
    open fun foo(x: Int): String = ""
}

class BadParam520 : ParamBase520() {
    <!NOTHING_TO_OVERRIDE!>override<!> fun foo(x: String): String = ""
}

// TESTCASE NUMBER: 3
open class CountBase520 {
    open fun bar(): Int = 1
}

class BadCount520 : CountBase520() {
    <!NOTHING_TO_OVERRIDE!>override<!> fun bar(x: Int): Int = 1
}
