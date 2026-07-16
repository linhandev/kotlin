/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, importing -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: renaming import of property-like callable expands to invoke on the new name
 */

// FILE: lib.kt
package pkg1005.lib

object Gate1005 {
    operator fun invoke(): String = "OK"
}

// FILE: use.kt
package pkg1005.use

import pkg1005.lib.Gate1005 as entry1005

// TESTCASE NUMBER: 1
fun box(): String = if (entry1005() == "OK") "OK" else "NOK"
