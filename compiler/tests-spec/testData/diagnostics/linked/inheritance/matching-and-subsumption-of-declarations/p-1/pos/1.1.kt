// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, matching-and-subsumption-of-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: MatchDerived520 overrides foo(x: Int) and bar with same signatures as base
 */

open class MatchBase520 {
    open fun foo(x: Int): String = ""
    open val bar: Int get() = 1
}

class MatchDerived520 : MatchBase520() {
    override fun foo(x: Int): String = "ok"
    override val bar: Int get() = 2
}

// TESTCASE NUMBER: 1
fun case1(d: MatchDerived520): String = d.foo(1)
