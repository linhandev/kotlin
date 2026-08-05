// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 16 -> sentence 16
 *                declarations, function-declaration, extension-function-declaration -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: imported extension function can be called on a matching receiver
 */
// FILE: a.kt
package pkg56016.ext

fun String.twice56016(): String = this + this

// FILE: box.kt
package pkg56016.app

import pkg56016.ext.twice56016

// TESTCASE NUMBER: 1
fun test(): String = "a".twice56016()

fun box(): String {
    if (test() != "aa") return "NOK"
    if ("xy".twice56016() != "xyxy") return "NOK"
    return "OK"
}
