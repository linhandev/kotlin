/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, importing -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: import path through classifier type resolves companion object member
 */

// FILE: lib.kt
package pkg1003.lib

class Outer1003 {
    companion object {
        fun greet1003(): String = "OK"
    }
}

// FILE: use.kt
package pkg1003.use

import pkg1003.lib.Outer1003.Companion.greet1003

// TESTCASE NUMBER: 1
fun box(): String = if (greet1003() == "OK") "OK" else "NOK"
