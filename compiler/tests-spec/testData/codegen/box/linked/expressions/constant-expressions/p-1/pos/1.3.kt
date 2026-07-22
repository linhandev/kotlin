// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, constant-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: string template interpolating only constants is compile-time constant
 */

// TESTCASE NUMBER: 1

const val S: String = "val=${1 + 2}"

fun box(): String = if (S == "val=3") "OK" else "NOK"
