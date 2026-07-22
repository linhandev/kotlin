// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: named companion object is accessible at runtime
 */

// TESTCASE NUMBER: 1
class Factory {
    companion object Creator {
        fun create(): String = "OK"
    }
}

fun box(): String {
    return if (Factory.create() == "OK") "OK" else "NOK"
}
