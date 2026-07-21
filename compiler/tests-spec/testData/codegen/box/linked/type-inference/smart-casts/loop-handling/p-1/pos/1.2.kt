/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, loop-handling -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: doWhileAndSmartCasts2 smart casts after do-while at runtime
 */
// TESTCASE NUMBER: 1

fun doWhileAndSmartCasts2_1414(): Int {
    var a: Int? = null
    do {
        if (a == null) a = 10
    } while (a == null)
    return a + 1
}

fun box(): String = if (doWhileAndSmartCasts2_1414() == 11) "OK" else "NOK"
