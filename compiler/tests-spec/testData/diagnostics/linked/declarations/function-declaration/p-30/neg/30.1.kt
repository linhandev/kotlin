// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: function body cannot access private members from outside their declaring classifier
 */

// TESTCASE NUMBER: 1
class C {
    private fun secret(): Int = 1
}

fun readFromFunctionBody(c: C): Int {
    fun inner(): Int = c.<!INVISIBLE_MEMBER!>secret<!>()
    return inner()
}
