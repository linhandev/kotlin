// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, object-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: object literal with supertype and class body override
 */

// TESTCASE NUMBER: 1

interface Greet {
    fun msg(): String
}

fun box(): String {
    val g = object : Greet {
        override fun msg(): String = "hi"
    }
    return if (g.msg() == "hi") "OK" else "NOK"
}
