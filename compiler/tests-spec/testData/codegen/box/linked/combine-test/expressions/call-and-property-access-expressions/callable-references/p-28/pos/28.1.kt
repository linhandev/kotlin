// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 28 -> sentence 28
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: unbound property reference User::name infers (User) -> String and is invoked with receiver argument, verifying runtime semantics
 */

data class User(val name: String)

val g: (User) -> String = User::name

// TESTCASE NUMBER: 1
fun test(u: User): String = g(u)

fun box(): String {
    if (test(User("hello")) != "hello") return "NOK1"
    if (test(User("kotlin")) != "kotlin") return "NOK2"
    return "OK"
}
