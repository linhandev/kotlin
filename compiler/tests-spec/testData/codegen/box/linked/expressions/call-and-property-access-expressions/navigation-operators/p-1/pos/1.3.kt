// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: api.greet("x") invokes greet via dot member call
 */

// TESTCASE NUMBER: 1

class Api {
    fun greet(name: String): String = "hi $name"
}

fun box(): String {
    val api = Api()
    return if (api.greet("x") == "hi x") "OK" else "NOK"
}
