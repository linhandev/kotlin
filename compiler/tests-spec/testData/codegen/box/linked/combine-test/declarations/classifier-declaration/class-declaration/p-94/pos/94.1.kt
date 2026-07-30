// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 94 -> sentence 94
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 94 -> sentence 94
 *                declarations, classifier-declaration, classifier-initialization -> paragraph 94 -> sentence 94
 * NUMBER: 1
 * DESCRIPTION: secondary constructor chains delegation through overload to primary constructor in class declaration
 */

// TESTCASE NUMBER: 1
class User(val name: String, val age: Int, val tag: String) {
    constructor(n: String, a: Int) : this(n, a, "")
    constructor(n: String) : this(n, 0)
}

fun viaChained(): User = User("Ann")

fun viaTwoArg(): User = User("Bob", 5)

fun viaPrimary(): User = User("Cat", 3, "vip")

fun box(): String {
    val chained = viaChained()
    if (chained.name != "Ann") return "NOK: chained name"
    if (chained.age != 0) return "NOK: chained age"
    if (chained.tag != "") return "NOK: chained tag"
    val twoArg = viaTwoArg()
    if (twoArg.name != "Bob") return "NOK: two-arg name"
    if (twoArg.age != 5) return "NOK: two-arg age"
    if (twoArg.tag != "") return "NOK: two-arg tag"
    val primary = viaPrimary()
    if (primary.name != "Cat") return "NOK: primary name"
    if (primary.age != 3) return "NOK: primary age"
    if (primary.tag != "vip") return "NOK: primary tag"
    return "OK"
}
