// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 107 -> sentence 107
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 107 -> sentence 107
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 107 -> sentence 107
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 107 -> sentence 107
 * NUMBER: 1
 * DESCRIPTION: secondary constructor with reordered parameters delegates via named arguments at call site in class declaration
 */

// TESTCASE NUMBER: 1
class User(val name: String, val age: Int) {
    constructor(years: Int, label: String) : this(label, years)
}

fun viaNamedSecondary(): User = User(years = 2, label = "Ann")

fun viaPositionalSecondary(): User = User(3, "Bob")

fun viaPrimary(): User = User("Cat", 4)

fun box(): String {
    val named = viaNamedSecondary()
    if (named.name != "Ann") return "NOK: named name"
    if (named.age != 2) return "NOK: named age"
    val positional = viaPositionalSecondary()
    if (positional.name != "Bob") return "NOK: positional name"
    if (positional.age != 3) return "NOK: positional age"
    val primary = viaPrimary()
    if (primary.name != "Cat") return "NOK: primary name"
    if (primary.age != 4) return "NOK: primary age"
    return "OK"
}
