// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 52 -> sentence 52
 * NUMBER: 2
 * DESCRIPTION: Space in CONTINUE_AT token as continue @loop breaks CONTINUE_AT lexeme
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    outer@ for (i in 1..3) {
        for (j in 1..3) {
            continue<!SYNTAX!><!> @outer
        }
    }
    return "OK"
}
