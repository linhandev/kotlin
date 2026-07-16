/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, call-with-specified-type-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: call f<T>() with explicit type argument resolves generic callable with one type parameter
 */

fun <T> foo11208(): String = "OK"

// TESTCASE NUMBER: 1
fun box(): String = if (foo11208<Int>() == "OK") "OK" else "NOK"
