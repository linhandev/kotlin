// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 372 -> sentence 372
 * declarations, declaration-visibility -> paragraph 372 -> sentence 372
 * declarations, property-declaration -> paragraph 372 -> sentence 372
 * declarations, classifier-declaration, data-class-declaration -> paragraph 372 -> sentence 372
 * NUMBER: 1
 * DESCRIPTION: data class private val is not exposed in generated public copy parameters
 */

// TESTCASE NUMBER: 1
data class User(private val id: Int, val name: String)

// TESTCASE NUMBER: 1
fun test(): String = User(1, "A").name

fun box(): String {
    if (test() != "A") return "NOK"
    return "OK"
}
