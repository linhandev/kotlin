/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, fully-qualified-call -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: fully-qualified P.n() OCS contains all top-level callables named n in package P
 */

// FILE: lib.kt
package pkg11201.abc

fun pick11201(a: Int): String = "int:$a"

fun pick11201(a: Double): String = "dbl:$a"

// FILE: box.kt
// TESTCASE NUMBER: 1
fun box(): String {
    val fromInt = pkg11201.abc.pick11201(1)
    val fromDouble = pkg11201.abc.pick11201(1.0)
    return if (fromInt == "int:1" && fromDouble == "dbl:1.0") "OK" else "NOK: $fromInt/$fromDouble"
}
