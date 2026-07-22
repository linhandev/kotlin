// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, callable-references -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: member reference O::a on object singleton O reports NONE_APPLICABLE
 */

object O {
    val a: Int = 42
    fun a(): String = "x"
}

// TESTCASE NUMBER: 1
fun case1() {
    val ref: (O) -> Int = O::<!NONE_APPLICABLE!>a<!>
}
