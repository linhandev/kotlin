// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, linked-scopes -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: Derived641.greet() calls inherited super.greet() and appends !
 */

// TESTCASE NUMBER: 1
open class Base641 {
    open fun greet(): String = "hi"
}

class Derived641 : Base641() {
    override fun greet(): String = super.greet() + "!"
}

fun case1(d: Derived641): String = d.greet()
