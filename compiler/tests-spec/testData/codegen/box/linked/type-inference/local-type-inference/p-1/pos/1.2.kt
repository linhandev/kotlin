/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, local-type-inference -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: smart cast participates in local type inference at runtime
 */
// TESTCASE NUMBER: 1

fun <T> id142(value: T): T = value

fun smartCastLocal142(): String {
    var a: Any? = null
    a = "ok"
    if (a == null) return "NOK"
    return id142(a)
}

fun box(): String = if (smartCastLocal142() == "ok") "OK" else "NOK"
