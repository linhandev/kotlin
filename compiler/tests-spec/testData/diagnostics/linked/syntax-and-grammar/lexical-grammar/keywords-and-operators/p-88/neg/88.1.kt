// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 88 -> sentence 88
 * NUMBER: 1
 * DESCRIPTION: Standalone FOR token as statement causes compile error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    run { }
    for<!SYNTAX!><!>
    return "OK"
<!NO_RETURN_IN_FUNCTION_WITH_BLOCK_BODY!>}<!>
