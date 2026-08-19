// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 40 -> sentence 40
 *                type-system, subtyping, subtyping-rules -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: reified type parameter with upper bound A correctly checks is type at runtime in inheritance scenario — subclass instances are A, but A instance is not subclass
 */

open class A
class B : A()
class C : A()

inline fun <reified T : A> checkIs(value: Any?): Boolean = value is T

// TESTCASE NUMBER: 1
fun box(): String {
    val a = A()
    val b = B()
    val c = C()
    if (!checkIs<A>(a)) return "NOK: A is A"
    if (!checkIs<A>(b)) return "NOK: B is A"
    if (!checkIs<A>(c)) return "NOK: C is A"
    if (!checkIs<B>(b)) return "NOK: B is B"
    if (checkIs<B>(a)) return "NOK: A is not B"
    if (checkIs<B>(c)) return "NOK: C is not B"
    return "OK"
}
