// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, object-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: object singleton is accessed via implicit default constructor without explicit call
 */

// TESTCASE NUMBER: 1
object Counter {
    var value = 0
}

fun use(): Int = Counter.value
