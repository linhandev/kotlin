// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: object declaration with interface inheritance delegation
 */

// TESTCASE NUMBER: 1
interface I {
    fun value(): Int
}

object Case1 : I by object : I {
    override fun value(): Int = 1
}
