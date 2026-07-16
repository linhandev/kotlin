// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 3 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: script multiple statements with semicolon in function body
 */

// TESTCASE NUMBER: 1
fun case1(): Int {
    val a = 1;
    val b = 2;
    return a + b
}
