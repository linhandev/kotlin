// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, data-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: data class generates equals and toString at runtime
 */

// TESTCASE NUMBER: 1
data class User(val id: Int, val name: String)

fun box(): String {
    val a = User(1, "Alice")
    val b = User(1, "Alice")
    val c = User(2, "Bob")
    return if (a == b && a != c && a.toString().contains("Alice")) "OK" else "NOK"
}
