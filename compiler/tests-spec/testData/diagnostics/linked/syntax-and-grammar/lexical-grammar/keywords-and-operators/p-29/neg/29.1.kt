// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: ARROW token in incomplete function type (Int) -> without return type causes parser error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val f: (Int) ->
    <!SYNTAX!>return<!> <!SYNTAX!>"OK"<!>
}
