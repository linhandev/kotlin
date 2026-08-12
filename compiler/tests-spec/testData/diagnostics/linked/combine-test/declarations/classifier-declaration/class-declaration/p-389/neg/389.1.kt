// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 389 -> sentence 389
 * declarations, declaration-visibility -> paragraph 389 -> sentence 389
 * declarations, function-declaration -> paragraph 389 -> sentence 389
 * NUMBER: 1
 * DESCRIPTION: top-level function in same file cannot call class private fun
 */

// TESTCASE NUMBER: 1
class C { private fun secret(): Int = 1 }
fun expose(c: C): Int = c.<!INVISIBLE_MEMBER!>secret<!>()
fun test() = expose(C())
