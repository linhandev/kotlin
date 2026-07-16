// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, literals -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: Hex literal with invalid non-HexDigit characters (e.g., 0xG, 0xGH, 0xZ) violates HexLiteral BNF rule.
 */

// TESTCASE NUMBER: 1
fun case1() {
    val value = <!INT_LITERAL_OUT_OF_RANGE!>0x<!><!UNRESOLVED_REFERENCE, UNSUPPORTED!>G<!><!SYNTAX!><!>
}
