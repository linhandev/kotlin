// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, object-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: object cannot be instantiated like a constructor call
 */

// TESTCASE NUMBER: 1
object Singleton

fun tryInstantiate() {
    val x = <!DEBUG_INFO_MISSING_UNRESOLVED, FUNCTION_EXPECTED!>Singleton<!>()
}
