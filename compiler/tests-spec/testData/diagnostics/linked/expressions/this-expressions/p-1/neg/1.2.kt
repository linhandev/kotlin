// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, this-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: this@run inside lambda labeled label@ reports UNRESOLVED_REFERENCE when label does not match
 */

object C {
    fun run(block: C.() -> Unit) {
        block()
    }
}

// TESTCASE NUMBER: 1
fun case1() {
    C.run label@{
        val x = this<!UNRESOLVED_REFERENCE!>@run<!>
    }
}
