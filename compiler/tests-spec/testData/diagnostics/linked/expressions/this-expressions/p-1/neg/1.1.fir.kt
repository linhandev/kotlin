// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, this-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: this@B inside C.run lambda in B.foo extension reports UNRESOLVED_REFERENCE when B is not enclosing classifier
 */

// TESTCASE NUMBER: 1
interface B

object C {
    fun run(block: C.() -> Unit) {
        block()
    }
}

class A {
    fun B.foo() {
        C.run {
            val x = this<!UNRESOLVED_LABEL!>@B<!>
        }
    }
}
