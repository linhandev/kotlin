// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: in operator prefers explicitly imported Int contains extension over star-imported contains at runtime
 */

// FILE: explicit.kt
package pkg5252.explicit

operator fun Int.contains(other: Int): Boolean = this > other

// FILE: star.kt
package pkg5252.star

operator fun Int.contains(other: Int): Boolean = this <= other

// FILE: use.kt
package pkg5252.use

import pkg5252.explicit.contains
import pkg5252.star.*

// TESTCASE NUMBER: 1
fun test(receiver: Int, element: Int): Boolean = element in receiver

fun box(): String {
    if (test(3, 5)) return "NOK"
    if (!test(10, 5)) return "NOK"
    if (test(3, 6)) return "NOK"
    if (!test(7, 6)) return "NOK"
    return "OK"
}
