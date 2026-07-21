/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, function-signature-type-inference, named-and-anonymous-function-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: expression body — declared return type constrains generic foo call at runtime
 */
// TESTCASE NUMBER: 1

fun <T> foo1431(): T {
    @Suppress("UNCHECKED_CAST")
    return 42 as T
}

fun bar1431(): Int = foo1431()

fun box(): String = if (bar1431() == 42) "OK" else "NOK"
