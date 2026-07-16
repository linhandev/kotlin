// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 8 -> sentence 8
 * NUMBER: 2
 * DESCRIPTION: private generic function with upper bound and infix member function in class body compile successfully
 */

// TESTCASE NUMBER: 1
private fun <T : Number> constrained(value: T): T = value

// TESTCASE NUMBER: 2
class Box {
    infix fun Int.tag(label: String): String = "$label:$this"
}
