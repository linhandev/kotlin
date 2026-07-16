// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, local-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: local class captures values from enclosing scope
 */

// TESTCASE NUMBER: 1
fun foo(): Int {
    val x = 2
    class Local {
        val y = x
    }
    return Local().y
}
