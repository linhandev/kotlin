// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Backtick character inside QuotedSymbol `a``b` violates QuotedSymbol rule
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val <!VARIABLE_WITH_NO_TYPE_NO_INITIALIZER!>`a`<!><!SYNTAX!>`b` = 2<!>
    return "OK"
}
