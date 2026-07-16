// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions, exhaustive-when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: exhaustive when on sealed class covers all direct non-sealed subtypes
 */

// TESTCASE NUMBER: 1

sealed class Base
class Derived1 : Base()
class Derived2 : Base()

fun box(): String {
    val s: Base = Derived1()
    return when (s) {
        is Derived1 -> "OK"
        is Derived2 -> "NOK"
    }
}
