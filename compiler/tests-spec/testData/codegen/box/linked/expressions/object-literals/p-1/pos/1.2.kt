// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, object-literals -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: object literal with multiple supertypes implicitly downcast to declared return type
 */

// TESTCASE NUMBER: 1

open class Base
interface I

fun baz(): Base = object : Base(), I {}

fun box(): String {
    val b: Base = baz()
    return if (b is Base) "OK" else "NOK"
}
