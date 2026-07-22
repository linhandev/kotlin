/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: constructor of type X is a function-like callable resolved as X()
 */

class Box1134(var created: Boolean = false) {
    init {
        created = true
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    val instance = Box1134()
    return if (instance.created) "OK" else "NOK"
}
