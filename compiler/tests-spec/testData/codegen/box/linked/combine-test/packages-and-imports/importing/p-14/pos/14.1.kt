// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 14 -> sentence 14
 *                declarations, classifier-declaration -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: nested class can be imported and constructed by short name
 */
// FILE: a.kt
package pkg56014.api

class Outer56014 {
    class Inner56014(val x: Int = 2)
}

// FILE: box.kt
package pkg56014.app

import pkg56014.api.Outer56014.Inner56014

// TESTCASE NUMBER: 1
fun test(): Inner56014 = Inner56014()

fun box(): String {
    if (test().x != 2) return "NOK"
    return "OK"
}
