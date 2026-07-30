// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 7 -> sentence 7
 *                declarations, classifier-declaration, constructor-declaration -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: constructor reference ::User infers function type (String) -> User and creates instances when invoked, verifying runtime semantics
 */

data class User(val name: String)

val ctor: (String) -> User = ::User

// TESTCASE NUMBER: 1
fun test(): User = ctor("A")

fun box(): String {
    val u = test()
    if (u.name != "A") return "NOK"
    if (u !is User) return "NOK"
    val u2 = ctor("B")
    if (u2.name != "B") return "NOK"
    return "OK"
}
