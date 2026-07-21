/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: runtime-type-information, runtime-available-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: star-projected classifier type is runtime-available for is checks at runtime
 */
// TESTCASE NUMBER: 1

interface Foo1511<A, B>
class Fee1511<T, U> : Foo1511<U, T>

fun checkStarProjected1511(foo: Foo1511<String, Int>): Boolean = foo is Fee1511<*, *>

fun box(): String {
    val foo: Foo1511<String, Int> = Fee1511<Int, String>()
    if (!checkStarProjected1511(foo)) return "NOK: star-projected is should succeed"
    return "OK"
}
