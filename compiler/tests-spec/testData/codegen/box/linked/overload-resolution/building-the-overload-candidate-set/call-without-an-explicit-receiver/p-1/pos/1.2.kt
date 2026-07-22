/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, call-without-an-explicit-receiver -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: explicitly imported top-level function has higher priority than star-import in OCS
 */

// FILE: explicit.kt
package pkg11205.explicit

fun resolve11205(): String = "explicit"

// FILE: star.kt
package pkg11205.star

fun resolve11205(): String = "star"

// FILE: use.kt
package pkg11205.use

import pkg11205.explicit.resolve11205
import pkg11205.star.*

// TESTCASE NUMBER: 1
fun box(): String = if (resolve11205() == "explicit") "OK" else "NOK"
