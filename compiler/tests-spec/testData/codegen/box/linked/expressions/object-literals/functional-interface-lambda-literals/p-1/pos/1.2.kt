// SAM_CONVERSIONS: CLASS

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, object-literals, functional-interface-lambda-literals -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: functional interface lambda literal for zero-abstract-parameter SAM
 */

// TESTCASE NUMBER: 1

fun interface Action {
    fun run()
}

fun box(): String {
    var ran = false
    val action = Action { ran = true }
    action.run()
    return if (ran) "OK" else "NOK"
}
