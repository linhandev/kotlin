// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, callable-references -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Box::n binds to property n and reads n from given Box receiver
 */

// TESTCASE NUMBER: 1

class Box(val n: Int)

fun box(): String {
    val ref: (Box) -> Int = Box::n
    if (ref(Box(3)) != 3) return "NOK"
    return "OK"
}
