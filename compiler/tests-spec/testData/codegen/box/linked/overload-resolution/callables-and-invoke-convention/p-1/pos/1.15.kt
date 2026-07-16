/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 15
 * DESCRIPTION: extension property-like callable with member operator invoke expands to X.invoke()
 */

class Holder1150 {
    operator fun invoke(): String = "member"
}

val String.callable1150: Holder1150
    get() = Holder1150()

// TESTCASE NUMBER: 1
fun box(): String {
    val result = "x".callable1150()
    return if (result == "member") "OK" else "NOK"
}
