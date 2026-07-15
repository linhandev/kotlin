// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 110 -> sentence 110
 * NUMBER: 3
 * DESCRIPTION: INNER token on top-level class causes compile error
 */

// TESTCASE NUMBER: 1
<!WRONG_MODIFIER_CONTAINING_DECLARATION!>inner<!> class TopLevelInner110

fun case1(): String = "OK"
