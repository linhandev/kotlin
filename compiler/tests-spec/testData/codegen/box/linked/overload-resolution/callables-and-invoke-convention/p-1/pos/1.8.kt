/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 8
 * DESCRIPTION: property-like callable imported with renaming import expands to H.invoke()
 */

// FILE: lib.kt
package pkg1139.lib

object Holder1139 {
    var invoked = false
    operator fun invoke() {
        invoked = true
    }
}

// FILE: main.kt
package pkg1139.use

import pkg1139.lib.Holder1139 as Renamed1139

// TESTCASE NUMBER: 1
fun box(): String {
    Renamed1139()
    return if (Renamed1139.invoked) "OK" else "NOK"
}
