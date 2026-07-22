// SAM_CONVERSIONS: CLASS

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, interface-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: SAM conversion for fun interface
 */

// TESTCASE NUMBER: 1
fun interface FI {
    fun bar(s: Int): Int
}

fun box(): String {
    val fi = FI { it + 42 }
    return if (fi.bar(0) == 42) "OK" else "NOK"
}
