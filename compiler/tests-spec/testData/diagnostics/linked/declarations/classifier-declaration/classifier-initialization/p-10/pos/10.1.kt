// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: function return type is inferred from expression body and explicit Nothing return is allowed
 */

// TESTCASE NUMBER: 1
fun inferredInt() = 42

// TESTCASE NUMBER: 2
fun inferredUnit() {
}

// TESTCASE NUMBER: 3
fun explicitNothing(): Nothing = throw IllegalStateException()
