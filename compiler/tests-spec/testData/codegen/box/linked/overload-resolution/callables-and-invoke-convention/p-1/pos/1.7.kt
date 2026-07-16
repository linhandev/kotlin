/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 7
 * DESCRIPTION: function-like callable imported with renaming import resolves as bar()
 */

// FILE: lib.kt
package pkg1138.lib

fun original1138(): String = "OK"

// FILE: main.kt
package pkg1138.use

import pkg1138.lib.original1138 as renamed1138

// TESTCASE NUMBER: 1
fun box(): String = renamed1138()
