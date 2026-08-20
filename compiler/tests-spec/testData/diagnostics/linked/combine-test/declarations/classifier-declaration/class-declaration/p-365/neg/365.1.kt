// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 365 -> sentence 365
 * declarations, declaration-visibility -> paragraph 365 -> sentence 365
 * declarations, property-declaration -> paragraph 365 -> sentence 365
 * NUMBER: 1
 * DESCRIPTION: top-level function in same file still cannot access class private property
 */

// TESTCASE NUMBER: 1
class C(private val secret: Int)
fun expose(c: C): Int = c.<!INVISIBLE_MEMBER!>secret<!>
fun test() = expose(C(1))
