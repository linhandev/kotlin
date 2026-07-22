/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 14
 * DESCRIPTION: member property-like callable with extension operator invoke expands to X.invoke()
 */

class Marker1146

operator fun Marker1146.invoke(): String = "ext"

class Wrapper1146 {
    val target = Marker1146()
}

// TESTCASE NUMBER: 1
fun box(): String {
    val result = Wrapper1146().target()
    return if (result == "ext") "OK" else "NOK"
}
