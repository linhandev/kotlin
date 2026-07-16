// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 53 -> sentence 53
 * NUMBER: 2
 * DESCRIPTION: Space in BREAK_AT token as break @loop breaks BREAK_AT lexeme
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    loop@ for (i in 1..3) {
        break<!SYNTAX!><!> @loop
    }
    return "OK"
}
