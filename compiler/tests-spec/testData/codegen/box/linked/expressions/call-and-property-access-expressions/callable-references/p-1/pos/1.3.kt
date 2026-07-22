// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, callable-references -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: A(5)::n binds to property n of specific instance and reads its value
 */

// TESTCASE NUMBER: 1

class A(val n: Int)

fun box(): String {
    val ref: () -> Int = A(5)::n
    if (ref() != 5) return "NOK"
    return "OK"
}
