/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, determining-function-applicability-for-a-specific-call, rationale -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: function applicable when call arguments are assignable to parameters
 */

fun pick11301(a: Int): String = "int:$a"

fun pick11301(a: String): String = "str:$a"

// TESTCASE NUMBER: 1
fun box(): String = if (pick11301(1) == "int:1") "OK" else "NOK"
