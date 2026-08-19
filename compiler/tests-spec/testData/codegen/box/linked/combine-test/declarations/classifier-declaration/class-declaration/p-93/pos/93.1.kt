// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 93 -> sentence 93
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 93 -> sentence 93
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 93 -> sentence 93
 * NUMBER: 1
 * DESCRIPTION: multiple secondary constructors delegate to primary constructor separately in class declaration
 */

// TESTCASE NUMBER: 1
class User(val name: String, val age: Int) {
    constructor(name: String) : this(name, 0)
    constructor() : this("guest", 0)
}

fun viaNoArg(): User = User()

fun viaSingleArg(): User = User("Ann")

fun viaPrimary(): User = User("Bob", 25)

fun box(): String {
    val noArg = viaNoArg()
    if (noArg.name != "guest") return "NOK: no-arg name"
    if (noArg.age != 0) return "NOK: no-arg age"
    val singleArg = viaSingleArg()
    if (singleArg.name != "Ann") return "NOK: single-arg name"
    if (singleArg.age != 0) return "NOK: single-arg age"
    val primary = viaPrimary()
    if (primary.name != "Bob") return "NOK: primary name"
    if (primary.age != 25) return "NOK: primary age"
    return "OK"
}
