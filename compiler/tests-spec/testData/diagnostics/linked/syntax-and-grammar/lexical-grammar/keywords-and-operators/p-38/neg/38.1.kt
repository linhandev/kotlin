// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: Double AT @@file:JvmName glued without Hidden breaks AT_BOTH_WS file annotation
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>@<!>@file:JvmName("BoxFile")
fun case1(): String {
    return "OK"
}
