// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 4
 * DESCRIPTION: object implementing interface with overridden member in body compiles successfully
 */

// TESTCASE NUMBER: 1
interface Base {
    fun value(): Int
}

object Case1 : Base {
    override fun value(): Int = 5
}
