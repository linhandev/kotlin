// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: parameterized is may use partial star projection type arguments
 */

// TESTCASE NUMBER: 1

interface Foo<A, B>
class Fee<T, U> : Foo<U, T>

fun box(): String {
    val foo: Foo<String, Int> = Fee<Int, String>()
    if (foo is Fee<Int, *>) return "OK"
    return "NOK"
}
