// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, linked-scopes -> paragraph 3 -> sentence 3
 * NUMBER: 3
 * DESCRIPTION: Outer633.Inner.read() accesses outer private secret
 */

// TESTCASE NUMBER: 1
class Outer633 {
    private val secret = 42
    inner class Inner {
        fun read(): Int = secret
    }
}

fun case1(o: Outer633): Int = o.Inner().read()
