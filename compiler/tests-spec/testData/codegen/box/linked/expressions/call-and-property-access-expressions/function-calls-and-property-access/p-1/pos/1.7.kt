// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 7
 * DESCRIPTION: call-site arguments evaluated left-to-right before default parameters
 */

// TESTCASE NUMBER: 1

var log = ""

fun h(): Int {
    log += "h"
    return 1
}

fun g(): Int {
    log += "g"
    return 2
}

fun f(x: Int = h(), y: Int = g()): Int = x + y

fun box(): String {
    log = ""
    if (f(y = run { log += "n"; 20 }, x = run { log += "m"; 10 }) != 30) return "NOK"
    if (log != "nm") return "NOK"

    log = ""
    if (f(y = run { log += "n"; 2 }) != 3) return "NOK"
    if (log != "nh") return "NOK"

    return "OK"
}
