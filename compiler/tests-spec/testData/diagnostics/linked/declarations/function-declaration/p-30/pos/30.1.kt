// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: function body scope may access outer variables and enclosing classifier members
 */

// TESTCASE NUMBER: 1
private val topLevelValue = 10

class Holder(val member: Int) {
    fun compute(): Int {
        fun inner(): Int = topLevelValue + member
        return inner()
    }
}

// TESTCASE NUMBER: 2
fun outerFunction(base: Int): Int {
    fun inner(): Int = base + 1
    return inner()
}
