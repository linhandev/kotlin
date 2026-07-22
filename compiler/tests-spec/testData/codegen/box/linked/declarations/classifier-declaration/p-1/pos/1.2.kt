// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: class implementing interface override method returns expected value at runtime
 */

// TESTCASE NUMBER: 1
interface Greeter {
    fun greet(): String
}

class HelloGreeter : Greeter {
    override fun greet(): String = "OK"
}

fun box(): String {
    return if (HelloGreeter().greet() == "OK") "OK" else "NOK"
}
