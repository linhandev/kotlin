// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, this-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: this@function refers to extension function receiver being declared
 */

// TESTCASE NUMBER: 1

class Host {
    val token = "OK"

    fun String.foo(): String = this@Host.token
}

fun box(): String {
    return if (Host().let { with(it) { "".foo() } } == "OK") "OK" else "NOK"
}
