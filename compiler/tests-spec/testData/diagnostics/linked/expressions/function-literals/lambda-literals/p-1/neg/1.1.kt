// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, function-literals, lambda-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: lambda literal cannot have vararg parameters
 */

// TESTCASE NUMBER: 1
fun case1() {
    val f: (<!UNSUPPORTED!>vararg<!> Int) -> Unit = {}
}
