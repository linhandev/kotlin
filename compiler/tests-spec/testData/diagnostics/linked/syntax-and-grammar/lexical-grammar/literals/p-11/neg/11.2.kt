// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 11 -> sentence 11
 * NUMBER: 2
 * DESCRIPTION: BinLiteral 0b2 with invalid non-BinDigit 2. BinLiteral requires BinDigits (0 or 1).
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val value = <!INT_LITERAL_OUT_OF_RANGE!>0b2<!>
<!NO_RETURN_IN_FUNCTION_WITH_BLOCK_BODY!>}<!>
