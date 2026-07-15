// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: DoubleExponent 2.0e+ with missing DecDigits after plus sign
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val value = <!FLOAT_LITERAL_OUT_OF_RANGE!>2.0e+<!>
<!NO_RETURN_IN_FUNCTION_WITH_BLOCK_BODY!>}<!>
