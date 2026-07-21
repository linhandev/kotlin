// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, type-constraint-definition -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: type constraint T <: U is unsound when argument type is not a subtype of U
 */

fun <T : Number> pick131(t: T): T = t

// TESTCASE NUMBER: 1
fun case_1() {
    <!CANNOT_INFER_PARAMETER_TYPE!>pick131<!>(<!ARGUMENT_TYPE_MISMATCH!>"x"<!>)
}
