/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 16
 * DESCRIPTION: extension property-like callable with extension operator invoke expands to X.invoke()
 */

class Holder1151

operator fun Holder1151.invoke(): String = "extension"

val String.callable1151: Holder1151
    get() = Holder1151()

// TESTCASE NUMBER: 1
fun box(): String {
    val result = "x".callable1151()
    return if (result == "extension") "OK" else "NOK"
}
