// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, this-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: unlabeled this refers to default implicit receiver in extension lambda
 */

// TESTCASE NUMBER: 1

object C {
    fun run(block: C.() -> String): String = block()
}

fun box(): String {
    return if (C.run { token() } == "OK") "OK" else "NOK"
}

private fun C.token(): String = "OK"
