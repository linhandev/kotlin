// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 64 -> sentence 64
 * NUMBER: 2
 * DESCRIPTION: Incomplete delegate annotation @delegate without colon causes parser error
 */

// TESTCASE NUMBER: 1
class BrokenDelegate64 {
    @delegate<!SYNTAX!><!> Suppress("WARNING")
    val x: Int by lazy { 1 }
}

fun case1(): String {
    return "OK"
}
