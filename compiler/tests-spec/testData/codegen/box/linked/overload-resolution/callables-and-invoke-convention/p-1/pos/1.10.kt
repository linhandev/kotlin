/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 10
 * DESCRIPTION: enum entry E.A() expands to invoke with entry as dispatch receiver
 */

enum class Color1141 {
    RED,
    BLUE;

    operator fun invoke(): String = name
}

// TESTCASE NUMBER: 1
fun box(): String = if (Color1141.RED() == "RED") "OK" else "NOK"
