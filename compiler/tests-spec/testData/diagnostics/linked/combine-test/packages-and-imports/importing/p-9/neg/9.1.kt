// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 9 -> sentence 9
 *                declarations, declaration-visibility -> paragraph 9 -> sentence 9
 *                declarations, classifier-declaration -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: private nested class cannot be imported via Outer.Inner path or constructed by short name from another package
 */
// FILE: a.kt
package pkg56009.sec

class Host56009 {
    private class Secret56009(val v: Int = 1)
    fun localOk56009(): Int = Secret56009().v
}

// FILE: main.kt
package pkg56009.app

// TESTCASE NUMBER: 1
import pkg56009.sec.Host56009.<!INVISIBLE_REFERENCE!>Secret56009<!>

fun case_1() {
    <!INVISIBLE_MEMBER!>Secret56009<!>()
}
