/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: named call f(a = v) filters OCS to callables with matching formal parameter names
 */

fun pick11206(): String = "zero"

fun pick11206(x: Int): String = "int"

fun pick11206(y: String): String = "str"

// TESTCASE NUMBER: 1
fun box(): String = if (pick11206(x = 1) == "int") "OK" else "NOK"
