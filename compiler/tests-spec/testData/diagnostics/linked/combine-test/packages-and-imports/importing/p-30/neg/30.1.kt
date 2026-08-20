// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 30 -> sentence 30
 *                packages-and-imports, importing -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: same-package other-file private class remains invisible to import and short-name use
 */
// FILE: a.kt
package pkg56030.same

private class Secret56030

fun localOk56030(): Int = Secret56030().hashCode()

// FILE: main.kt
package pkg56030.same

// TESTCASE NUMBER: 1
import pkg56030.same.<!INVISIBLE_REFERENCE!>Secret56030<!>

fun case_1() {
    <!INVISIBLE_MEMBER!>Secret56030<!>()
}
