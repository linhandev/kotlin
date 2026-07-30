// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 5 -> sentence 5
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: unbound property reference User::name typed as (User) -> String and invoked with receiver instance, verifying runtime semantics
 */

data class User(val name: String)

// TESTCASE NUMBER: 1
val g: (User) -> String = User::name

fun test(u: User): String = g(u)

fun box(): String {
    if (test(User("Alice")) != "Alice") return "NOK"
    if (test(User("Bob")) != "Bob") return "NOK"
    return "OK"
}
