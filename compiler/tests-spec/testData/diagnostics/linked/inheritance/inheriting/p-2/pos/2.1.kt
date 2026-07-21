// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, inheriting -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: InheritRuleDerived532.foo() uses inherited open base implementation
 */

open class InheritRuleBase532 {
    open fun foo(): Int = 1
}

class InheritRuleDerived532 : InheritRuleBase532()

// TESTCASE NUMBER: 1
fun case1(d: InheritRuleDerived532): Int {
    return d.foo()
}
