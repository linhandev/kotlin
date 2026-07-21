// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: scopes-and-identifiers, identifiers-and-paths -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: super<Base642>.value() in Derived642 calls base implementation
 */

// TESTCASE NUMBER: 1
open class Base642 {
    open fun value(): Int = 1
}

class Derived642 : Base642() {
    override fun value(): Int = super<Base642>.value() + 10
}

fun case1(d: Derived642): Int = d.value()
