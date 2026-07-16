// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 62 -> sentence 62
 * NUMBER: 3
 * DESCRIPTION: Incomplete param annotation @param: missing annotation name on constructor parameter causes parser error
 */

// TESTCASE NUMBER: 1
class BrokenParamColon62(<!SYNTAX!>@param:<!> val x: Int)

fun case1(): String {
    return "OK"
}
