// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, inheriting -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: Resolved533 override foo() resolves Super533 and abstract Interface533
 */

open class Super533 {
    open fun foo(): Int = 1
}

interface Interface533 {
    fun foo(): Int
}

class Resolved533 : Super533(), Interface533 {
    override fun foo(): Int = 2
}

// TESTCASE NUMBER: 1
fun case1(c: Resolved533): Int = c.foo()
