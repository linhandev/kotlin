// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: b.doubler(3) calls operator fun invoke on Doubler property
 */

// TESTCASE NUMBER: 1

class Doubler {
    operator fun invoke(x: Int): Int = x * 2
}

class Box {
    val doubler = Doubler()
}

fun box(): String {
    val b = Box()
    if (b.doubler(3) != 6) return "NOK"
    return "OK"
}
