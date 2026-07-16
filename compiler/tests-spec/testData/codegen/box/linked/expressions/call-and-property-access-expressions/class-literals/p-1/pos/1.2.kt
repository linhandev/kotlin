// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, class-literals -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Derived instance x::class is Derived::class not Base::class
 */

// TESTCASE NUMBER: 1

open class Base
class Derived : Base()

fun box(): String {
    val x: Base = Derived()
    if (x::class != Derived::class) return "NOK"
    if (x::class == Base::class) return "NOK"
    return "OK"
}
