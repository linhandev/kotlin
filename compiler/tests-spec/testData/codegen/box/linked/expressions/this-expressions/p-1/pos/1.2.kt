// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, this-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: this@type refers to classifier currently being declared
 */

// TESTCASE NUMBER: 1

class A(val token: String = "OK") {
    inner class Inner {
        fun read(): String {
            fun String.ext(): String = this@A.token
            return "".ext()
        }
    }
}

fun box(): String {
    return if (A().Inner().read() == "OK") "OK" else "NOK"
}
