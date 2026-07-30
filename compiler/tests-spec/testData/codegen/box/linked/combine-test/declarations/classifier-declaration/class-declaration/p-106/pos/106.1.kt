// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 106 -> sentence 106
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 106 -> sentence 106
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 106 -> sentence 106
 *                declarations, function-declaration -> paragraph 106 -> sentence 106
 * NUMBER: 1
 * DESCRIPTION: secondary constructor with default parameter delegates to primary constructor in class declaration
 */

// TESTCASE NUMBER: 1
class User(val name: String, val age: Int) {
    constructor(name: String = "guest") : this(name, 0)
}

fun viaDefaultSecondary(): User = User()

fun viaExplicitSecondary(): User = User("Ann")

fun viaPrimary(): User = User("Bob", 25)

fun box(): String {
    val defaultSecondary = viaDefaultSecondary()
    if (defaultSecondary.name != "guest") return "NOK: default name"
    if (defaultSecondary.age != 0) return "NOK: default age"
    val explicitSecondary = viaExplicitSecondary()
    if (explicitSecondary.name != "Ann") return "NOK: explicit name"
    if (explicitSecondary.age != 0) return "NOK: explicit age"
    val primary = viaPrimary()
    if (primary.name != "Bob") return "NOK: primary name"
    if (primary.age != 25) return "NOK: primary age"
    return "OK"
}
