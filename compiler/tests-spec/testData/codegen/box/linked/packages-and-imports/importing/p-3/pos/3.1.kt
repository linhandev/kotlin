/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, importing -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: renaming import changes unqualified name but not qualified access
 */

// FILE: defs.kt
package pkg1004.foo

fun foo(): String = "fromFoo"

fun bar(): String = "fromBar"

// FILE: use.kt
package pkg1004.foo

import pkg1004.foo.foo as baz

// TESTCASE NUMBER: 1
fun box(): String {
    val qualifiedFoo = pkg1004.foo.foo()
    val qualifiedBar = pkg1004.foo.bar()
    val renamed = baz()
    return if (qualifiedFoo == "fromFoo" &&
        qualifiedBar == "fromBar" &&
        renamed == "fromFoo"
    ) {
        "OK"
    } else {
        "NOK"
    }
}
