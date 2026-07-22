/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: trailing lambda call f(1) { } is equivalent to f(1, body = { }) for overload resolution
 */

fun consume11207(prefix: Int, body: () -> String): String = "$prefix:${body()}"

// TESTCASE NUMBER: 1
fun box(): String {
    val viaTrailing = consume11207(1) { "OK" }
    val viaNamed = consume11207(1, body = { "OK" })
    return if (viaTrailing == "1:OK" && viaNamed == "1:OK") "OK" else "NOK: $viaTrailing/$viaNamed"
}
