// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, inheriting -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: Satisfied533 inherits concrete foo() from ConcreteBase533 satisfying Interface533
 */

open class ConcreteBase533 {
    fun foo(): Int = 1
}

interface AbstractIface533 {
    fun foo(): Int
}

class Satisfied533 : ConcreteBase533(), AbstractIface533

// TESTCASE NUMBER: 1
fun case1(s: Satisfied533): Int = s.foo()
