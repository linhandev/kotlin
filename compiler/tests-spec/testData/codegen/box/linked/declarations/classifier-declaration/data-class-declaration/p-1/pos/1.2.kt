// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, data-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: data class generates hashCode consistent with equals at runtime
 */

// TESTCASE NUMBER: 1
data class DataPair(val first: Int, val second: Int)

fun box(): String {
    val a = DataPair(1, 2)
    val b = DataPair(1, 2)
    return if (a == b && a.hashCode() == b.hashCode()) "OK" else "NOK"
}
