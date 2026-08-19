// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 2 -> sentence 2
 *                packages-and-imports, modules -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: FQCN can reference a declaration from another package without import in the same module
 */
// FILE: a.kt
package pkg56002.a

class Box56002(val v: Int = 1)

// FILE: box.kt
package pkg56002.b

// TESTCASE NUMBER: 1
fun test(): pkg56002.a.Box56002 = pkg56002.a.Box56002()

fun box(): String {
    if (test().v != 1) return "NOK"
    return "OK"
}
