/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, importing -> paragraph 2 -> sentence 2
 * NUMBER: 4
 * DESCRIPTION: import path through object declaration resolves object member
 */

// FILE: lib.kt
package pkg1003.lib

object Api1003 {
    fun ping1003(): String = "OK"
}

// FILE: use.kt
package pkg1003.use

import pkg1003.lib.Api1003.ping1003

// TESTCASE NUMBER: 1
fun box(): String = if (ping1003() == "OK") "OK" else "NOK"
