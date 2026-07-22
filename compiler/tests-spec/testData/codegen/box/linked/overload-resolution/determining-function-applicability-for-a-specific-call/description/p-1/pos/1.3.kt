/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, determining-function-applicability-for-a-specific-call, description -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: lambda argument deferred but call remains applicable with matching function type
 */

fun apply11302(block: () -> Int): Int = block()

// TESTCASE NUMBER: 1
fun box(): String = if (apply11302 { 42 } == 42) "OK" else "NOK"
