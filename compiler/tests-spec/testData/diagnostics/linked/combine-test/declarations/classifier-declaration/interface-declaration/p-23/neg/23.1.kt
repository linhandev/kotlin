// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 23 -> sentence 23
 *                declarations, declaration-visibility -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: implementing class cannot access interface private helper used by default body (INVISIBLE_MEMBER; contrast with p-22)
 */

// TESTCASE NUMBER: 1
interface WithPrivateHelper {
    private fun helper(): Int = 1
    fun f(): Int = helper()
}

class LeakHelper : WithPrivateHelper {
    fun g(): Int = <!INVISIBLE_MEMBER!>helper<!>()
}
