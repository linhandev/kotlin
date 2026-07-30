// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 14 -> sentence 14
 *                type-system, subtyping, subtyping-rules -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: bare type is-check on class hierarchy with smart cast works correctly at runtime
 */

// TESTCASE NUMBER: 1
open class Base<T>
class Derived<T>(val t: T) : Base<T>()

class OtherDerived<T>(val t: T) : Base<T>()

fun test(base: Base<Int>): Int {
    if (base is Derived) {
        return base.t + 1
    }
    return -1
}

fun box(): String {
    val d: Base<Int> = Derived(41)
    if (test(d) != 42) return "NOK: Derived(41)"
    val other: Base<Int> = OtherDerived(99)
    if (test(other) != -1) return "NOK: other not Derived"
    return "OK"
}
