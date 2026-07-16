/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, importing -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: cross-package import of top-level val works in box()
 */

// FILE: lib.kt
package pkg1002.lib

val exportedValue1002 = 42

// FILE: main.kt
package pkg1002.main

import pkg1002.lib.exportedValue1002

// TESTCASE NUMBER: 1
fun box(): String = if (exportedValue1002 == 42) "OK" else "NOK"
