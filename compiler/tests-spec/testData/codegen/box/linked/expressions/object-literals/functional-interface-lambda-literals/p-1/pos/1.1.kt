// SAM_CONVERSIONS: CLASS

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, object-literals, functional-interface-lambda-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: functional interface lambda literal with explicit parameter list
 */

// TESTCASE NUMBER: 1

fun interface FI {
    fun format(n: Int): String
}

fun box(): String {
    val fi = FI { n: Int -> n.toString() }
    return if (fi.format(7) == "7") "OK" else "NOK"
}
