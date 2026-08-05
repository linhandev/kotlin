// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 1 -> sentence 1
 *                declarations, classifier-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: same-package top-level class is usable by short name without import
 */
// FILE: service.kt
package pkg56001.app

class Service56001

// FILE: box.kt
package pkg56001.app

// TESTCASE NUMBER: 1
fun test(): Service56001 = Service56001()

fun box(): String {
    val s = test()
    if (s !is Service56001) return "NOK"
    return "OK"
}
