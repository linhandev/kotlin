// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: argument evaluation order at runtime
 */

// TESTCASE NUMBER: 1
var log = ""

fun <T> logged(value: T): T = value.also { log += "$value," }

fun combine(a: Int, b: Int, c: Int): Int = a + b + c

fun box(): String {
    log = ""
    val result = combine(logged(1), logged(2), logged(3))
    return if (result == 6 && log == "1,2,3,") "OK" else "NOK result=$result log=$log"
}
