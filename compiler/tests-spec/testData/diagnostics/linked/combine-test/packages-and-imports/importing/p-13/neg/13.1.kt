// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: top-level private is file-private and invisible to another file in the same package
 */
// FILE: a.kt
package pkg56013.same

private fun helper56013(): Int = 1

fun local56013(): Int = helper56013()

// FILE: b.kt
package pkg56013.same

// TESTCASE NUMBER: 1
fun case_1(): Int = <!INVISIBLE_MEMBER!>helper56013<!>()
