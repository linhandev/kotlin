// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: CR (U+000D) alone cannot be used as line terminator in source code
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val<!SYNTAX!>\<!><!SYNTAX!>r<!>
<!UNRESOLVED_REFERENCE!>x<!> = 1  //CR不能作为换行符
    return "OK"
}
