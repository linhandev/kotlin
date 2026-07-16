/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, loop-handling -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: breakFromInfiniteLoop smart casts after while true at runtime
 */
// TESTCASE NUMBER: 1

fun randomBoolean1414(): Boolean = true

fun breakFromInfiniteLoop1414(): Int {
    var a: Int? = 42
    while (true) {
        if (a == null) return -1
        if (randomBoolean1414()) break
    }
    return a + 1
}

fun box(): String = if (breakFromInfiniteLoop1414() == 43) "OK" else "NOK"
