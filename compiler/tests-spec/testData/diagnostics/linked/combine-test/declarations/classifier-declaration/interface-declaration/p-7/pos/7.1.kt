// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 7 -> sentence 7
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 7 -> sentence 7
 *                declarations, function-declaration -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: primary constructor override val with interface default function type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Named {
    val name: String
    fun label(): String = "user:$name"
}

class User(override val name: String) : Named

fun case1() {
    val u = User("Ann")
    checkSubtype<User>(u)
    checkSubtype<String>(u.name)
    checkSubtype<String>(u.label())
    checkSubtype<Named>(u)
}
