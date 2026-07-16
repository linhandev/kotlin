// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, resolving-callable-references-not-used-as-arguments-to-a-call -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: invoke operator convention does not apply to callable reference candidates
 */

object Holder1161N {
    operator fun invoke(x: Int): Int = x
}

// TESTCASE NUMBER: 1
fun case_1(): (Int) -> Int = ::<!UNRESOLVED_REFERENCE!>Holder1161N<!>
