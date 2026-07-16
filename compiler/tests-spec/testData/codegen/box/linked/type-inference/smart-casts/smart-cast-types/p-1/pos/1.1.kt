/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, smart-cast-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: id(a) uses smart cast in inference and returns non-null Any at runtime
 */
// TESTCASE NUMBER: 1

fun <T> id1412(value: T): T = value

fun use1412(): Any {
    var a: Any? = "ok"
    if (a == null) return ""
    return id1412(a)
}

fun box(): String = if (use1412() == "ok") "OK" else "NOK"
