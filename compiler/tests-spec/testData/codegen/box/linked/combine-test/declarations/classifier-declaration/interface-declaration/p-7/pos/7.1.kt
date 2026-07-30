// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 7 -> sentence 7
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 7 -> sentence 7
 *                declarations, function-declaration -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: primary constructor override val satisfies abstract interface property used by interface default function
 */

// TESTCASE NUMBER: 1
interface Named {
    val name: String
    fun label(): String = "user:$name"
}

class User(override val name: String) : Named

fun box(): String {
    if (User("Ann").name != "Ann") return "NOK: ctor-prop"
    if (User("Ann").label() != "user:Ann") return "NOK: default-reads-abstract-prop"
    if (User("Bob").label() != "user:Bob") return "NOK: other-name"
    val asIface: Named = User("Ann")
    if (asIface.label() != "user:Ann") return "NOK: via-interface"
    return "OK"
}
