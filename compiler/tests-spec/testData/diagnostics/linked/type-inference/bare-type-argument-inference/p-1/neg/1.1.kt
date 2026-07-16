// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, bare-type-argument-inference -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: bare type argument inference — bare inner type without subject type context cannot infer type arguments
 * HELPERS: checkType
 */

class Outer144<E> {
    inner open class InnerBase144<F>
    inner class Inner144<H> : InnerBase144<H>()
}

// TESTCASE NUMBER: 1
fun case_1(y: Any?) {
    if (y is <!NO_TYPE_ARGUMENTS_ON_RHS!>Outer144.Inner144<!>) {}
}
