// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: extension dispatch resolves using the static receiver type in the hierarchy
 */

// TESTCASE NUMBER: 1
open class Animal

class Dog : Animal()

fun Animal.label(): String = "animal"

open class Base

class Derived : Base()

fun Base.kind(): String = "base"

fun box(): String {
    val animal = Dog().label()
    val base = Derived().kind()
    return if (animal == "animal" && base == "base") "OK" else "NOK animal=$animal base=$base"
}
