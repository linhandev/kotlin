// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: function can return an object that implements an interface
 */

// TESTCASE NUMBER: 1
interface Logger {
    fun log(msg: String): String
}

object Console : Logger {
    override fun log(msg: String): String = "log:$msg"
}

fun logger(): Logger = Console

fun test(): String = logger().log("hi")

fun box(): String {
    if (test() != "log:hi") return "NOK: test"
    if (logger() !== Console) return "NOK: identity"
    return "OK"
}
