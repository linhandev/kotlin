// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, classifier-type-inheritance, inheritance-from-built-in-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: IntToInt513 implements (Int) -> Int and invoke adds one
 */

class IntToInt513 : (Int) -> Int {
    override fun invoke(p1: Int): Int = p1 + 1
}

// TESTCASE NUMBER: 1
fun case1(f: IntToInt513): Int = f(1)
